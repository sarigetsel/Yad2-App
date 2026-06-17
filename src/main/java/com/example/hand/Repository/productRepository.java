package com.example.hand.Repository;
import com.example.hand.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product,Integer> {
}
