package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.Restaurante;
import co.edu.uceva.restaurantservice.model.service.RestauranteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurante-service")
@CrossOrigin(origins = "*")
public class RestauranteRestController {
    
    @Autowired
    private RestauranteServiceImpl restauranteService;
    
    /**
     * Obtener todos los restaurantes
     * @return Lista de restaurantes
     */
    @GetMapping("/restaurantes")
    public List<Restaurante> listar() {
        return restauranteService.listar();
    }
    
    /**
     * Buscar restaurante por ID
     * @param id ID del restaurante
     * @return Restaurante encontrado
     */
    @GetMapping("/restaurantes/{id}")
    public ResponseEntity<Restaurante> buscarRestaurante(@PathVariable Integer id) {
        Restaurante restaurante = restauranteService.findById(id);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Crear nuevo restaurante
     * @param restaurante Datos del restaurante
     * @return Restaurante creado
     */
    @PostMapping("/restaurante")
    public Restaurante guardarRestaurante(@RequestBody Restaurante restaurante) {
        return restauranteService.save(restaurante);
    }
    
    /**
     * Actualizar restaurante
     * @param restaurante Datos actualizados del restaurante
     * @return Restaurante actualizado
     */
    @PutMapping("/restaurante")
    public Restaurante actualizarRestaurante(@RequestBody Restaurante restaurante) {
        return restauranteService.save(restaurante);
    }
    
    /**
     * Eliminar restaurante por ID
     * @param id ID del restaurante a eliminar
     */
    @DeleteMapping("/restaurantes/{id}")
    public ResponseEntity<Void> eliminarRestaurante(@PathVariable Integer id) {
        Restaurante restaurante = restauranteService.findById(id);
        if (restaurante != null) {
            restauranteService.delete(restaurante);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Buscar restaurante por nombre
     * @param nombre Nombre del restaurante
     * @return Restaurante encontrado
     */
    @GetMapping("/restaurantes/nombre/{nombre}")
    public ResponseEntity<Restaurante> buscarPorNombre(@PathVariable String nombre) {
        Restaurante restaurante = restauranteService.findByNombre(nombre);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Buscar restaurantes por dirección
     * @param direccion Dirección a buscar
     * @return Lista de restaurantes
     */
    @GetMapping("/restaurantes/direccion/{direccion}")
    public List<Restaurante> buscarPorDireccion(@PathVariable String direccion) {
        return restauranteService.findByDireccion(direccion);
    }
}

