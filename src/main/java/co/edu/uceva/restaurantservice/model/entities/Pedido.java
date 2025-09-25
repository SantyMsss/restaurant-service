package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "reserva_id")
    private Integer reservaId;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    
    @Column(name = "est_pedido", nullable = false)
    private String estPedido;
    
    @Column(name = "pre_tot_pedido", nullable = false)
    private Float preTotPedido;
    
    // Relación muchos a uno con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    @JsonBackReference
    private Usuario usuario;
    
    // Relación uno a muchos con DetallePedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetallePedido> detallesPedido;
    
    // Constructores
    public Pedido() {}
    
    public Pedido(Integer reservaId, Integer usuarioId, String estPedido, Float preTotPedido) {
        this.reservaId = reservaId;
        this.usuarioId = usuarioId;
        this.estPedido = estPedido;
        this.preTotPedido = preTotPedido;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getReservaId() {
        return reservaId;
    }
    
    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }
    
    public Integer getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public String getEstPedido() {
        return estPedido;
    }
    
    public void setEstPedido(String estPedido) {
        this.estPedido = estPedido;
    }
    
    public Float getPreTotPedido() {
        return preTotPedido;
    }
    
    public void setPreTotPedido(Float preTotPedido) {
        this.preTotPedido = preTotPedido;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }
    
    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
}
