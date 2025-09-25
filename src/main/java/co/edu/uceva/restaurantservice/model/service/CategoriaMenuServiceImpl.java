package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.CategoriaMenuDao;
import co.edu.uceva.restaurantservice.model.entities.CategoriaMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaMenuServiceImpl implements ICategoriaMenuService {

    private final CategoriaMenuDao categoriaMenuDao;

    public CategoriaMenuServiceImpl(CategoriaMenuDao categoriaMenuDao) { this.categoriaMenuDao = categoriaMenuDao; }

    @Override
    public List<CategoriaMenu> listar() { return categoriaMenuDao.findAll(); }

    @Override
    public CategoriaMenu findById(Integer id) { return categoriaMenuDao.findById(id).orElse(null); }

    @Override
    public CategoriaMenu save(CategoriaMenu categoriaMenu) { return categoriaMenuDao.save(categoriaMenu); }

    @Override
    public void delete(Integer id) { categoriaMenuDao.deleteById(id); }

    @Override
    public List<CategoriaMenu> findByRestaurante(Integer restauranteId) { return categoriaMenuDao.findByRestauranteId(restauranteId); }

    @Override
    public CategoriaMenu findByNombre(String nombre) { return categoriaMenuDao.findByNombre(nombre).orElse(null); }

    @Override
    public List<CategoriaMenu> buscarPorNombre(String texto) { return categoriaMenuDao.findByNombreContainingIgnoreCase(texto); }
}
