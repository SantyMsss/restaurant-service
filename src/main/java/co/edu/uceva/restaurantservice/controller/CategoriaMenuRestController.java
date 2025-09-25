package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.CategoriaMenu;
import co.edu.uceva.restaurantservice.model.service.ICategoriaMenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria-menu-service")
@CrossOrigin(origins = "*")
public class CategoriaMenuRestController {

    private final ICategoriaMenuService categoriaMenuService;

    public CategoriaMenuRestController(ICategoriaMenuService categoriaMenuService) { this.categoriaMenuService = categoriaMenuService; }

    @GetMapping("/categorias")
    public List<CategoriaMenu> listar() { return categoriaMenuService.listar(); }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaMenu> buscar(@PathVariable Integer id) {
        CategoriaMenu c = categoriaMenuService.findById(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping("/categoria")
    public CategoriaMenu crear(@RequestBody CategoriaMenu categoriaMenu) { return categoriaMenuService.save(categoriaMenu); }

    @PutMapping("/categoria")
    public CategoriaMenu actualizar(@RequestBody CategoriaMenu categoriaMenu) { return categoriaMenuService.save(categoriaMenu); }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (categoriaMenuService.findById(id) != null) { categoriaMenuService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categorias/restaurante/{restauranteId}")
    public List<CategoriaMenu> porRestaurante(@PathVariable Integer restauranteId) { return categoriaMenuService.findByRestaurante(restauranteId); }

    @GetMapping("/categorias/nombre/{nombre}")
    public ResponseEntity<CategoriaMenu> porNombre(@PathVariable String nombre) {
        CategoriaMenu c = categoriaMenuService.findByNombre(nombre);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @GetMapping("/categorias/buscar")
    public List<CategoriaMenu> buscar(@RequestParam String q) { return categoriaMenuService.buscarPorNombre(q); }
}
