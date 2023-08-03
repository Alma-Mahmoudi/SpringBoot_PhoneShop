package shop.fr.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.fr.DAO.entities.Order;
import shop.fr.DAO.repositories.OrderRepository;
import shop.fr.services.OrderService;

/**
 * OrderController is a RESTful API controller that handles HTTP requests related to orders.
 * It provides endpoints for retrieving, creating, updating, deleting orders ,add or delete product from Order.
 * The base path for all Order-related requests is "/api/orders". 
 * http://localhost:4666/api/orders
 */

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    /**
     * Retrieves a list of all orders.
     *
     * @return A ResponseEntity containing a list of Order objects representing all available orders.
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
    	List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves an order with the specified ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return A ResponseEntity containing the Order object corresponding to the given order ID, or a NOT_FOUND status if the order is not found.
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId){
        Order order = orderService.getOrderById(orderId);
        
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Creates a new order.
     *
     * @param order The Order object to be created.
     * @return The newly created Order object.
     */
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
    
    /**
     * Deletes an order with the specified ID.
     *
     * @param orderId The ID of the order to delete.
     * @return A ResponseEntity containing a map indicating that the order was successfully deleted, or a NOT_FOUND status if the order is not found.
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);

        if (order != null) {
            orderRepository.delete(order);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //-------------------------
    
    /**
     * Adds a product to an existing order.
     *
     * @param orderId   The ID of the order to which the product will be added.
     * @param productId The ID of the product to be added to the order.
     * @return The updated Order object after adding the product.
     */
    @PutMapping("/{orderId}/product/{productId}")
    public Order addProductToOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.addProductToOrder(orderId, productId);
    }
    
    /**
     * Removes a product from an existing order.
     *
     * @param orderId   The ID of the order from which the product will be removed.
     * @param productId The ID of the product to be removed from the order.
     * @return The updated Order object after removing the product.
     */
    @DeleteMapping("/{orderId}/product/{productId}")
    public Order removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.removeProductFromOrder(orderId, productId);
    }

    
}
