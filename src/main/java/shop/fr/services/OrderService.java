package shop.fr.services;

import java.util.List;

import shop.fr.DAO.entities.Order;

public interface OrderService {

	List<Order> getAllOrders();
    Order getOrderById(Long orderId);
    Order createOrder(Order order);

    Order SaveOrder(Order order);
    void DeleteOrder(Long orderId);
}
