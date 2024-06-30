package com.naresh.Database.Repostory;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naresh.Database.entity.Seller;



@Repository
public interface SellerRepository extends JpaRepository<Seller, String>{
	
	
	Optional<Seller> findBysellerName(String sellerName);// pk
	Optional<Seller> findBysellerId(String sellerId);
	
 
}
