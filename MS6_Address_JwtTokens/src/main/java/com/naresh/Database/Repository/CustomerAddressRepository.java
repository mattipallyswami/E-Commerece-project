package com.naresh.Database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

 
import org.springframework.stereotype.Repository;

import com.naresh.Database.Entity.CustomerAddress;

@Repository
 
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Integer> {
 
// 	public Product findByProductName(String productName);
	
	
	 public CustomerAddress findByCustomerName(String customerName);

}
