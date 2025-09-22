package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaDao extends JpaRepository<Reserva, Integer> {
    
    // Buscar reservas por usuario
    List<Reserva> findByUsuarioId(Integer usuarioId);
    
    // Buscar reservas por mesa
    List<Reserva> findByMesaId(Integer mesaId);
    
    // Buscar reservas por estado
    List<Reserva> findByEstReserva(String estReserva);
    
    // Buscar reservas activas por mesa
    @Query("SELECT r FROM Reserva r WHERE r.mesaId = :mesaId AND r.estReserva = 'ACTIVA'")
    List<Reserva> findReservasActivasByMesa(@Param("mesaId") Integer mesaId);
    
    // Buscar reservas por rango de fechas
    List<Reserva> findByFechReservaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    // Buscar reservas de un usuario en una fecha específica
    @Query("SELECT r FROM Reserva r WHERE r.usuarioId = :usuarioId AND DATE(r.fechReserva) = DATE(:fecha)")
    List<Reserva> findReservasByUsuarioAndFecha(@Param("usuarioId") Integer usuarioId, @Param("fecha") LocalDateTime fecha);
}

