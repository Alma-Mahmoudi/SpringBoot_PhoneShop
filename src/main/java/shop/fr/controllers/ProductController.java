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

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductController(ProductService productService) {
		this.productService = productService ; 
	}
	
	@GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);	
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
		return new ResponseEntity<>(productService.getProductById(productId) ,HttpStatus.OK);
	}

    @PostMapping
    public ResponseEntity<Product> createSujet(@RequestBody Product product) {
		return ResponseEntity.ok().body(productService.createProduct(product));
    }

  //update sujet by id 
  	@PutMapping("/{productId}")
  	public ResponseEntity<Product> update (@PathVariable("productId") Long productId, @RequestBody Product product){  		
  		return ResponseEntity.ok(productService.updateProduct(productId, product));
  	}
  	
    @DeleteMapping("/{productId}")
	public ResponseEntity<Map<String , Boolean>> deleteSujet(@PathVariable Long productId){
		Product product=productService.getProductById(productId);			
		productRepository.delete(product);
		Map<String ,Boolean> response =new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		}
	
}
