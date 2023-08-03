package shop.fr.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shop.fr.DAO.entities.Order;
import shop.fr.DAO.repositories.OrderRepository;
import shop.fr.services.OrderService;
import shop.fr.services.servicesImpl.OrderServiceImpl;
import shop.fr.controllers.OrderController;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the OrderController class.
 */

@SpringBootTest
public class OrderRestControllerTest {

	// Mock the OrderService
    @Mock
    private OrderServiceImpl orderService;

 // Inject the mock OrderService into the OrderController
    @InjectMocks
    private OrderController orderController;

    @MockBean
    private OrderRepository orderRepository ; 
    
    // Initialize Mockito annotations
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for the getAllOrders() method.
     */
    @Test
    public void testGetAllOrders() {
        // Mock the orderService.getAllOrders() method
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, new Date(), 100.0, "STATUS1", "Customer1", "customer1@example.com", new ArrayList<>()));
        orders.add(new Order(2L, new Date(), 200.0, "STATUS2", "Customer2", "customer2@example.com", new ArrayList<>()));
        when(orderService.getAllOrders()).thenReturn(orders);

        // Call the controller method
        ResponseEntity<List<Order>> responseEntity = orderController.getAllOrders();

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(orders);
    }

    /**
     * Test for the getOrderById() method when the order is found.
     */
    @Test
    public void testGetOrderById_OrderFound() {
        // Mock the orderService.getOrderById() method
        Order order = new Order(1L, new Date(), 100.0, "STATUS1", "Customer1", "customer1@example.com", new ArrayList<>());
        when(orderService.getOrderById(1L)).thenReturn(order);

        // Call the controller method
        ResponseEntity<Order> responseEntity = orderController.getOrderById(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(order);
    }
 
    /**
     * Test for the getOrderById() method when the order is not found.
     *  Que faire si le produit demandé n'existe pas ? 
     */
    @Test
    public void testGetOrderById_OrderNotFound() {
        // Mock the orderService.getOrderById() method
        when(orderService.getOrderById(1L)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Order> responseEntity = orderController.getOrderById(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNull();
    }

    /**
     * Test for the createOrder() method.
     */
    @Test
    public void testCreateOrder() {
        // Mock the orderService.createOrder() method
        Order orderToCreate = new Order(2l,new Date(), 100.0, "STATUS1", "Customer1", "customer1@example.com", new ArrayList<>());
        Order createdOrder = new Order(1L, new Date(), 100.0, "STATUS1", "Customer1", "customer1@example.com", new ArrayList<>());
        when(orderService.createOrder(orderToCreate)).thenReturn(createdOrder);

        // Call the controller method
        Order returnedOrder = orderController.createOrder(orderToCreate);

        // Verify the returned order
        assertThat(returnedOrder).isEqualTo(createdOrder);
    }

    /**
     * Test for the deletOrder() method when the order is found.
     */
    @Test
    public void testDeleteOrder_OrderFound() {
        // Mock the orderService.getOrderById() and orderRepository.delete() methods
        Order orderToDelete = new Order(1L, new Date(), 100.0, "STATUS1", "Customer1", "customer1@example.com", new ArrayList<>());
        when(orderService.getOrderById(1L)).thenReturn(orderToDelete);

        // Call the controller method
        ResponseEntity<Map<String, Boolean>> responseEntity = orderController.deleteOrder(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get("deleted")).isEqualTo(Boolean.TRUE);
    }
    
    /**
     * Test for the deletOrder() method when the order is not found.
	 * ue faire si la commande contient des produits non présents en base de données ?
	 */
    @Test
    public void testDeleteOrder_OrderNotFound() {
        // Mock the orderService.getOrderById() method
        when(orderService.getOrderById(1L)).thenReturn(null);

        // Call the controller method
        ResponseEntity<Map<String, Boolean>> responseEntity = orderController.deleteOrder(1L);

        // Verify the response
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNull();
    }

}