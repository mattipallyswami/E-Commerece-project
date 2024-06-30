package com.naresh.Database.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.naresh.Database.Entity.Product;


@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product,String> {
	
	
//    @Query(value="select * from product777 where product_name='?1 ",nativeQuery = true)
 
 	public Product findByProductName(String productName);

}
