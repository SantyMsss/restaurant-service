package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Integer> {
    
    // Buscar pedidos por usuario
    List<Pedido> findByUsuarioId(Integer usuarioId);
    
    // Buscar pedidos por estado
    List<Pedido> findByEstPedido(String estPedido);
    
    // Buscar pedidos por reserva
    List<Pedido> findByReservaId(Integer reservaId);
    
    // Buscar pedidos pendientes
    @Query("SELECT p FROM Pedido p WHERE p.estPedido = 'PENDIENTE'")
    List<Pedido> findPedidosPendientes();
    
    // Buscar pedidos por rango de precio total
    List<Pedido> findByPreTotPedidoBetween(Float precioMin, Float precioMax);
    
    // Obtener total de ventas por usuario
    @Query("SELECT SUM(p.preTotPedido) FROM Pedido p WHERE p.usuarioId = :usuarioId AND p.estPedido = 'COMPLETADO'")
    Float getTotalVentasByUsuario(@Param("usuarioId") Integer usuarioId);
}

