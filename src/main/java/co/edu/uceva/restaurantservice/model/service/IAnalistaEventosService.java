package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.AnalistaEventos;
import java.util.List;

public interface IAnalistaEventosService {
    List<AnalistaEventos> listar();
    AnalistaEventos findById(Integer id);
    AnalistaEventos save(AnalistaEventos analistaEventos);
    void delete(Integer id);
    List<AnalistaEventos> findByRestaurant(Integer restaurantId);
}
