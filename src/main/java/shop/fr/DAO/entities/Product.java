/**
 * 
 */
package shop.fr.DAO.entities;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * @author Alma MAHMOUDI
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.*;

@Entity
@Table(name="T_Product") 
public class Product{
		
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="ProductName",length=100, nullable=false)
	private String name ;
	
	@Column(name="Description",length=100, nullable=true )
	private String description;
	
	@Column(name="Price",length=100, nullable=true)
	private double price;
	
	@Column(name="Quantity",length=100, nullable=true )
	private int QuantityAvailable ;
	
	public Product() {}

	public Product(String name, String description, double price, int quantityAvailable) {
		this.name = name;
		this.description = description;
		this.price = price;
		QuantityAvailable = quantityAvailable;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return name;
	}

	public void setProductName(String productName) {
		this.name = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return QuantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		QuantityAvailable = quantityAvailable;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + name + ", description=" + description + ", price="
				+ price + ", QuantityAvailable=" + QuantityAvailable + "]";
	}
	
	

}
