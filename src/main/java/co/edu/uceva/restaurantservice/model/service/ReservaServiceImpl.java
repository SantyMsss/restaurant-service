package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.ReservaDao;
import co.edu.uceva.restaurantservice.model.entities.Reserva;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaServiceImpl implements IReservaService {

    private final ReservaDao reservaDao;

    public ReservaServiceImpl(ReservaDao reservaDao) {
        this.reservaDao = reservaDao;
    }

    @Override
    public List<Reserva> listar() { return reservaDao.findAll(); }

    @Override
    public Reserva findById(Integer id) { return reservaDao.findById(id).orElse(null); }

    @Override
    public Reserva save(Reserva reserva) { return reservaDao.save(reserva); }

    @Override
    public void delete(Integer id) { reservaDao.deleteById(id); }

    @Override
    public List<Reserva> findByUsuario(Integer usuarioId) { return reservaDao.findByUsuarioId(usuarioId); }

    @Override
    public List<Reserva> findByMesa(Integer mesaId) { return reservaDao.findByMesaId(mesaId); }

    @Override
    public List<Reserva> findByEstado(String estado) { return reservaDao.findByEstReserva(estado); }

    @Override
    public List<Reserva> findActivasByMesa(Integer mesaId) { return reservaDao.findReservasActivasByMesa(mesaId); }

    @Override
    public List<Reserva> findByRangoFechas(LocalDateTime inicio, LocalDateTime fin) { return reservaDao.findByFechReservaBetween(inicio, fin); }

    @Override
    public List<Reserva> findByUsuarioAndFecha(Integer usuarioId, LocalDateTime fecha) { return reservaDao.findReservasByUsuarioAndFecha(usuarioId, fecha); }
}
