package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaDao extends JpaRepository<Mesa, Integer> {
    
    // Buscar mesa por código
    Optional<Mesa> findByCodMesa(String codMesa);
    
    // Buscar mesas por restaurante
    List<Mesa> findByRestauranteId(Integer restauranteId);
    
    // Buscar mesas disponibles por restaurante
    @Query("SELECT m FROM Mesa m WHERE m.restaurante.id = :restauranteId AND m.estMesa = true")
    List<Mesa> findMesasDisponiblesByRestaurante(@Param("restauranteId") Integer restauranteId);
    
    // Buscar mesas por número de sillas
    List<Mesa> findByNumSillasGreaterThanEqual(Integer numSillas);
    
    // Buscar mesas disponibles con capacidad mínima
    @Query("SELECT m FROM Mesa m WHERE m.numSillas >= :numSillas AND m.estMesa = true")
    List<Mesa> findMesasDisponiblesConCapacidad(@Param("numSillas") Integer numSillas);
}
