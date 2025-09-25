package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.entities.Chatbot;
import java.util.List;

public interface IChatbotService {
    List<Chatbot> listar();
    Chatbot findById(Integer id);
    Chatbot save(Chatbot chatbot);
    void delete(Integer id);
    List<Chatbot> findByRestaurant(Integer restaurantId);
    List<Chatbot> findByUsuario(Integer usuarioId);
}
