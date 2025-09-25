package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Usuario;
import java.util.List;

public interface IUsuarioService {
    List<Usuario> listar();
    Usuario findById(Integer id);
    Usuario save(Usuario usuario);
    void delete(Integer id);
    Usuario findByEmail(String email);
    Usuario login(String email, String password);
    List<Usuario> findByRol(String rol);
    List<Usuario> findActivos();
    List<Usuario> buscarPorNombre(String texto);
    boolean existeEmail(String email);
}
