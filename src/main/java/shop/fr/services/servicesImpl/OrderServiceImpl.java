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

/**
 * OrderServiceImpl is an implementation of the OrderService interface.
 * It provides methods to manage orders and their associated products in the system.
 */

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
    private final OrderRepository orderRepository;
	@Autowired
    private final ProductRepository productRepository;


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
	public Order createOrder(Order order) {
        order.setOrderDate(new Date());   // Utiliser la date actuelle
        order.updateTotalAmount();        // Mettre à jour le totalAmount
        order.setStatus("En attente");
        return orderRepository.save(order);
    }
	
	@Override
	public Order updateOrder(Long orderId, Order newOrder) {
		Order oldOrder = getOrderById(orderId);
		updateOrderFields(oldOrder,newOrder);
		return orderRepository.save(oldOrder);
	}
	
	private void updateOrderFields(Order oldOrder, Order newOrder) {
		oldOrder.setCustomerEmail(newOrder.getCustomerEmail());
		oldOrder.setCustomerName(newOrder.getCustomerName());
		oldOrder.setStatus(newOrder.getStatus());
		oldOrder.updateTotalAmount();
		oldOrder.setOrderDate(new Date());
	}	
	
	@Override
    public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
    }

	//-----------------------------------------
	@Override
	public Order addProductToOrder(Long orderId,Long productId) {
		
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new NoSuchElementException("La commande spécifiée est introuvable :" + productId));

		Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));
	  
		order.getProducts().add(product);
	    order.updateTotalAmount();      // Mettre à jour le totalAmount
	    order.setOrderDate(new Date()); // Utiliser la date actuelle
	    order.setStatus("En attente");
	    return orderRepository.save(order);
	}
	@Override
	public Order removeProductFromOrder(Long orderId, Long productId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new NoSuchElementException("La commande spécifiée est introuvable :" + productId));

		Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));
       
		if (!order.getProducts().contains(product)) {
            throw new IllegalArgumentException("Le produit n'appartient pas à la commande.");
        }

        order.getProducts().remove(product);
        double NewTotalAmount =  order.getTotalAmount() - product.getPrice();
        order.setTotalAmount(NewTotalAmount);;
        return orderRepository.save(order);
    }

	
	
}
