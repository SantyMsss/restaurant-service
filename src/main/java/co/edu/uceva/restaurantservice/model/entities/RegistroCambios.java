package co.edu.uceva.restaurantservice.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_cambios")
public class RegistroCambios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    
    @Column(name = "tip_cambio", nullable = false)
    private String tipCambio;
    
    @Column(name = "regla_afectada", nullable = false)
    private String reglaAfectada;
    
    @Column(name = "fech_cambio", nullable = false)
    private LocalDateTime fechCambio;
    
    // Constructores
    public RegistroCambios() {}
    
    public RegistroCambios(Integer usuarioId, String tipCambio, String reglaAfectada, LocalDateTime fechCambio) {
        this.usuarioId = usuarioId;
        this.tipCambio = tipCambio;
        this.reglaAfectada = reglaAfectada;
        this.fechCambio = fechCambio;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public String getTipCambio() {
        return tipCambio;
    }
    
    public void setTipCambio(String tipCambio) {
        this.tipCambio = tipCambio;
    }
    
    public String getReglaAfectada() {
        return reglaAfectada;
    }
    
    public void setReglaAfectada(String reglaAfectada) {
        this.reglaAfectada = reglaAfectada;
    }
    
    public LocalDateTime getFechCambio() {
        return fechCambio;
    }
    
    public void setFechCambio(LocalDateTime fechCambio) {
        this.fechCambio = fechCambio;
    }
}
