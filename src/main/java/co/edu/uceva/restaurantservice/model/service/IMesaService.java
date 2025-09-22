package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Mesa;
import java.util.List;

public interface IMesaService {
    
    List<Mesa> listar();
    Mesa findById(Integer id);
    Mesa save(Mesa mesa);
    void delete(Mesa mesa);
    Mesa findByCodMesa(String codMesa);
    List<Mesa> findByRestauranteId(Integer restauranteId);
    List<Mesa> findMesasDisponiblesByRestaurante(Integer restauranteId);
    List<Mesa> findMesasDisponiblesConCapacidad(Integer numSillas);
}

