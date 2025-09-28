package co.edu.uceva.restaurantservice.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "detalle_pedido")
public class DetallePedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "pedido_id", nullable = false)
    private Integer pedidoId;
    
    @Column(name = "item_menu_id", nullable = false)
    private Integer itemMenuId;
    
    @Column(name = "cant_item", nullable = false)
    private Integer cantItem;
    
    @Column(name = "prec_unitario", nullable = false)
    private Float precUnitario;
    
    @Column(nullable = false)
    private Float subtotal;
    
    // Relación muchos a uno con Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    @JsonBackReference("pedido-detalles")
    private Pedido pedido;
    
    // Relación muchos a uno con ItemsMenu
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_menu_id", insertable = false, updatable = false)
    private ItemsMenu itemsMenu;
    
    // Constructores
    public DetallePedido() {}
    
    public DetallePedido(Integer pedidoId, Integer itemMenuId, Integer cantItem, Float precUnitario, Float subtotal) {
        this.pedidoId = pedidoId;
        this.itemMenuId = itemMenuId;
        this.cantItem = cantItem;
        this.precUnitario = precUnitario;
        this.subtotal = subtotal;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPedidoId() {
        return pedidoId;
    }
    
    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }
    
    public Integer getItemMenuId() {
        return itemMenuId;
    }
    
    public void setItemMenuId(Integer itemMenuId) {
        this.itemMenuId = itemMenuId;
    }
    
    public Integer getCantItem() {
        return cantItem;
    }
    
    public void setCantItem(Integer cantItem) {
        this.cantItem = cantItem;
    }
    
    public Float getPrecUnitario() {
        return precUnitario;
    }
    
    public void setPrecUnitario(Float precUnitario) {
        this.precUnitario = precUnitario;
    }
    
    public Float getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public ItemsMenu getItemsMenu() {
        return itemsMenu;
    }
    
    public void setItemsMenu(ItemsMenu itemsMenu) {
        this.itemsMenu = itemsMenu;
    }
}
