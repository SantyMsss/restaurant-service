package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.ItemsMenu;
import co.edu.uceva.restaurantservice.model.service.IItemsMenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items-menu-service")
@CrossOrigin(origins = "*")
public class ItemsMenuRestController {

    private final IItemsMenuService itemsMenuService;

    public ItemsMenuRestController(IItemsMenuService itemsMenuService) { this.itemsMenuService = itemsMenuService; }

    @GetMapping("/items")
    public List<ItemsMenu> listar() { return itemsMenuService.listar(); }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemsMenu> buscar(@PathVariable Integer id) {
        ItemsMenu i = itemsMenuService.findById(id);
        return i != null ? ResponseEntity.ok(i) : ResponseEntity.notFound().build();
    }

    @PostMapping("/item")
    public ItemsMenu crear(@RequestBody ItemsMenu itemsMenu) { return itemsMenuService.save(itemsMenu); }

    @PutMapping("/item")
    public ItemsMenu actualizar(@RequestBody ItemsMenu itemsMenu) { return itemsMenuService.save(itemsMenu); }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (itemsMenuService.findById(id) != null) { itemsMenuService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/items/categoria/{categoriaId}")
    public List<ItemsMenu> porCategoria(@PathVariable Integer categoriaId) { return itemsMenuService.findByCategoria(categoriaId); }

    @GetMapping("/items/categoria/{categoriaId}/disponibles")
    public List<ItemsMenu> disponiblesPorCategoria(@PathVariable Integer categoriaId) { return itemsMenuService.findDisponiblesByCategoria(categoriaId); }

    @GetMapping("/items/buscar")
    public List<ItemsMenu> buscar(@RequestParam String q) { return itemsMenuService.buscarPorNombre(q); }

    @GetMapping("/items/precio")
    public List<ItemsMenu> porRangoPrecio(@RequestParam Float min, @RequestParam Float max) { return itemsMenuService.findByRangoPrecio(min, max); }

    @GetMapping("/items/disponibles")
    public List<ItemsMenu> disponibles() { return itemsMenuService.findDisponibles(); }

    @GetMapping("/items/baratos")
    public List<ItemsMenu> masBaratos(@RequestParam Float max) { return itemsMenuService.findMasBaratosQue(max); }
}
