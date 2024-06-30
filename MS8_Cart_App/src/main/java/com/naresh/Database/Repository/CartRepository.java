package com.naresh.Database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.naresh.Database.DTO.CartDto;
import com.naresh.Database.Entity.Cart;
 
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	Cart findByCustomerName(String customerName);

}
