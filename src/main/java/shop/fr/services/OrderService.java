package shop.fr.services;

import java.util.List;

import shop.fr.DAO.entities.Order;

/**
 * OrderService interface provides methods for managing orders.
 */
public interface OrderService {

	/**
     * Retrieves a list of all orders.
     *
     * @return A list of Order objects representing all available orders.
     */
    List<Order> getAllOrders();

    /**
     * Retrieves an order with the specified ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The Order object corresponding to the given order ID, or null if not found.
     */
    Order getOrderById(Long orderId);

    /**
     * Creates a new order.
     *
     * @param order The Order object to be created.
     * @return The newly created Order object.
     */
    Order createOrder(Order order);

    /**
     * Updates an existing order with the specified ID.
     *
     * @param orderId The ID of the order to update.
     * @param order   The updated Order object.
     * @return The updated Order object.
     */
    Order updateOrder(Long orderId, Order order);

    /**
     * Deletes an order with the specified ID.
     *
     * @param orderId The ID of the order to delete.
     */
    void deleteOrder(Long orderId);

    /**
     * Adds a product to an existing order.
     *
     * @param orderId   The ID of the order to which the product will be added.
     * @param productId The ID of the product to add to the order.
     * @return The updated Order object after adding the product.
     */
    Order addProductToOrder(Long orderId, Long productId);

    /**
     * Removes a product from an existing order.
     *
     * @param orderId   The ID of the order from which the product will be removed.
     * @param productId The ID of the product to remove from the order.
     * @return The updated Order object after removing the product.
     */
    Order removeProductFromOrder(Long orderId, Long productId);
}
