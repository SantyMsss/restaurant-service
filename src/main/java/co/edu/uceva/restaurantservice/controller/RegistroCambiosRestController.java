package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.RegistroCambios;
import co.edu.uceva.restaurantservice.model.service.IRegistroCambiosService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registro-cambios-service")
@CrossOrigin(origins = "*")
public class RegistroCambiosRestController {

    private final IRegistroCambiosService registroCambiosService;

    public RegistroCambiosRestController(IRegistroCambiosService registroCambiosService) { this.registroCambiosService = registroCambiosService; }

    @GetMapping("/registros")
    public List<RegistroCambios> listar() { return registroCambiosService.listar(); }

    @GetMapping("/registros/{id}")
    public ResponseEntity<RegistroCambios> buscar(@PathVariable Integer id) {
        RegistroCambios r = registroCambiosService.findById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping("/registro")
    public RegistroCambios crear(@RequestBody RegistroCambios registroCambios) { return registroCambiosService.save(registroCambios); }

    @PutMapping("/registro")
    public RegistroCambios actualizar(@RequestBody RegistroCambios registroCambios) { return registroCambiosService.save(registroCambios); }

    @DeleteMapping("/registros/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (registroCambiosService.findById(id) != null) { registroCambiosService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/registros/usuario/{usuarioId}")
    public List<RegistroCambios> porUsuario(@PathVariable Integer usuarioId) { return registroCambiosService.findByUsuario(usuarioId); }

    @GetMapping("/registros/tipo/{tipo}")
    public List<RegistroCambios> porTipo(@PathVariable String tipo) { return registroCambiosService.findByTipoCambio(tipo); }

    @GetMapping("/registros/rango")
    public List<RegistroCambios> porRango(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return registroCambiosService.findByRangoFechas(inicio, fin);
    }
}
