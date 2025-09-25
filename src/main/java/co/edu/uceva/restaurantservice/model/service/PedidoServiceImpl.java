package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.PedidoDao;
import co.edu.uceva.restaurantservice.model.entities.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoDao pedidoDao;

    public PedidoServiceImpl(PedidoDao pedidoDao) { this.pedidoDao = pedidoDao; }

    @Override
    public List<Pedido> listar() { return pedidoDao.findAll(); }

    @Override
    public Pedido findById(Integer id) { return pedidoDao.findById(id).orElse(null); }

    @Override
    public Pedido save(Pedido pedido) { return pedidoDao.save(pedido); }

    @Override
    public void delete(Integer id) { pedidoDao.deleteById(id); }

    @Override
    public List<Pedido> findByUsuario(Integer usuarioId) { return pedidoDao.findByUsuarioId(usuarioId); }

    @Override
    public List<Pedido> findByEstado(String estado) { return pedidoDao.findByEstPedido(estado); }

    @Override
    public List<Pedido> findByReserva(Integer reservaId) { return pedidoDao.findByReservaId(reservaId); }

    @Override
    public List<Pedido> findPendientes() { return pedidoDao.findPedidosPendientes(); }

    @Override
    public List<Pedido> findByRangoPrecio(Float min, Float max) { return pedidoDao.findByPreTotPedidoBetween(min, max); }

    @Override
    public Float getTotalVentasByUsuario(Integer usuarioId) { return pedidoDao.getTotalVentasByUsuario(usuarioId); }
}
