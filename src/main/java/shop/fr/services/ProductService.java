package shop.fr.services;

import java.util.List;

import org.springframework.stereotype.Service;

import shop.fr.DAO.entities.Product;

/**
 * ProductService interface provides methods for managing products.
 */
@Service
public interface ProductService {
	
	/**
     * Retrieves a list of all products.
     *
     * @return A list of Product objects representing all available products.
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a product with the specified ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The Product object corresponding to the given product ID, or null if not found.
     */
    Product getProductById(Long productId);

    /**
     * Creates a new product.
     *
     * @param product The Product object to be created.
     * @return The newly created Product object.
     */
    Product createProduct(Product product);

    /**
     * Updates an existing product with the specified ID.
     *
     * @param productId The ID of the product to update.
     * @param product   The updated Product object.
     * @return The updated Product object.
     */
    Product updateProduct(Long productId, Product product);

    /**
     * Deletes a product with the specified ID.
     *
     * @param productId The ID of the product to delete.
     */
    void deleteProduct(Long productId);
    
}
