package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.DetallePedido;
import co.edu.uceva.restaurantservice.model.service.IDetallePedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalle-pedido-service")
@CrossOrigin(origins = "*")
public class DetallePedidoRestController {

    private final IDetallePedidoService detallePedidoService;

    public DetallePedidoRestController(IDetallePedidoService detallePedidoService) { this.detallePedidoService = detallePedidoService; }

    @GetMapping("/detalles")
    public List<DetallePedido> listar() { return detallePedidoService.listar(); }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<DetallePedido> buscar(@PathVariable Integer id) {
        DetallePedido d = detallePedidoService.findById(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    @PostMapping("/detalle")
    public DetallePedido crear(@RequestBody DetallePedido detallePedido) { return detallePedidoService.save(detallePedido); }

    @PutMapping("/detalle")
    public DetallePedido actualizar(@RequestBody DetallePedido detallePedido) { return detallePedidoService.save(detallePedido); }

    @DeleteMapping("/detalles/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (detallePedidoService.findById(id) != null) { detallePedidoService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/detalles/pedido/{pedidoId}")
    public List<DetallePedido> porPedido(@PathVariable Integer pedidoId) { return detallePedidoService.findByPedido(pedidoId); }

    @GetMapping("/detalles/item/{itemMenuId}")
    public List<DetallePedido> porItem(@PathVariable Integer itemMenuId) { return detallePedidoService.findByItemMenu(itemMenuId); }

    @GetMapping("/detalles/pedido/{pedidoId}/subtotal")
    public Float subtotalPorPedido(@PathVariable Integer pedidoId) { return detallePedidoService.getSubtotalByPedido(pedidoId); }

    @GetMapping("/detalles/mas-vendidos")
    public List<Object[]> itemsMasVendidos() { return detallePedidoService.getItemsMasVendidos(); }

    @GetMapping("/detalles/cantidad/mayor")
    public List<DetallePedido> cantidadMayor(@RequestParam Integer cant) { return detallePedidoService.findByCantidadMayorA(cant); }
}
