package com.naresh.Database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naresh.Database.Entity.Order;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	
	public List<Order> findByCustomerName(String customerName);
	
	 
	public List<Order> findByOrderDateAndCustomerName(LocalDate date,String customerName);

}
