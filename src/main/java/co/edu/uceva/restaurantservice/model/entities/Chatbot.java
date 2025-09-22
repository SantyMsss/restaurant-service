package co.edu.uceva.restaurantservice.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "chatbot")
public class Chatbot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    
    @Column(nullable = false)
    private String input;
    
    @Column(nullable = false)
    private String respuesta;
    
    // Constructores
    public Chatbot() {}
    
    public Chatbot(Integer restaurantId, Integer usuarioId, String input, String respuesta) {
        this.restaurantId = restaurantId;
        this.usuarioId = usuarioId;
        this.input = input;
        this.respuesta = respuesta;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getRestaurantId() {
        return restaurantId;
    }
    
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public Integer getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public String getInput() {
        return input;
    }
    
    public void setInput(String input) {
        this.input = input;
    }
    
    public String getRespuesta() {
        return respuesta;
    }
    
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
