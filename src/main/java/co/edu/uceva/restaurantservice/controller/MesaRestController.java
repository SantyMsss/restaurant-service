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
    @PostMapping("/mesa")
    public Mesa guardarMesa(@RequestBody Mesa mesa) {
        return mesaService.save(mesa);
    }
    
    /**
     * Actualizar mesa
     * @param mesa Datos actualizados de la mesa
     * @return Mesa actualizada
     */
    @PutMapping("/mesa")
    public Mesa actualizarMesa(@RequestBody Mesa mesa) {
        return mesaService.save(mesa);
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

