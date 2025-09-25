package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.Usuario;
import co.edu.uceva.restaurantservice.model.service.IUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario-service")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

    private final IUsuarioService usuarioService;

    public UsuarioRestController(IUsuarioService usuarioService) { this.usuarioService = usuarioService; }

    @GetMapping({"/usuarios", "/usuario"})
    public List<Usuario> listar() { return usuarioService.listar(); }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {
        Usuario u = usuarioService.findById(id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario")
    public Usuario crear(@RequestBody Usuario usuario) { return usuarioService.save(usuario); }

    @PutMapping("/usuario")
    public Usuario actualizar(@RequestBody Usuario usuario) { return usuarioService.save(usuario); }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (usuarioService.findById(id) != null) { usuarioService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios/email")
    public ResponseEntity<Usuario> porEmail(@RequestParam String email) {
        Usuario u = usuarioService.findByEmail(email);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String password) {
        Usuario u = usuarioService.login(email, password);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.status(401).build();
    }

    @GetMapping("/usuarios/rol/{rol}")
    public List<Usuario> porRol(@PathVariable String rol) { return usuarioService.findByRol(rol); }

    @GetMapping("/usuarios/activos")
    public List<Usuario> activos() { return usuarioService.findActivos(); }

    @GetMapping("/usuarios/buscar")
    public List<Usuario> buscarNombre(@RequestParam String q) { return usuarioService.buscarPorNombre(q); }

    @GetMapping("/usuarios/existe")
    public boolean existeEmail(@RequestParam String email) { return usuarioService.existeEmail(email); }
}
