package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "parqueadero")
public class Parqueadero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "cod_parqueadero", nullable = false, unique = true)
    private String codParqueadero;
    
    @Column(name = "est_parqueadero", nullable = false)
    private Boolean estParqueadero;
    
    // Relación muchos a uno con Restaurante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    @JsonBackReference
    private Restaurante restaurante;
    
    // Constructores
    public Parqueadero() {}
    
    public Parqueadero(String codParqueadero, Boolean estParqueadero, Restaurante restaurante) {
        this.codParqueadero = codParqueadero;
        this.estParqueadero = estParqueadero;
        this.restaurante = restaurante;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodParqueadero() {
        return codParqueadero;
    }
    
    public void setCodParqueadero(String codParqueadero) {
        this.codParqueadero = codParqueadero;
    }
    
    public Boolean getEstParqueadero() {
        return estParqueadero;
    }
    
    public void setEstParqueadero(Boolean estParqueadero) {
        this.estParqueadero = estParqueadero;
    }
    
    public Restaurante getRestaurante() {
        return restaurante;
    }
    
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
