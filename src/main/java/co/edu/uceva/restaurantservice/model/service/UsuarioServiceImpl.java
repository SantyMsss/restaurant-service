package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.UsuarioDao;
import co.edu.uceva.restaurantservice.model.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioDao usuarioDao;

    public UsuarioServiceImpl(UsuarioDao usuarioDao) { this.usuarioDao = usuarioDao; }

    @Override
    public List<Usuario> listar() { return usuarioDao.findAll(); }

    @Override
    public Usuario findById(Integer id) { return usuarioDao.findById(id).orElse(null); }

    @Override
    public Usuario save(Usuario usuario) { return usuarioDao.save(usuario); }

    @Override
    public void delete(Integer id) { usuarioDao.deleteById(id); }

    @Override
    public Usuario findByEmail(String email) { return usuarioDao.findByEmailUsuario(email).orElse(null); }

    @Override
    public Usuario login(String email, String password) { return usuarioDao.findByEmailUsuarioAndPassword(email, password).orElse(null); }

    @Override
    public List<Usuario> findByRol(String rol) { return usuarioDao.findByRolUsuario(rol); }

    @Override
    public List<Usuario> findActivos() { return usuarioDao.findUsuariosActivos(); }

    @Override
    public List<Usuario> buscarPorNombre(String texto) { return usuarioDao.findByNomUsuarioContainingIgnoreCase(texto); }

    @Override
    public boolean existeEmail(String email) { return usuarioDao.existsByEmailUsuario(email); }
}
