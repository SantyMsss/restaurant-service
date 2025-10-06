package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.UsuarioDao;
import co.edu.uceva.restaurantservice.model.entities.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioDao usuarioDao, PasswordEncoder passwordEncoder) { 
        this.usuarioDao = usuarioDao; 
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> listar() { return usuarioDao.findAll(); }

    @Override
    public Usuario findById(Integer id) { return usuarioDao.findById(id).orElse(null); }

    @Override
    public Usuario save(Usuario usuario) { 
        // Validar que no exista otro usuario con el mismo email (excepto si es actualización del mismo)
        Usuario usuarioExistente = usuarioDao.findByEmailUsuario(usuario.getEmailUsuario()).orElse(null);
        if (usuarioExistente != null && !usuarioExistente.getId().equals(usuario.getId())) {
            throw new RuntimeException("Ya existe un usuario con el email: " + usuario.getEmailUsuario());
        }
        
        // Encriptar la contraseña antes de guardar
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            // Solo encriptar si la contraseña no está ya encriptada (para evitar doble encriptación en updates)
            if (!isPasswordEncrypted(usuario.getPassword())) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
        }
        
        return usuarioDao.save(usuario); 
    }
    
    /**
     * Verifica si una contraseña ya está encriptada con BCrypt
     * Las contraseñas de BCrypt siempre empiezan con $2a$, $2b$, $2x$ o $2y$
     */
    private boolean isPasswordEncrypted(String password) {
        return password != null && password.matches("^\\$2[abyxy]\\$.*");
    }

    @Override
    public void delete(Integer id) { usuarioDao.deleteById(id); }

    @Override
    public Usuario findByEmail(String email) { return usuarioDao.findByEmailUsuario(email).orElse(null); }

    @Override
    public Usuario login(String email, String password) { 
        // Buscar usuario por email
        Usuario usuario = usuarioDao.findByEmailUsuario(email).orElse(null);
        
        // Si el usuario existe y la contraseña coincide
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;
        }
        
        return null;
    }

    @Override
    public List<Usuario> findByRol(String rol) { return usuarioDao.findByRolUsuario(rol); }

    @Override
    public List<Usuario> findActivos() { return usuarioDao.findUsuariosActivos(); }

    @Override
    public List<Usuario> buscarPorNombre(String texto) { return usuarioDao.findByNomUsuarioContainingIgnoreCase(texto); }

    @Override
    public boolean existeEmail(String email) { return usuarioDao.existsByEmailUsuario(email); }
}
