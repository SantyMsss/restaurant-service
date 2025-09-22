package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.RestauranteDao;
import co.edu.uceva.restaurantservice.model.entities.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements IRestauranteService {
    
    @Autowired
    private RestauranteDao restauranteDao;
    
    @Override
    public List<Restaurante> listar() {
        return restauranteDao.findAll();
    }
    
    @Override
    public Restaurante findById(Integer id) {
        return restauranteDao.findById(id).orElse(null);
    }
    
    @Override
    public Restaurante save(Restaurante restaurante) {
        return restauranteDao.save(restaurante);
    }
    
    @Override
    public void delete(Restaurante restaurante) {
        restauranteDao.delete(restaurante);
    }
    
    @Override
    public Restaurante findByNombre(String nombre) {
        return restauranteDao.findByNombre(nombre).orElse(null);
    }
    
    @Override
    public List<Restaurante> findByDireccion(String direccion) {
        return restauranteDao.findByDireccionContainingIgnoreCase(direccion);
    }
}

