package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nom_usuario", nullable = false)
    private String nomUsuario;
    
    @Column(name = "email_usuario", nullable = false, unique = true)
    private String emailUsuario;
    
    @Column(name = "rol_usuario", nullable = false)
    private String rolUsuario;
    
    @Column(name = "tel_usuario")
    private String telUsuario;
    
    @Column(nullable = false)
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    @Column(name = "est_usuario", nullable = false)
    private String estUsuario;
    
    // Relación uno a muchos con Reserva
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("usuario-reservas")
    private List<Reserva> reservas;
    
    // Relación uno a muchos con Pedido
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("usuario-pedidos")
    private List<Pedido> pedidos;
    
    // Constructores
    public Usuario() {}
    
    public Usuario(String nomUsuario, String emailUsuario, String rolUsuario, String telUsuario, String password, String estUsuario) {
        this.nomUsuario = nomUsuario;
        this.emailUsuario = emailUsuario;
        this.rolUsuario = rolUsuario;
        this.telUsuario = telUsuario;
        this.password = password;
        this.estUsuario = estUsuario;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNomUsuario() {
        return nomUsuario;
    }
    
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }
    
    public String getEmailUsuario() {
        return emailUsuario;
    }
    
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    
    public String getRolUsuario() {
        return rolUsuario;
    }
    
    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
    
    public String getTelUsuario() {
        return telUsuario;
    }
    
    public void setTelUsuario(String telUsuario) {
        this.telUsuario = telUsuario;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEstUsuario() {
        return estUsuario;
    }
    
    public void setEstUsuario(String estUsuario) {
        this.estUsuario = estUsuario;
    }
    
    public List<Reserva> getReservas() {
        return reservas;
    }
    
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
