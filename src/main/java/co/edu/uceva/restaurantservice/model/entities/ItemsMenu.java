package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "items_menu")
public class ItemsMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nom_item", nullable = false)
    private String nomItem;
    
    @Column(name = "prec_item", nullable = false)
    private Float precItem;
    
    @Column(name = "desc_item")
    private String descItem;
    
    @Column(name = "est_item", nullable = false)
    private Boolean estItem;
    
    @Column(name = "img_item_menu")
    private String imgItemMenu;
    
    // Relación muchos a uno con CategoriaMenu
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_menu_id", nullable = false)
    @JsonBackReference
    private CategoriaMenu categoriaMenu;
    
    // Constructores
    public ItemsMenu() {}
    
    public ItemsMenu(String nomItem, Float precItem, String descItem, Boolean estItem, String imgItemMenu, CategoriaMenu categoriaMenu) {
        this.nomItem = nomItem;
        this.precItem = precItem;
        this.descItem = descItem;
        this.estItem = estItem;
        this.imgItemMenu = imgItemMenu;
        this.categoriaMenu = categoriaMenu;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNomItem() {
        return nomItem;
    }
    
    public void setNomItem(String nomItem) {
        this.nomItem = nomItem;
    }
    
    public Float getPrecItem() {
        return precItem;
    }
    
    public void setPrecItem(Float precItem) {
        this.precItem = precItem;
    }
    
    public String getDescItem() {
        return descItem;
    }
    
    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }
    
    public Boolean getEstItem() {
        return estItem;
    }
    
    public void setEstItem(Boolean estItem) {
        this.estItem = estItem;
    }
    
    public String getImgItemMenu() {
        return imgItemMenu;
    }
    
    public void setImgItemMenu(String imgItemMenu) {
        this.imgItemMenu = imgItemMenu;
    }
    
    public CategoriaMenu getCategoriaMenu() {
        return categoriaMenu;
    }
    
    public void setCategoriaMenu(CategoriaMenu categoriaMenu) {
        this.categoriaMenu = categoriaMenu;
    }
}
