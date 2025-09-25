package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.Pedido;
import co.edu.uceva.restaurantservice.model.service.IPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedido-service")
@CrossOrigin(origins = "*")
public class PedidoRestController {

    private final IPedidoService pedidoService;

    public PedidoRestController(IPedidoService pedidoService) { this.pedidoService = pedidoService; }

    @GetMapping("/pedidos")
    public List<Pedido> listar() { return pedidoService.listar(); }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Integer id) {
        Pedido p = pedidoService.findById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping("/pedido")
    public Pedido crear(@RequestBody Pedido pedido) { return pedidoService.save(pedido); }

    @PutMapping("/pedido")
    public Pedido actualizar(@RequestBody Pedido pedido) { return pedidoService.save(pedido); }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (pedidoService.findById(id) != null) { pedidoService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pedidos/usuario/{usuarioId}")
    public List<Pedido> porUsuario(@PathVariable Integer usuarioId) { return pedidoService.findByUsuario(usuarioId); }

    @GetMapping("/pedidos/estado/{estado}")
    public List<Pedido> porEstado(@PathVariable String estado) { return pedidoService.findByEstado(estado); }

    @GetMapping("/pedidos/reserva/{reservaId}")
    public List<Pedido> porReserva(@PathVariable Integer reservaId) { return pedidoService.findByReserva(reservaId); }

    @GetMapping("/pedidos/pendientes")
    public List<Pedido> pendientes() { return pedidoService.findPendientes(); }

    @GetMapping("/pedidos/precio")
    public List<Pedido> porRangoPrecio(@RequestParam Float min, @RequestParam Float max) { return pedidoService.findByRangoPrecio(min, max); }

    @GetMapping("/pedidos/ventas/usuario/{usuarioId}")
    public Float totalVentasUsuario(@PathVariable Integer usuarioId) { return pedidoService.getTotalVentasByUsuario(usuarioId); }
}
