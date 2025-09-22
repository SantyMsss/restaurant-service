package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurante")
public class Restaurante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String direccion;
    
    @Column(nullable = false)
    private String telefono;
    
    // Relación uno a muchos con Mesa
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Mesa> mesas;
    
    // Relación uno a muchos con Parqueadero
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Parqueadero> parqueaderos;
    
    // Relación uno a muchos con Categoria_Menu
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CategoriaMenu> categoriasMenu;
    
    // Constructores
    public Restaurante() {}
    
    public Restaurante(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public List<Mesa> getMesas() {
        return mesas;
    }
    
    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
    
    public List<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }
    
    public void setParqueaderos(List<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }
    
    public List<CategoriaMenu> getCategoriasMenu() {
        return categoriasMenu;
    }
    
    public void setCategoriasMenu(List<CategoriaMenu> categoriasMenu) {
        this.categoriasMenu = categoriasMenu;
    }
}
