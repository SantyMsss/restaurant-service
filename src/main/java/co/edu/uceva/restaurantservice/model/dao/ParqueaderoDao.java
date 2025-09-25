package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParqueaderoDao extends JpaRepository<Parqueadero, Integer> {
    List<Parqueadero> findByRestauranteId(Integer restauranteId);
}
