package com.naresh.Database.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.naresh.Database.Entity.CartItem;

import org.springframework.data.repository.query.Param;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
  public List<CartItem> findByCartCartId(int cartId);
  
  
  
  @Query(value="select* from cart_item777 where cart_id=:cartId and product_id=:productId",nativeQuery = true)
  public CartItem  getByCartIdAndProductId(@Param("cartId") int cartId,@Param("productId")String productId);
  
  public void deleteByProductId(String productId);
  
  public void deleteByCartCartId(int cartId );
  
  
  public List<CartItem> findByOrderOrderId(int orderId);
  
}
