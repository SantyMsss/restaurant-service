package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.Parqueadero;
import co.edu.uceva.restaurantservice.model.service.IParqueaderoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parqueadero-service")
@CrossOrigin(origins = "*")
public class ParqueaderoRestController {

    private final IParqueaderoService parqueaderoService;

    public ParqueaderoRestController(IParqueaderoService parqueaderoService) { this.parqueaderoService = parqueaderoService; }

    @GetMapping("/parqueaderos")
    public List<Parqueadero> listar() { return parqueaderoService.listar(); }

    @GetMapping("/parqueaderos/{id}")
    public ResponseEntity<Parqueadero> buscar(@PathVariable Integer id) {
        Parqueadero p = parqueaderoService.findById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping("/parqueadero")
    public Parqueadero crear(@RequestBody Parqueadero parqueadero) { return parqueaderoService.save(parqueadero); }

    @PutMapping("/parqueadero")
    public Parqueadero actualizar(@RequestBody Parqueadero parqueadero) { return parqueaderoService.save(parqueadero); }

    @DeleteMapping("/parqueaderos/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (parqueaderoService.findById(id) != null) { parqueaderoService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/parqueaderos/restaurante/{restauranteId}")
    public List<Parqueadero> porRestaurante(@PathVariable Integer restauranteId) { return parqueaderoService.findByRestaurante(restauranteId); }
}
