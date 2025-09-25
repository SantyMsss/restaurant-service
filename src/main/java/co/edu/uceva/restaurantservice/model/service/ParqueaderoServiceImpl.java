package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Parqueadero;
import org.springframework.stereotype.Service;
import co.edu.uceva.restaurantservice.model.dao.ParqueaderoDao;

import java.util.List;

@Service
public class ParqueaderoServiceImpl implements IParqueaderoService {

    private final ParqueaderoDao parqueaderoDao;

    public ParqueaderoServiceImpl(ParqueaderoDao parqueaderoDao) { this.parqueaderoDao = parqueaderoDao; }

    @Override
    public List<Parqueadero> listar() { return parqueaderoDao.findAll(); }

    @Override
    public Parqueadero findById(Integer id) { return parqueaderoDao.findById(id).orElse(null); }

    @Override
    public Parqueadero save(Parqueadero parqueadero) { return parqueaderoDao.save(parqueadero); }

    @Override
    public void delete(Integer id) { parqueaderoDao.deleteById(id); }

    @Override
    public List<Parqueadero> findByRestaurante(Integer restauranteId) { return parqueaderoDao.findByRestauranteId(restauranteId); }
}
