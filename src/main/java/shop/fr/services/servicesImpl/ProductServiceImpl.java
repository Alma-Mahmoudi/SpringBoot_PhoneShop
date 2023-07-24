package shop.fr.services.servicesImpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.fr.DAO.entities.Product;
import shop.fr.DAO.repositories.ProductRepository;
import shop.fr.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
    private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository ;
	}
	@Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
	@Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));
    }
	@Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }
	@Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

	@Override
	public Product updateProduct(Long productId,Product product) {
		Product OldProduct =  getProductById(productId);
		
		OldProduct.setProductName(product.getProductName());
		OldProduct.setDescription(product.getDescription());
		OldProduct.setPrice(product.getPrice());
		OldProduct.setQuantityAvailable(product.getQuantityAvailable());
		
		return productRepository.save(OldProduct);
	
	}
	
}
