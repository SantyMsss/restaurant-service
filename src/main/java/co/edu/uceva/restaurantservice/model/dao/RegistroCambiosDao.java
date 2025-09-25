package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.RegistroCambios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroCambiosDao extends JpaRepository<RegistroCambios, Integer> {
    List<RegistroCambios> findByUsuarioId(Integer usuarioId);
    List<RegistroCambios> findByTipCambio(String tipCambio);
    List<RegistroCambios> findByFechCambioBetween(LocalDateTime inicio, LocalDateTime fin);
}
