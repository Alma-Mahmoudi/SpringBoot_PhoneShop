package shop.fr.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.fr.DAO.entities.Product;
import shop.fr.DAO.repositories.ProductRepository;
import shop.fr.services.ProductService;

/**
 * ProductController is a RESTful API controller that handles HTTP requests related to products.
 * It provides endpoints for retrieving, creating, updating, and deleting products.
 * The base path for all product-related requests is "/api/products". 
 * http://localhost:4666/api/products
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
     * Constructs a new instance of ProductController.
     *
     * @param productService The ProductService to be used for handling product-related operations.
     */
	public ProductController(ProductService productService) {
		this.productService = productService ; 
	}
	
	  /**
     * Retrieves a list of all products.
     *
     * @return ResponseEntity with a list of Product objects representing all available products, and an HTTP status of 200 OK.
     */
	@GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);	
    }

	 /**
     * Retrieves a product with the specified ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return ResponseEntity with the Product object corresponding to the given product ID, and an HTTP status of 200 OK.
     *         If the product is not found, it returns an HTTP status of 404 Not Found.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
    	Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
	}

    /**
     * Creates a new product.
     *
     * @param product The Product object to be created.
     * @return ResponseEntity with the newly created Product object and an HTTP status of 200 OK.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    	Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Updates an existing product with the specified ID.
     *
     * @param productId The ID of the product to update.
     * @param product   The updated Product object.
     * @return ResponseEntity with the updated Product object and an HTTP status of 200 OK.
     *         If the product is not found, it returns an HTTP status of 404 Not Found.
     */
  	@PutMapping("/{productId}")
  	public ResponseEntity<Product> update (@PathVariable("productId") Long productId, @RequestBody Product product){  		
  		Product updatedProduct = productService.updateProduct(productId, product);
  		 if (updatedProduct != null) {
  	        return ResponseEntity.ok(updatedProduct);
  	    } else {
  	        return ResponseEntity.notFound().build();
  	    }
  	}
  	
  	/**
     * Deletes a product with the specified ID.
     *
     * @param productId The ID of the product to delete.
     * @return ResponseEntity with a Map containing the "deleted" key set to true if the product is deleted successfully,
     *         and an HTTP status of 200 OK. If the product is not found, it returns an HTTP status of 404 Not Found.
     */
  	@DeleteMapping("/{productId}")
  	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long productId) {
  	    Product product = productService.getProductById(productId);

  	    if (product != null) {
  	        productRepository.delete(product);
  	        Map<String, Boolean> response = new HashMap<>();
  	        response.put("deleted", Boolean.TRUE);
  	        return ResponseEntity.ok(response);
  	    } else {
  	        return ResponseEntity.notFound().build();
  	    }
  	}
	
}
