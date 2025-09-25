package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.DetallePedidoDao;
import co.edu.uceva.restaurantservice.model.entities.DetallePedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements IDetallePedidoService {

    private final DetallePedidoDao detallePedidoDao;

    public DetallePedidoServiceImpl(DetallePedidoDao detallePedidoDao) { this.detallePedidoDao = detallePedidoDao; }

    @Override
    public List<DetallePedido> listar() { return detallePedidoDao.findAll(); }

    @Override
    public DetallePedido findById(Integer id) { return detallePedidoDao.findById(id).orElse(null); }

    @Override
    public DetallePedido save(DetallePedido detallePedido) { return detallePedidoDao.save(detallePedido); }

    @Override
    public void delete(Integer id) { detallePedidoDao.deleteById(id); }

    @Override
    public List<DetallePedido> findByPedido(Integer pedidoId) { return detallePedidoDao.findByPedidoId(pedidoId); }

    @Override
    public List<DetallePedido> findByItemMenu(Integer itemMenuId) { return detallePedidoDao.findByItemMenuId(itemMenuId); }

    @Override
    public Float getSubtotalByPedido(Integer pedidoId) { return detallePedidoDao.getSubtotalByPedido(pedidoId); }

    @Override
    public List<Object[]> getItemsMasVendidos() { return detallePedidoDao.getItemsMasVendidos(); }

    @Override
    public List<DetallePedido> findByCantidadMayorA(Integer cantidad) { return detallePedidoDao.findByCantItemGreaterThan(cantidad); }
}
