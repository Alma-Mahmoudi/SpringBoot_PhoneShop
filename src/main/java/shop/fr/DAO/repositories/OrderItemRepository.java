package shop.fr.DAO.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.fr.DAO.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository <OrderItem, Long>{

}
