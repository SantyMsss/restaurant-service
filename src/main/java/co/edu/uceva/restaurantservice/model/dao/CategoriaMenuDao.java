package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.CategoriaMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaMenuDao extends JpaRepository<CategoriaMenu, Integer> {
    
    // Buscar categorías por restaurante
    List<CategoriaMenu> findByRestauranteId(Integer restauranteId);
    
    // Buscar categoría por nombre
    Optional<CategoriaMenu> findByNombre(String nombre);
    
    // Buscar categorías por nombre que contenga un texto
    List<CategoriaMenu> findByNombreContainingIgnoreCase(String nombre);
}

