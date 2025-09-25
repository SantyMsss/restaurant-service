package co.edu.uceva.restaurantservice.model.dao;

import co.edu.uceva.restaurantservice.model.entities.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatbotDao extends JpaRepository<Chatbot, Integer> {
    List<Chatbot> findByRestaurantId(Integer restaurantId);
    List<Chatbot> findByUsuarioId(Integer usuarioId);
}
