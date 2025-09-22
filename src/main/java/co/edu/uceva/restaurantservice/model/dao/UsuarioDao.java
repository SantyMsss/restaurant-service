package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
    
    // Buscar usuario por email
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
    
    // Buscar usuario por email y password (para login)
    Optional<Usuario> findByEmailUsuarioAndPassword(String emailUsuario, String password);
    
    // Buscar usuarios por rol
    List<Usuario> findByRolUsuario(String rolUsuario);
    
    // Buscar usuarios activos
    @Query("SELECT u FROM Usuario u WHERE u.estUsuario = 'ACTIVO'")
    List<Usuario> findUsuariosActivos();
    
    // Buscar usuarios por nombre que contenga un texto
    List<Usuario> findByNomUsuarioContainingIgnoreCase(String nomUsuario);
    
    // Verificar si existe un email
    boolean existsByEmailUsuario(String emailUsuario);
}

