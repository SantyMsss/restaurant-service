package co.edu.uceva.restaurantservice.controller;

import co.edu.uceva.restaurantservice.model.entities.Chatbot;
import co.edu.uceva.restaurantservice.model.service.IChatbotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chatbot-service")
@CrossOrigin(origins = "*")
public class ChatbotRestController {

    private final IChatbotService chatbotService;

    public ChatbotRestController(IChatbotService chatbotService) { this.chatbotService = chatbotService; }

    @GetMapping("/chats")
    public List<Chatbot> listar() { return chatbotService.listar(); }

    @GetMapping("/chats/{id}")
    public ResponseEntity<Chatbot> buscar(@PathVariable Integer id) {
        Chatbot c = chatbotService.findById(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping("/chat")
    public Chatbot crear(@RequestBody Chatbot chatbot) { return chatbotService.save(chatbot); }

    @PutMapping("/chat")
    public Chatbot actualizar(@RequestBody Chatbot chatbot) { return chatbotService.save(chatbot); }

    @DeleteMapping("/chats/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (chatbotService.findById(id) != null) { chatbotService.delete(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/chats/restaurante/{restaurantId}")
    public List<Chatbot> porRestaurant(@PathVariable Integer restaurantId) { return chatbotService.findByRestaurant(restaurantId); }

    @GetMapping("/chats/usuario/{usuarioId}")
    public List<Chatbot> porUsuario(@PathVariable Integer usuarioId) { return chatbotService.findByUsuario(usuarioId); }
}
