/**
 * @author Alma MAHMOUDI
 *
 */

/**
 * Represents a Product entity.
 * Each product has a unique identifier, name, description, price, and available quantity.
 */
package shop.fr.DAO.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	/**
     * Default constructor for the Product entity.
     */
	public Product() {}

	 /**
     * Creates a new Product with specified details.
     *
     * @param name             The name of the product.
     * @param description      The description of the product.
     * @param price            The price of the product.
     * @param quantityAvailable The quantity available in stock for the product.
     */
	public Product(String name, String description, double price, int quantityAvailable) {
		this.name = name;
		this.description = description;
		this.price = price;
		QuantityAvailable = quantityAvailable;
	}

	public Product(long id,String name, String description, double price, int quantityAvailable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		QuantityAvailable = quantityAvailable;
	}
	
	/**Getters et Setters */
	public long getId() {return id;}
	public void setId(long id) {this.id = id;}

	public String getProductName() {return name;}
	public void setProductName(String productName) {this.name = productName;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;}

	public int getQuantityAvailable() {return QuantityAvailable;}
	public void setQuantityAvailable(int quantityAvailable) {QuantityAvailable = quantityAvailable;}
	
	/**
	 * Provides a string representation of the Product object.
	 * @return A formatted string displaying the product's id, name, description, price, and available quantity.
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + name + ", description=" + description + ", price="
				+ price + ", QuantityAvailable=" + QuantityAvailable + "]";
	}
	
	

}
