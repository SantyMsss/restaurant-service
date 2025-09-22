package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteDao extends JpaRepository<Restaurante, Integer> {
    
    // Buscar restaurante por nombre
    Optional<Restaurante> findByNombre(String nombre);
    
    // Buscar restaurantes por ciudad/dirección que contenga un texto
    List<Restaurante> findByDireccionContainingIgnoreCase(String direccion);
    
    // Buscar restaurantes por teléfono
    Optional<Restaurante> findByTelefono(String telefono);
}

