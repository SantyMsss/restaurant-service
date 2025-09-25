package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Parqueadero;
import java.util.List;

public interface IParqueaderoService {
    List<Parqueadero> listar();
    Parqueadero findById(Integer id);
    Parqueadero save(Parqueadero parqueadero);
    void delete(Integer id);
    List<Parqueadero> findByRestaurante(Integer restauranteId);
}
