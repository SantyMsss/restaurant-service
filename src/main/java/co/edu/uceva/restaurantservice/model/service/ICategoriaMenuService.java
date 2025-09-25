package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.CategoriaMenu;
import java.util.List;

public interface ICategoriaMenuService {
    List<CategoriaMenu> listar();
    CategoriaMenu findById(Integer id);
    CategoriaMenu save(CategoriaMenu categoriaMenu);
    void delete(Integer id);
    List<CategoriaMenu> findByRestaurante(Integer restauranteId);
    CategoriaMenu findByNombre(String nombre);
    List<CategoriaMenu> buscarPorNombre(String texto);
}
