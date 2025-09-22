package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "num_sillas", nullable = false)
    private Integer numSillas;
    
    @Column(name = "est_mesa", nullable = false)
    private Boolean estMesa;
    
    @Column(name = "cod_mesa", nullable = false, unique = true)
    private String codMesa;
    
    // Relación muchos a uno con Restaurante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    @JsonBackReference
    private Restaurante restaurante;
    
    // Relación uno a muchos con Reserva
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Reserva> reservas;
    
    // Constructores
    public Mesa() {}
    
    public Mesa(Integer numSillas, Boolean estMesa, String codMesa, Restaurante restaurante) {
        this.numSillas = numSillas;
        this.estMesa = estMesa;
        this.codMesa = codMesa;
        this.restaurante = restaurante;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getNumSillas() {
        return numSillas;
    }
    
    public void setNumSillas(Integer numSillas) {
        this.numSillas = numSillas;
    }
    
    public Boolean getEstMesa() {
        return estMesa;
    }
    
    public void setEstMesa(Boolean estMesa) {
        this.estMesa = estMesa;
    }
    
    public String getCodMesa() {
        return codMesa;
    }
    
    public void setCodMesa(String codMesa) {
        this.codMesa = codMesa;
    }
    
    public Restaurante getRestaurante() {
        return restaurante;
    }
    
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    
    public List<Reserva> getReservas() {
        return reservas;
    }
    
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
