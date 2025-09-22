package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "mesa_id", nullable = false)
    private Integer mesaId;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    
    @Column(name = "est_reserva", nullable = false)
    private String estReserva;
    
    @Column(name = "fech_reserva", nullable = false)
    private LocalDateTime fechReserva;
    
    // Relación muchos a uno con Mesa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id", insertable = false, updatable = false)
    @JsonBackReference
    private Mesa mesa;
    
    // Relación muchos a uno con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    @JsonBackReference
    private Usuario usuario;
    
    // Constructores
    public Reserva() {}
    
    public Reserva(Integer mesaId, Integer usuarioId, String estReserva, LocalDateTime fechReserva) {
        this.mesaId = mesaId;
        this.usuarioId = usuarioId;
        this.estReserva = estReserva;
        this.fechReserva = fechReserva;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getMesaId() {
        return mesaId;
    }
    
    public void setMesaId(Integer mesaId) {
        this.mesaId = mesaId;
    }
    
    public Integer getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public String getEstReserva() {
        return estReserva;
    }
    
    public void setEstReserva(String estReserva) {
        this.estReserva = estReserva;
    }
    
    public LocalDateTime getFechReserva() {
        return fechReserva;
    }
    
    public void setFechReserva(LocalDateTime fechReserva) {
        this.fechReserva = fechReserva;
    }
    
    public Mesa getMesa() {
        return mesa;
    }
    
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
