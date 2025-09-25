package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.Reserva;
import co.edu.uceva.restaurantservice.model.service.IReservaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reserva-service")
@CrossOrigin(origins = "*")
public class ReservaRestController {

    private final IReservaService reservaService;

    public ReservaRestController(IReservaService reservaService) { this.reservaService = reservaService; }

    @GetMapping("/reservas")
    public List<Reserva> listar() { return reservaService.listar(); }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<Reserva> buscar(@PathVariable Integer id) {
        Reserva r = reservaService.findById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping("/reserva")
    public Reserva crear(@RequestBody Reserva reserva) { return reservaService.save(reserva); }

    @PutMapping("/reserva")
    public Reserva actualizar(@RequestBody Reserva reserva) { return reservaService.save(reserva); }

    @DeleteMapping("/reservas/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (reservaService.findById(id) != null) { reservaService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/reservas/usuario/{usuarioId}")
    public List<Reserva> porUsuario(@PathVariable Integer usuarioId) { return reservaService.findByUsuario(usuarioId); }

    @GetMapping("/reservas/mesa/{mesaId}")
    public List<Reserva> porMesa(@PathVariable Integer mesaId) { return reservaService.findByMesa(mesaId); }

    @GetMapping("/reservas/estado/{estado}")
    public List<Reserva> porEstado(@PathVariable String estado) { return reservaService.findByEstado(estado); }

    @GetMapping("/reservas/mesa/{mesaId}/activas")
    public List<Reserva> activasPorMesa(@PathVariable Integer mesaId) { return reservaService.findActivasByMesa(mesaId); }

    @GetMapping("/reservas/rango")
    public List<Reserva> porRango(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return reservaService.findByRangoFechas(inicio, fin);
    }

    @GetMapping("/reservas/usuario/{usuarioId}/fecha")
    public List<Reserva> porUsuarioFecha(@PathVariable Integer usuarioId,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return reservaService.findByUsuarioAndFecha(usuarioId, fecha);
    }
}
