package shop.fr.services.servicesImpl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.fr.DAO.entities.Order;
import shop.fr.DAO.entities.Product;
import shop.fr.DAO.repositories.OrderRepository;
import shop.fr.DAO.repositories.ProductRepository;
import shop.fr.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
    private OrderRepository orderRepository;
	@Autowired
    private ProductRepository productRepository;


	public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
	
	@Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
	@Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + orderId));
    }
	
	@Override
    public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
    }
	
	public Order createOrder(List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Les produits spécifiés sont introuvables.");
        }

        Order order = new Order();
        order.setProducts(products);
        order.setOrderDate(new Date()); // Utiliser la date actuelle
        order.setStatus("En attente");
       
        return orderRepository.save(order);
    }
	
	public Order removeProductFromOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("La commande spécifiée est introuvable.");
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Le produit spécifié est introuvable.");
        }

        if (!order.getProducts().contains(product)) {
            throw new IllegalArgumentException("Le produit n'appartient pas à la commande.");
        }

        order.getProducts().remove(product);
        return orderRepository.save(order);
    }

	//Update
	
	
}
