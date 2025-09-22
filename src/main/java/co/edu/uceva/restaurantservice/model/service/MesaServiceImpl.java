package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.MesaDao;
import co.edu.uceva.restaurantservice.model.entities.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements IMesaService {
    
    @Autowired
    private MesaDao mesaDao;
    
    @Override
    public List<Mesa> listar() {
        return mesaDao.findAll();
    }
    
    @Override
    public Mesa findById(Integer id) {
        return mesaDao.findById(id).orElse(null);
    }
    
    @Override
    public Mesa save(Mesa mesa) {
        return mesaDao.save(mesa);
    }
    
    @Override
    public void delete(Mesa mesa) {
        mesaDao.delete(mesa);
    }
    
    @Override
    public Mesa findByCodMesa(String codMesa) {
        return mesaDao.findByCodMesa(codMesa).orElse(null);
    }
    
    @Override
    public List<Mesa> findByRestauranteId(Integer restauranteId) {
        return mesaDao.findByRestauranteId(restauranteId);
    }
    
    @Override
    public List<Mesa> findMesasDisponiblesByRestaurante(Integer restauranteId) {
        return mesaDao.findMesasDisponiblesByRestaurante(restauranteId);
    }
    
    @Override
    public List<Mesa> findMesasDisponiblesConCapacidad(Integer numSillas) {
        return mesaDao.findMesasDisponiblesConCapacidad(numSillas);
    }
}

