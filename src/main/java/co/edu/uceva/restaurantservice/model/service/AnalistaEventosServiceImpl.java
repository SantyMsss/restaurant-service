package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.AnalistaEventosDao;
import co.edu.uceva.restaurantservice.model.entities.AnalistaEventos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalistaEventosServiceImpl implements IAnalistaEventosService {

    private final AnalistaEventosDao analistaEventosDao;

    public AnalistaEventosServiceImpl(AnalistaEventosDao analistaEventosDao) { this.analistaEventosDao = analistaEventosDao; }

    @Override
    public List<AnalistaEventos> listar() { return analistaEventosDao.findAll(); }

    @Override
    public AnalistaEventos findById(Integer id) { return analistaEventosDao.findById(id).orElse(null); }

    @Override
    public AnalistaEventos save(AnalistaEventos analistaEventos) { return analistaEventosDao.save(analistaEventos); }

    @Override
    public void delete(Integer id) { analistaEventosDao.deleteById(id); }

    @Override
    public List<AnalistaEventos> findByRestaurant(Integer restaurantId) { return analistaEventosDao.findByRestaurantId(restaurantId); }
}
