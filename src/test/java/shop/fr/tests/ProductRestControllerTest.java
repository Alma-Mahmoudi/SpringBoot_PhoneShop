package shop.fr.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shop.fr.DAO.entities.Product;
import shop.fr.DAO.repositories.ProductRepository;
import shop.fr.services.ProductService;
import shop.fr.services.servicesImpl.ProductServiceImpl;
import shop.fr.controllers.ProductController;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ProductController class.
 */

@SpringBootTest
public class ProductRestControllerTest {

	// Mock the ProductService
	@MockBean
    private ProductService productService;

 // Inject the mock ProductService into the ProductController
    @InjectMocks
    private ProductController productController;
    
    @MockBean
    private ProductRepository productRepository;

    // Initialize Mockito annotations
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for the getAllProducts() method.
     */
    @Test
    public void testGetAllProducts() {
        // Mock the productService.getAllProducts() method
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", "Description 1", 10.0, 50));
        products.add(new Product(2L, "Product 2", "Description 2", 15.0, 30));
        when(productService.getAllProducts()).thenReturn(products);

        // Call the controller method
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(products);
    }

    /**
     * Test for the getProductById() method when the product is found.
     */
    @Test
    public void testGetProductById_ProductFound() {
        // Mock the productService.getProductById() method
        Product product = new Product(1L, "Product 1", "Description 1", 10.0, 50);
        when(productService.getProductById(1L)).thenReturn(product);

        // Call the controller method
        ResponseEntity<Product> responseEntity = productController.getProductById(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(product);
    }

    /**
     * Test for the getProductById() method when the product is not found.
     */
    @Test
    public void testGetProductById_ProductNotFound() {
        // Mock the productService.getProductById() method
        when(productService.getProductById(1L)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Product> responseEntity = productController.getProductById(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNull();
        assertThat("Aucun cProduct avec cette id est trouvé dans la BDD");
    }
    
    /**
     * Test for the createProduct() method.
     */
    @Test
    public void testCreateProduct() {
        // Mock the productService.createProduct() method
        Product productToCreate = new Product("New Product", "New Description", 25.0, 20);
        Product createdProduct = new Product(1L, "New Product", "New Description", 25.0, 20);
        when(productService.createProduct(productToCreate)).thenReturn(createdProduct);

        // Call the controller method
        ResponseEntity<Product> responseEntity = productController.createProduct(productToCreate);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(createdProduct);
    }

    /**
     * Test for the update() method when the product is found.
     */
    @Test
    public void testUpdateProduct_ProductFound() {
        // Mock the productService.updateProduct() method
        Product productToUpdate = new Product("Updated Product", "Updated Description", 30.0, 40);
        Product updatedProduct = new Product(1L, "Updated Product", "Updated Description", 30.0, 40);
        when(productService.updateProduct(1L, productToUpdate)).thenReturn(updatedProduct);

        // Call the controller method
        ResponseEntity<Product> responseEntity = productController.update(1L, productToUpdate);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(updatedProduct);
    }

    /**
     * Test for the update() method when the product is not found.
     */
    @Test
    public void testUpdateProduct_ProductNotFound() {
        // Mock the productService.updateProduct() method
        Product productToUpdate = new Product("Updated Product", "Updated Description", 30.0, 40);
        when(productService.updateProduct(1L, productToUpdate)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Product> responseEntity = productController.update(1L, productToUpdate);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNull();
    }

    /**
     * Test for the deleteProduct() method when the product is found.
     */
    @Test
    public void testDeleteProduct_ProductFound() {
        // Mock the productService.getProductById() and productRepository.delete() methods
        Product productToDelete = new Product(1L, "Product 1", "Description 1", 10.0, 50);
        when(productService.getProductById(1L)).thenReturn(productToDelete);

        // Call the controller method
        ResponseEntity<Map<String, Boolean>> responseEntity = productController.deleteProduct(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get("deleted")).isEqualTo(Boolean.TRUE);
    }

    /**
     * Test for the deletePorduct() method when the product is not found.
     */
    @Test
    public void testDeleteProduct_ProductNotFound() {
        // Mock the productService.getProductById() method to return null
        when(productService.getProductById(1L)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Map<String, Boolean>> responseEntity = productController.deleteProduct(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNull();
    }
}