package shop.fr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.fr.DAO.entities.Order;
import shop.fr.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);	
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getProductById(@PathVariable("orderId") Long orderId){
		return new ResponseEntity<>(orderService.getOrderById(orderId) ,HttpStatus.OK);
	}
    
    @PostMapping
    public Order createOrder(@RequestBody List<Long> productIds) {
        return orderService.createOrder(productIds);
    }
    
    @DeleteMapping("/{orderId}/products/{productId}")
    public Order removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.removeProductFromOrder(orderId, productId);
    }
    
    //@PutMapping("/{orderId}") 
   
    
}
