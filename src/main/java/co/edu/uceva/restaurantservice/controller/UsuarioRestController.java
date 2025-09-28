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

    @PostMapping(value = "/usuario", 
                consumes = {"application/json", "application/json;charset=UTF-8"}, 
                produces = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) { 
        try {
            // Validar campos requeridos
            if (usuario.getNomUsuario() == null || usuario.getNomUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getEmailUsuario() == null || usuario.getEmailUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getRolUsuario() == null || usuario.getRolUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getEstUsuario() == null || usuario.getEstUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            Usuario usuarioGuardado = usuarioService.save(usuario);
            return ResponseEntity.ok(usuarioGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/usuario", 
               consumes = {"application/json", "application/json;charset=UTF-8"}, 
               produces = {"application/json", "application/json;charset=UTF-8"})
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario) { 
        try {
            // Validar que el usuario existe para actualización
            if (usuario.getId() == null) {
                return ResponseEntity.badRequest().build();
            }
            
            Usuario usuarioExistente = usuarioService.findById(usuario.getId());
            if (usuarioExistente == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Validar campos requeridos
            if (usuario.getNomUsuario() == null || usuario.getNomUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getEmailUsuario() == null || usuario.getEmailUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getRolUsuario() == null || usuario.getRolUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (usuario.getEstUsuario() == null || usuario.getEstUsuario().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            Usuario usuarioActualizado = usuarioService.save(usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
