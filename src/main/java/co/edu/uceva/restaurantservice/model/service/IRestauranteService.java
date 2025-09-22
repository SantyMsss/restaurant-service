package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Restaurante;
import java.util.List;

public interface IRestauranteService {
    
    /**
     * Método para listar todos los restaurantes
     * @return Lista de restaurantes
     */
    List<Restaurante> listar();
    
    /**
     * Método para buscar un restaurante por ID
     * @param id ID del restaurante
     * @return Restaurante encontrado
     */
    Restaurante findById(Integer id);
    
    /**
     * Método para guardar un restaurante
     * @param restaurante Restaurante a guardar
     * @return Restaurante guardado
     */
    Restaurante save(Restaurante restaurante);
    
    /**
     * Método para eliminar un restaurante
     * @param restaurante Restaurante a eliminar
     */
    void delete(Restaurante restaurante);
    
    /**
     * Método para buscar restaurante por nombre
     * @param nombre Nombre del restaurante
     * @return Restaurante encontrado
     */
    Restaurante findByNombre(String nombre);
    
    /**
     * Método para buscar restaurantes por dirección
     * @param direccion Dirección a buscar
     * @return Lista de restaurantes
     */
    List<Restaurante> findByDireccion(String direccion);
}

