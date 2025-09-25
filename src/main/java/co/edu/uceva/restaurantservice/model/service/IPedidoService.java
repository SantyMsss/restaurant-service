package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Pedido;
import java.util.List;

public interface IPedidoService {
    List<Pedido> listar();
    Pedido findById(Integer id);
    Pedido save(Pedido pedido);
    void delete(Integer id);
    List<Pedido> findByUsuario(Integer usuarioId);
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByReserva(Integer reservaId);
    List<Pedido> findPendientes();
    List<Pedido> findByRangoPrecio(Float min, Float max);
    Float getTotalVentasByUsuario(Integer usuarioId);
}
