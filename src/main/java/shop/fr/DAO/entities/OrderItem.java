package shop.fr.DAO.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.*;

@Entity
@Table(name="T_OrderItem") 
public class OrderItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
	
	@ManyToOne
    @JoinColumn(name = "order_id") 
    private Order order;
    
    @Column(name="quantity",length=100, nullable=true)
    private int quantity;

	public OrderItem() {}

	public OrderItem(int quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id +  ", quantity=" + quantity + "]";
	}
    
	
    
}
