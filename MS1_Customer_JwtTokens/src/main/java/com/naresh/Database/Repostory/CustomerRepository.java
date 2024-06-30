package com.naresh.Database.Repostory;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naresh.Database.entity.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
	
	
	Optional<Customer> findByCustomerName(String customerName);
	Optional<Customer> findByCustomerId(String customerId);
	
	
	

}
