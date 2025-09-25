package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.AnalistaEventos;
import co.edu.uceva.restaurantservice.model.service.IAnalistaEventosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analista-eventos-service")
@CrossOrigin(origins = "*")
public class AnalistaEventosRestController {

    private final IAnalistaEventosService analistaEventosService;

    public AnalistaEventosRestController(IAnalistaEventosService analistaEventosService) { this.analistaEventosService = analistaEventosService; }

    @GetMapping("/analistas")
    public List<AnalistaEventos> listar() { return analistaEventosService.listar(); }

    @GetMapping("/analistas/{id}")
    public ResponseEntity<AnalistaEventos> buscar(@PathVariable Integer id) {
        AnalistaEventos a = analistaEventosService.findById(id);
        return a != null ? ResponseEntity.ok(a) : ResponseEntity.notFound().build();
    }

    @PostMapping("/analista")
    public AnalistaEventos crear(@RequestBody AnalistaEventos analistaEventos) { return analistaEventosService.save(analistaEventos); }

    @PutMapping("/analista")
    public AnalistaEventos actualizar(@RequestBody AnalistaEventos analistaEventos) { return analistaEventosService.save(analistaEventos); }

    @DeleteMapping("/analistas/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (analistaEventosService.findById(id) != null) { analistaEventosService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/analistas/restaurante/{restaurantId}")
    public List<AnalistaEventos> porRestaurant(@PathVariable Integer restaurantId) { return analistaEventosService.findByRestaurant(restaurantId); }
}
