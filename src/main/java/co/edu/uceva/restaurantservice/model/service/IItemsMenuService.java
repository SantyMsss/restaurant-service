package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.ItemsMenu;
import java.util.List;

public interface IItemsMenuService {
    List<ItemsMenu> listar();
    ItemsMenu findById(Integer id);
    ItemsMenu save(ItemsMenu itemsMenu);
    void delete(Integer id);
    List<ItemsMenu> findByCategoria(Integer categoriaId);
    List<ItemsMenu> findDisponiblesByCategoria(Integer categoriaId);
    List<ItemsMenu> buscarPorNombre(String texto);
    List<ItemsMenu> findByRangoPrecio(Float min, Float max);
    List<ItemsMenu> findDisponibles();
    List<ItemsMenu> findMasBaratosQue(Float precioMax);
}
