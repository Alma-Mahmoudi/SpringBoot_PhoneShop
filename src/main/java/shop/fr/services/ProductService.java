package shop.fr.services;

import java.util.List;

import shop.fr.DAO.entities.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(Product product);   
    Product updateProduct(Long productId,Product product);
    void deleteProduct(Long productId);
    
}
