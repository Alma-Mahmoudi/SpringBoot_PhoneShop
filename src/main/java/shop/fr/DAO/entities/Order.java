package shop.fr.DAO.entities;

/**
 * Represents a Order entity.
 * Each Order has a unique identifier, date, status, customerName, customerEmail and available List of products.
 */

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
	private double totalAmount = 0.0;
    
	@Column(name="status",length=100, nullable=true)
	private String status;
	
	@Column(name="CustomerName",length=100, nullable=true)
    private String customerName;
	
	@Column(name="CustomerEmail",length=100, nullable=true)
    private String customerEmail;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products = new ArrayList<>();

	 /**
     * Default constructor for the Order entity.
     */
	public Order() {}
	
	/**
     * Creates a new Order with specified details.
     *
     * @param orderDate      The date of the order.
     * @param totalAmount    The total amount of the order.
     * @param status         The status of the order.
     * @param customerName   The name of the customer who placed the order.
     * @param customerEmail  The email of the customer who placed the order.
     */
	public Order(Date orderDate, double totalAmount, String status, String customerName, String customerEmail) {
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
	}
	
	/**
     * Creates a new Order with specified details.
     *
     * @param id             The id of the order
     * @param orderDate      The date of the order.
     * @param totalAmount    The total amount of the order.
     * @param status         The status of the order.
     * @param customerName   The name of the customer who placed the order.
     * @param customerEmail  The email of the customer who placed the order.
     */
	public Order(long id, Date orderDate, double totalAmount, String status, String customerName, String customerEmail,
			List<Product> products) {
		this.id = id;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.products = products;
	}

	//Getters & setters 
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}

	public Date getOrderDate() {return orderDate;}
	public void setOrderDate(Date orderDate) {this.orderDate = orderDate;}

	public double getTotalAmount() {return totalAmount;}
	public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}

	public String getCustomerName() {return customerName;}
	public void setCustomerName(String customerName) {this.customerName = customerName;}

	public String getCustomerEmail() {return customerEmail;}
	public void setCustomerEmail(String customerEmail) {this.customerEmail = customerEmail;}
	
	/**
     * Get the list of products associated with this order.
     *
     * @return The list of products.
     */
	public List<Product> getProducts() {
        return products;
    }
	
	/**
     * Set the list of products associated with this order.
     *
     * @param products The list of products to set.
     */
	public void setProducts(List<Product> products) {
        //this.products.clear();
        if (products != null) {
            this.products.addAll(products);
        }
    }
	
	/**
     * Update the total amount of the order by summing the prices of all products in the order.
     */
	public void updateTotalAmount() {
		
		if (products.size() == 0) {
			this.totalAmount = 0.0 ; 
        }else {
        	for (Product product : products) {
                this.totalAmount += product.getPrice();
            }
        }		        
	}
	
	/**
	 * Provides a string representation of the Order object.
	 * @return A formatted string displaying the order's id,  date, status, customerName and customerEmail .
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", customerName=" + customerName + ", customerEmail=" + customerEmail + "]";
	}
	
}
