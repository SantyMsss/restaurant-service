package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.MesaDao;
import co.edu.uceva.restaurantservice.model.dao.RestauranteDao;
import co.edu.uceva.restaurantservice.model.entities.Mesa;
import co.edu.uceva.restaurantservice.model.entities.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaServiceImpl implements IMesaService {
    
    @Autowired
    private MesaDao mesaDao;
    
    @Autowired
    private RestauranteDao restauranteDao;
    
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
        // Validar campos requeridos
        if (mesa.getNumSillas() == null || mesa.getNumSillas() <= 0) {
            throw new RuntimeException("El número de sillas es requerido y debe ser mayor a 0");
        }
        
        if (mesa.getCodMesa() == null || mesa.getCodMesa().trim().isEmpty()) {
            throw new RuntimeException("El código de mesa es requerido");
        }
        
        if (mesa.getEstMesa() == null) {
            mesa.setEstMesa(true); // Valor por defecto: disponible
        }
        
        // Validar que el restaurante existe
        if (mesa.getRestaurante() == null) {
            throw new RuntimeException("El restaurante es requerido");
        }
        
        if (mesa.getRestaurante().getId() == null) {
            throw new RuntimeException("El ID del restaurante es requerido");
        }
        
        Restaurante restaurante = restauranteDao.findById(mesa.getRestaurante().getId())
            .orElseThrow(() -> new RuntimeException("Restaurante no encontrado con ID: " + mesa.getRestaurante().getId()));
        
        // Verificar que no exista otra mesa con el mismo código en el mismo restaurante
        Mesa mesaExistente = mesaDao.findByCodMesa(mesa.getCodMesa()).orElse(null);
        if (mesaExistente != null && !mesaExistente.getId().equals(mesa.getId())) {
            throw new RuntimeException("Ya existe una mesa con el código: " + mesa.getCodMesa());
        }
        
        mesa.setRestaurante(restaurante);
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

