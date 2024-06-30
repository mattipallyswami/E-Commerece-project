package com.naresh.Database.Service;
import com.naresh.Database.CustomException.*;
import com.naresh.Database.Entity.*;
import com.naresh.Database.DTO.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream ;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naresh.Database.Entity.Cart;
import com.naresh.Database.Entity.CartItem;
import com.naresh.Database.Mapper.ConverterImpl;
import com.naresh.Database.Repository.*;

import com.naresh.Database.Token.*;
import com.naresh.Database.feignClient.ProductFeignClientsConsumer;

import jakarta.transaction.Transactional;
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	JwtToken jwtToken;
	
	@Autowired
	ProductFeignClientsConsumer feignClientsConsume;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	ConverterImpl converterImpl=new ConverterImpl();
	
	@Override
	public String createYourCart(CartDto cartDto,String token) {
	 
		Cart existedcustomer=cartRepository.findByCustomerName(jwtToken.getCustomerNameOfToken(token));
		 
		
//		if(existedcustomer.getCustomerName().equals(jwtToken.getCustomerNameOfToken(token)))
//		{
//			throw new DuplicateCartNotAllowed("duplicate cart IS not allowed", new Throwable("cart already found for this customer"));
//
//		}
//		
		if(existedcustomer!=null)
		{
			throw new CartAlreadyExistForThisCustomer("cart already exist for this customer", new Throwable("cart already found for this customer"));
		}
		
		Cart cart=converterImpl.cartDtoToCart(cartDto);
		
		cart.setCustomerName(jwtToken.getCustomerNameOfToken(token));
		
		// setting cartId for cartitems
		
		if(cart.getCartItems()!=null&&!cart.getCartItems().isEmpty())
		{
		List<CartItem> cartItems=cart.getCartItems();
		
		cartItems.stream().forEach(item->item.setCart(cart));
		}
	 	
		
		cartRepository.save(cart);
	 
		
		return " your cart created suceessfully";
	
	}

	@Override
	public long  getProductPrice(String pid) {
 	
		return feignClientsConsume.getPrice(pid).getBody();

	 	
	}

	@Override
	public double cartTotalValue(int cartId) {

    // with cartid retrive all cartItems from cartItem table and getPrices of all products of cartItem and add them
		
		
		List<CartItem> cartItems=cartItemRepository.findByCartCartId(cartId);// cartItems--> list
		
		double totalCartValue=cartItems.stream().mapToDouble(item->(feignClientsConsume.getPrice(item.getProductId()).getBody())*item.getCartItemQuantity()).sum();

			
//		 cartItems.stream().forEach(item->
//                        {
//                        	total= ((feignClientsConsume.getPrice(item.getProductId()).getBody())*item.getCartItemQuantity());
//                			
//                  		  total+=price;
//                  		  
//                        });		
		 
		 
//		 
//		long price=0;
//	 
//		long total=0;
//		
//		  for(CartItem item:cartItems)
//		  {
//			  
//		  total= ((feignClientsConsume.getPrice(item.getProductId()).getBody())*item.getCartItemQuantity());
//			
//		  total+=price;
//		  
		//  }
		 
		
//	    cartItems.stream().map(item->
//		{
//			long amount=0; 
//		 
//			amount= (feignClientsConsume.getPrice(item.getProductId()).getBody())*item.getCartItemQuantity()+price;
//			 
//			
//			return amount;
//		});
//		
	 	
		  
		  return totalCartValue;
		 
	}

	 
	@Override
	public List<CartItemDto> getcartProducts(String token)
	{
		
		
		
		  Cart yourCart=cartRepository.findByCustomerName(jwtToken.getCustomerNameOfToken(token));// it will also find cartItems beacuse of @onetoMnay(cascade)  we are not used @jsonignore on List<CartItem> cartItems in cartEntity
		
		  // but cartItemRepo.findById will not find cartDetails(i.e Cart property in cartItem Entity) because we are using @jsonIgnore to break loop b.w two entites. so it is not going to "Cart cart" property in cartItem eNTITY. it will ignore
		  //List<CartItem> cartItems= cartItemRepository.findByCartCartId(yourCart.getCartId());
		
		 return  converterImpl.cartItemListToCartItemDtoList(yourCart.getCartItems());
		
//     	Cart cart=cartRepository.findById(cartId).get();
//		return cart.getCartItems();
		 
	}
	
	@Override
	public CartDto getMyCart(String token) 
	{
	
		Cart cart=cartRepository.findByCustomerName(jwtToken.getCustomerNameOfToken(token));

		
		cart.setCartTotal(cartTotalValue(cart.getCartId()));//  cartTotalValue is method that gives totalCartValue
		
		
		CartDto cartDto=converterImpl.cartToCartDto(cart);
		
        return cartDto;
	}


	@Override
	public String addProductToCart(CartItemDto cartItemDto,String token) {
		
		
		
		 Cart existingCustomerRecord=cartRepository.findByCustomerName(jwtToken.getCustomerNameOfToken(token));

      
		     if(existingCustomerRecord.getCartId()==0)
		    {
		    throw new CartIdNotFound("this cartId not existed",new Throwable("please first create cartId and add products"));
		    }
		 
		    
		    CartItem cartItem=converterImpl.cartItemDtoToCartItem(cartItemDto);
			
		    // internally adding to cartItem and common column cartId(fk) 
	         // Cart cart=new Cart();
	          Cart cart=cartRepository.findById(existingCustomerRecord.getCartId()).get();
	          
	          cartItem.setCart(cart);
	          
	          
	          
	         Order order= orderRepository.findById(7777).get();
	          
	         
	         cartItem.setOrder(order);
	      
	         System.out.println(cartItem.getCart());
	         System.out.println(cartItem.getCart().getCartId());
	         System.out.println(cartItem.getOrder());
	         System.out.println(cartItem.getOrder().getOrderId());
	         System.out.println(cartItem.getProductId());

	         
	        // cartItemRepository.save(cartItem); 
	          
	// making to not add same to cart Again
		    
	  List<CartItem> cartItems= cartItemRepository.findByCartCartId(existingCustomerRecord.getCartId());
		    
  
	  boolean isProductExistsInCart=cartItems.stream().anyMatch(item->item.getProductId().equals(cartItem.getProductId()));
	    
		 
			  if(isProductExistsInCart)
			  {
				  throw new ThisProductAlreadyExistedInTheCart("this product already added to your cart with id"+cartItem.getProductId()
				  ,new Throwable(" this product already exists in your cart")); 
			  }
			  else
			  {
				  cartItemRepository.save(cartItem); 
		  }
		 
	  	return "product added successfully to cart";
	}
	@Override
	public String addProductsToCart(List<CartItemDto> cartItemDto,String token) {

      // internally adding to cartItem and common column cartId(fk) 
		//cartRepository.findById(cartId).orElseThrow(()->new CartIdNotFound("this cartId not existed"+cartId,new Throwable("please first create cartId and add products")));

		  Cart existedCart=cartRepository.findByCustomerName(jwtToken.getCustomerNameOfToken(token));
		  
		  
		  
		    if(existedCart.getCartId()==0)
		    {
		    throw new CartIdNotFound("this cartId not existed",new Throwable("please first create cartId and add products"));
		    }
		  
		List<CartItem> cartItems=converterImpl.cartItemDtoListToCartItemList(cartItemDto);
		
		
//		          Cart cart=new Cart();
//		          cart.setCartId(cartId);
		          
		      // setting carId(FK) for all cartItems
		
		System.out.println(existedCart);
		          
		cartItems.stream().forEach(item->item.setCart(existedCart));   
		
		System.out.println(cartItems);
	      
	    List<CartItem> existedCartItems=cartItemRepository.findByCartCartId(existedCart.getCartId());
	        // making list of existed cartItems with only productIds(id1,id2,id3). --> it doent conatins objects
	        
	        List<String> existedCartItemsProductIds =existedCartItems.stream().map(CartItem::getProductId).collect(Collectors.toList());
	        
	        
	        cartItems.stream().forEach(item->
	        {
	        	if(existedCartItemsProductIds.contains(item.getProductId()))
	        	{
	        		throw new ThisProductAlreadyExistedInTheCart("this product already added to your cart with id"+item.getProductId()
					  ,new Throwable(" this product already exists in your cart")); 
	        	}
	        	else
	        	{
	        		cartItemRepository.save(item);
	        		
	        		System.out.println("product saved with "+item.getProductId());
	        		
	        	}
	        	
	        });
	 	
	  	return " All selected products added successfully to cart";
	}
    @Transactional
	@Override
	public String removeProductFromCart(String productId,String token) {

    	
    	     Cart Yourcart=cartRepository.findByCustomerName(jwtToken.getCustomerNameOfToken(token));
    	 
    	    // get cart items of this custmername
    	 
    	      List<CartItem> cartItems= cartItemRepository.findByCartCartId(Yourcart.getCartId());// or yourCart.getCartItems();
    	
    	      System.out.println(cartItems);
    	      System.out.println(productId);
    	      
    	    boolean isProductAvailabaleInCart=  cartItems.stream().anyMatch(item->item.getProductId().equals(productId));
    	    
    	    
    	      
    	    if(isProductAvailabaleInCart)
    	     {
 
		     cartItemRepository.deleteByProductId(productId);
		     
		     return "selected product deleted from cart sucessfully";
		
    	    }
		  return "product not available in your cart with id "+productId+"please add product to your cart ";
		
	 	}
    @Transactional
	@Override
	public String clearCart(String token) {

 
		
		 Cart existingCart= cartRepository.findByCustomerName(jwtToken.getCustomerNameOfToken(token));
		 
		 if(existingCart==null)
		 {
			 throw new CartNotExisted("cart not existed for this customer",new Throwable("please create a cart first"));
		 }
		
		 cartItemRepository.deleteByCartCartId(existingCart.getCartId());
		
		
		return "caa cleared successfully";
	}

}
