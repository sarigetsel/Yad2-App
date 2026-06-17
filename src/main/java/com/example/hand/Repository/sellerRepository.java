package com.example.hand.Repository;

import com.example.hand.model.seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface sellerRepository extends JpaRepository<seller,Integer> {
   UserDetails findByEmail(String email);
}
