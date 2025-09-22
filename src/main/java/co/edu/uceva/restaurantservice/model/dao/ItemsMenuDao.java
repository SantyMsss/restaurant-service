package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.ItemsMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsMenuDao extends JpaRepository<ItemsMenu, Integer> {
    
    // Buscar items por categoría
    List<ItemsMenu> findByCategoriaMenuId(Integer categoriaMenuId);
    
    // Buscar items disponibles por categoría
    @Query("SELECT i FROM ItemsMenu i WHERE i.categoriaMenu.id = :categoriaId AND i.estItem = true")
    List<ItemsMenu> findItemsDisponiblesByCategoria(@Param("categoriaId") Integer categoriaId);
    
    // Buscar items por nombre
    List<ItemsMenu> findByNomItemContainingIgnoreCase(String nomItem);
    
    // Buscar items por rango de precio
    List<ItemsMenu> findByPrecItemBetween(Float precioMin, Float precioMax);
    
    // Buscar items disponibles
    List<ItemsMenu> findByEstItemTrue();
    
    // Buscar items más baratos que un precio
    List<ItemsMenu> findByPrecItemLessThanEqualAndEstItemTrue(Float precioMax);
}

