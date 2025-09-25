package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.AnalistaEventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalistaEventosDao extends JpaRepository<AnalistaEventos, Integer> {
    List<AnalistaEventos> findByRestaurantId(Integer restaurantId);
}
