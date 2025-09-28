package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.Mesa;
import co.edu.uceva.restaurantservice.model.service.MesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mesa-service")
@CrossOrigin(origins = "*")
public class MesaRestController {
    
    @Autowired
    private MesaServiceImpl mesaService;
    
    /**
     * Obtener todas las mesas
     * @return Lista de mesas
     */
    @GetMapping("/mesas")
    public List<Mesa> listar() {
        return mesaService.listar();
    }
    
    /**
     * Buscar mesa por ID
     * @param id ID de la mesa
     * @return Mesa encontrada
     */
    @GetMapping("/mesas/{id}")
    public ResponseEntity<Mesa> buscarMesa(@PathVariable Integer id) {
        Mesa mesa = mesaService.findById(id);
        if (mesa != null) {
            return ResponseEntity.ok(mesa);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Crear nueva mesa
     * @param mesa Datos de la mesa
     * @return Mesa creada
     */
    @PostMapping(value = "/mesa", 
                consumes = {"application/json", "application/json;charset=UTF-8"}, 
                produces = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<?> guardarMesa(@RequestBody Mesa mesa) {
        try {
            Mesa mesaGuardada = mesaService.save(mesa);
            return ResponseEntity.ok(mesaGuardada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear mesa: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }
    
    /**
     * Actualizar mesa
     * @param mesa Datos actualizados de la mesa
     * @return Mesa actualizada
     */
    @PutMapping(value = "/mesa", 
               consumes = {"application/json", "application/json;charset=UTF-8"}, 
               produces = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<?> actualizarMesa(@RequestBody Mesa mesa) {
        try {
            if (mesa.getId() == null) {
                return ResponseEntity.badRequest().body("El ID es requerido para actualizar una mesa");
            }
            
            Mesa mesaExistente = mesaService.findById(mesa.getId());
            if (mesaExistente == null) {
                return ResponseEntity.notFound().build();
            }
            
            Mesa mesaActualizada = mesaService.save(mesa);
            return ResponseEntity.ok(mesaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al actualizar mesa: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }
    
    /**
     * Eliminar mesa por ID
     * @param id ID de la mesa a eliminar
     */
    @DeleteMapping("/mesas/{id}")
    public ResponseEntity<Void> eliminarMesa(@PathVariable Integer id) {
        Mesa mesa = mesaService.findById(id);
        if (mesa != null) {
            mesaService.delete(mesa);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Buscar mesas por restaurante
     * @param restauranteId ID del restaurante
     * @return Lista de mesas del restaurante
     */
    @GetMapping("/mesas/restaurante/{restauranteId}")
    public List<Mesa> buscarPorRestaurante(@PathVariable Integer restauranteId) {
        return mesaService.findByRestauranteId(restauranteId);
    }
    
    /**
     * Buscar mesas disponibles por restaurante
     * @param restauranteId ID del restaurante
     * @return Lista de mesas disponibles
     */
    @GetMapping("/mesas/disponibles/restaurante/{restauranteId}")
    public List<Mesa> buscarDisponiblesPorRestaurante(@PathVariable Integer restauranteId) {
        return mesaService.findMesasDisponiblesByRestaurante(restauranteId);
    }
    
    /**
     * Buscar mesas disponibles con capacidad mínima
     * @param numSillas Número mínimo de sillas
     * @return Lista de mesas con capacidad suficiente
     */
    @GetMapping("/mesas/disponibles/capacidad/{numSillas}")
    public List<Mesa> buscarConCapacidad(@PathVariable Integer numSillas) {
        return mesaService.findMesasDisponiblesConCapacidad(numSillas);
    }
    
    /**
     * Buscar mesa por código
     * @param codMesa Código de la mesa
     * @return Mesa encontrada
     */
    @GetMapping("/mesas/codigo/{codMesa}")
    public ResponseEntity<Mesa> buscarPorCodigo(@PathVariable String codMesa) {
        Mesa mesa = mesaService.findByCodMesa(codMesa);
        if (mesa != null) {
            return ResponseEntity.ok(mesa);
        }
        return ResponseEntity.notFound().build();
    }
}

