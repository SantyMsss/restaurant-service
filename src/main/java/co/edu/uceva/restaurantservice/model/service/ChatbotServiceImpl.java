package co.edu.uceva.restaurantservice.model.service;

import co.edu.uceva.restaurantservice.model.dao.ChatbotDao;
import co.edu.uceva.restaurantservice.model.entities.Chatbot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotServiceImpl implements IChatbotService {

    private final ChatbotDao chatbotDao;

    public ChatbotServiceImpl(ChatbotDao chatbotDao) { this.chatbotDao = chatbotDao; }

    @Override
    public List<Chatbot> listar() { return chatbotDao.findAll(); }

    @Override
    public Chatbot findById(Integer id) { return chatbotDao.findById(id).orElse(null); }

    @Override
    public Chatbot save(Chatbot chatbot) { return chatbotDao.save(chatbot); }

    @Override
    public void delete(Integer id) { chatbotDao.deleteById(id); }

    @Override
    public List<Chatbot> findByRestaurant(Integer restaurantId) { return chatbotDao.findByRestaurantId(restaurantId); }

    @Override
    public List<Chatbot> findByUsuario(Integer usuarioId) { return chatbotDao.findByUsuarioId(usuarioId); }
}
