package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.DetallePedido;
import java.util.List;

public interface IDetallePedidoService {
    List<DetallePedido> listar();
    DetallePedido findById(Integer id);
    DetallePedido save(DetallePedido detallePedido);
    void delete(Integer id);
    List<DetallePedido> findByPedido(Integer pedidoId);
    List<DetallePedido> findByItemMenu(Integer itemMenuId);
    Float getSubtotalByPedido(Integer pedidoId);
    List<Object[]> getItemsMasVendidos();
    List<DetallePedido> findByCantidadMayorA(Integer cantidad);
}
