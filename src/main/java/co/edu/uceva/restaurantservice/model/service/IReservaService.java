package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Reserva;
import java.time.LocalDateTime;
import java.util.List;

public interface IReservaService {
    List<Reserva> listar();
    Reserva findById(Integer id);
    Reserva save(Reserva reserva);
    void delete(Integer id);
    List<Reserva> findByUsuario(Integer usuarioId);
    List<Reserva> findByMesa(Integer mesaId);
    List<Reserva> findByEstado(String estado);
    List<Reserva> findActivasByMesa(Integer mesaId);
    List<Reserva> findByRangoFechas(LocalDateTime inicio, LocalDateTime fin);
    List<Reserva> findByUsuarioAndFecha(Integer usuarioId, LocalDateTime fecha);
}
