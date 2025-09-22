package co.edu.uceva.restaurantservice.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "analista_eventos")
public class AnalistaEventos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;
    
    @Column(name = "tip_evento", nullable = false)
    private String tipEvento;
    
    // Constructores
    public AnalistaEventos() {}
    
    public AnalistaEventos(Integer restaurantId, String tipEvento) {
        this.restaurantId = restaurantId;
        this.tipEvento = tipEvento;
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
    
    public String getTipEvento() {
        return tipEvento;
    }
    
    public void setTipEvento(String tipEvento) {
        this.tipEvento = tipEvento;
    }
}
