package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoDao extends JpaRepository<DetallePedido, Integer> {
    
    // Buscar detalles por pedido
    List<DetallePedido> findByPedidoId(Integer pedidoId);
    
    // Buscar detalles por item del menú
    List<DetallePedido> findByItemMenuId(Integer itemMenuId);
    
    // Calcular subtotal por pedido
    @Query("SELECT SUM(d.subtotal) FROM DetallePedido d WHERE d.pedidoId = :pedidoId")
    Float getSubtotalByPedido(@Param("pedidoId") Integer pedidoId);
    
    // Obtener items más vendidos
    @Query("SELECT d.itemMenuId, SUM(d.cantItem) as total FROM DetallePedido d GROUP BY d.itemMenuId ORDER BY total DESC")
    List<Object[]> getItemsMasVendidos();
    
    // Buscar detalles con cantidad mayor a un valor
    List<DetallePedido> findByCantItemGreaterThan(Integer cantidad);
}

