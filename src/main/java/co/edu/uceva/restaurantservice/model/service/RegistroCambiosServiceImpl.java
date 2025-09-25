package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.RegistroCambiosDao;
import co.edu.uceva.restaurantservice.model.entities.RegistroCambios;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroCambiosServiceImpl implements IRegistroCambiosService {

    private final RegistroCambiosDao registroCambiosDao;

    public RegistroCambiosServiceImpl(RegistroCambiosDao registroCambiosDao) { this.registroCambiosDao = registroCambiosDao; }

    @Override
    public List<RegistroCambios> listar() { return registroCambiosDao.findAll(); }

    @Override
    public RegistroCambios findById(Integer id) { return registroCambiosDao.findById(id).orElse(null); }

    @Override
    public RegistroCambios save(RegistroCambios registroCambios) { return registroCambiosDao.save(registroCambios); }

    @Override
    public void delete(Integer id) { registroCambiosDao.deleteById(id); }

    @Override
    public List<RegistroCambios> findByUsuario(Integer usuarioId) { return registroCambiosDao.findByUsuarioId(usuarioId); }

    @Override
    public List<RegistroCambios> findByTipoCambio(String tipo) { return registroCambiosDao.findByTipCambio(tipo); }

    @Override
    public List<RegistroCambios> findByRangoFechas(LocalDateTime inicio, LocalDateTime fin) { return registroCambiosDao.findByFechCambioBetween(inicio, fin); }
}
