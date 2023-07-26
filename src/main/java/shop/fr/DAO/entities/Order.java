package shop.fr.DAO.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.*;

@Entity
@Table(name="T_ORDER") 
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name="Date",length=100, nullable=false)
    private Date orderDate;
    
	@Column(name="totalAmount",length=100, nullable=true)
	private double totalAmount;
    
	@Column(name="status",length=100, nullable=true)
	private String status;
	
	@Column(name="CustomerName",length=100, nullable=true)
    private String customerName;
	
	@Column(name="CustomerEmail",length=100, nullable=true)
    private String customerEmail;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products = new ArrayList<>();

	public Order() {}
	public Order(Date orderDate, double totalAmount, String status, String customerName, String customerEmail) {
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
	}
	
	//Getters & setters 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	//Get Products 
	public List<Product> getProducts() {
        return products;
    }
	//Add Products 
	public void setProducts(List<Product> products) {
        this.products.clear();
        if (products != null) {
            this.products.addAll(products);
        }
    }
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", customerName=" + customerName + ", customerEmail=" + customerEmail + "]";
	}
	
}
