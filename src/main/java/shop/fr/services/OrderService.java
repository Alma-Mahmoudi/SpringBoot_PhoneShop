package shop.fr.services;

import java.util.List;

import shop.fr.DAO.entities.Order;

public interface OrderService {

	List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    
    Order createOrder(List<Long> productIds) ;
    Order removeProductFromOrder(Long orderId, Long productId);
    //Update Order saveOrder(Order order);
    void deleteOrder(Long orderId);
}
