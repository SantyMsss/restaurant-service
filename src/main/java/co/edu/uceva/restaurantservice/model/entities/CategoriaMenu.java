package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "categoria_menu")
public class CategoriaMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(name = "img_cat_menu")
    private String imgCatMenu;
    
    // Relación muchos a uno con Restaurante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    @JsonBackReference
    private Restaurante restaurante;
    
    // Relación uno a muchos con Items_Menu
    @OneToMany(mappedBy = "categoriaMenu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ItemsMenu> itemsMenu;
    
    // Constructores
    public CategoriaMenu() {}
    
    public CategoriaMenu(String nombre, String imgCatMenu, Restaurante restaurante) {
        this.nombre = nombre;
        this.imgCatMenu = imgCatMenu;
        this.restaurante = restaurante;
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
    
    public String getImgCatMenu() {
        return imgCatMenu;
    }
    
    public void setImgCatMenu(String imgCatMenu) {
        this.imgCatMenu = imgCatMenu;
    }
    
    public Restaurante getRestaurante() {
        return restaurante;
    }
    
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    
    public List<ItemsMenu> getItemsMenu() {
        return itemsMenu;
    }
    
    public void setItemsMenu(List<ItemsMenu> itemsMenu) {
        this.itemsMenu = itemsMenu;
    }
}
