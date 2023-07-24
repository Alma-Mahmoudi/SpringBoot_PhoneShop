package shop.fr.DAO.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.fr.DAO.entities.Order;


@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {

}
