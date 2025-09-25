package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.RegistroCambios;
import java.time.LocalDateTime;
import java.util.List;

public interface IRegistroCambiosService {
    List<RegistroCambios> listar();
    RegistroCambios findById(Integer id);
    RegistroCambios save(RegistroCambios registroCambios);
    void delete(Integer id);
    List<RegistroCambios> findByUsuario(Integer usuarioId);
    List<RegistroCambios> findByTipoCambio(String tipo);
    List<RegistroCambios> findByRangoFechas(LocalDateTime inicio, LocalDateTime fin);
}
