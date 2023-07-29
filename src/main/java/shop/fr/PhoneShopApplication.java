package shop.fr;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import shop.fr.DAO.entities.Product;
import shop.fr.DAO.repositories.ProductRepository;

/**
 * The main class that starts the PhoneShop application.
 */
@SpringBootApplication
public class PhoneShopApplication {

	private final ProductRepository productRepository;
	
	/**
     * Constructor for the PhoneShopApplication class.
     *
     * @param productRepository The repository for managing Product entities.
     */
	 public PhoneShopApplication(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	 }
	 
	 /**
	     * The main method that starts the PhoneShop application.
	     *
	     * @param args The command line arguments (if any).
	     */
	public static void main(String[] args) {
		SpringApplication.run(PhoneShopApplication.class, args);
	}
	
	/**
     * Initializes the application by inserting some sample products into the database.
     * This method is annotated with @PostConstruct to ensure it is executed after the application context is fully loaded.
     */
	@PostConstruct
    public void init() {
		
		// Insert some sample products into the database at application startup
        Product product1 = new Product("Produit 1", "Description du produit 1", 10.0, 1);
        Product product2 = new Product("Produit 2", "Description du produit 2", 20.0, 2);
//        Product product3 = new Product("Produit 3", "Description du produit 3", 30.0, 3);
//        Product product4 = new Product("Produit 4", "Description du produit 4", 40.0, 4);
//        Product product5 = new Product("Produit 5", "Description du produit 5", 50.0, 5);
//        Product product6 = new Product("Produit 6", "Description du produit 6", 60.0, 6);

        productRepository.saveAll(Arrays.asList(product1, product2));
    }
	

}
