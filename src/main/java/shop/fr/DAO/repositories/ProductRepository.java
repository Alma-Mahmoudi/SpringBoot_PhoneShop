package shop.fr.DAO.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shop.fr.DAO.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

}
