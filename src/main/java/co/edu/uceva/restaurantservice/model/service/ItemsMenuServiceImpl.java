package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.ItemsMenuDao;
import co.edu.uceva.restaurantservice.model.entities.ItemsMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsMenuServiceImpl implements IItemsMenuService {

    private final ItemsMenuDao itemsMenuDao;

    public ItemsMenuServiceImpl(ItemsMenuDao itemsMenuDao) { this.itemsMenuDao = itemsMenuDao; }

    @Override
    public List<ItemsMenu> listar() { return itemsMenuDao.findAll(); }

    @Override
    public ItemsMenu findById(Integer id) { return itemsMenuDao.findById(id).orElse(null); }

    @Override
    public ItemsMenu save(ItemsMenu itemsMenu) { return itemsMenuDao.save(itemsMenu); }

    @Override
    public void delete(Integer id) { itemsMenuDao.deleteById(id); }

    @Override
    public List<ItemsMenu> findByCategoria(Integer categoriaId) { return itemsMenuDao.findByCategoriaMenuId(categoriaId); }

    @Override
    public List<ItemsMenu> findDisponiblesByCategoria(Integer categoriaId) { return itemsMenuDao.findItemsDisponiblesByCategoria(categoriaId); }

    @Override
    public List<ItemsMenu> buscarPorNombre(String texto) { return itemsMenuDao.findByNomItemContainingIgnoreCase(texto); }

    @Override
    public List<ItemsMenu> findByRangoPrecio(Float min, Float max) { return itemsMenuDao.findByPrecItemBetween(min, max); }

    @Override
    public List<ItemsMenu> findDisponibles() { return itemsMenuDao.findByEstItemTrue(); }

    @Override
    public List<ItemsMenu> findMasBaratosQue(Float precioMax) { return itemsMenuDao.findByPrecItemLessThanEqualAndEstItemTrue(precioMax); }
}
