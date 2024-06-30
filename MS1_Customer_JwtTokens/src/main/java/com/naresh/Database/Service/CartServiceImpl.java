package com.naresh.Database.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naresh.Database.Dto.CartDto;
import com.naresh.Database.Dto.CartItemDto;
import com.naresh.Database.feignClient.FeignClientConsumer;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	FeignClientConsumer feignClientConsumer;
	
	
	@Override
	public String createCart(CartDto cartDto,String token) {
 
		
		
		System.out.println(cartDto.getCartItemDto());
	
		String msg=feignClientConsumer.addCart(cartDto, token).getBody();
		
		
		
		
		return msg;
	}

 

	@Override
	public String addSingleProductToCart(CartItemDto cartItemDto, String token) {


		
		return feignClientConsumer.addingProductToCart(cartItemDto, token).getBody();
		
	}


	@Override
	public String addingProductsToCart(List<CartItemDto> cartItemDto, String token) {


		
		return feignClientConsumer.addProductsToCart(cartItemDto, token).getBody();
		
		
	}



	@Override
	public CartDto getMyCartDetails(String token) {


		return feignClientConsumer.getMyCart(token).getBody();
		
	}



	@Override
	public List<CartItemDto> getAllMyCartProducts(String token) {


		return feignClientConsumer.getMyAllCartItems(token).getBody();	
		
		}



	@Override
	public String removeProductFromCart(String productId,String token) {

          	return feignClientConsumer.deleteProductFromCart(productId,token).getBody();

	 }


		@Override
		public String removeAllProductsFromCart(String token) {

			return feignClientConsumer.deleteAllProductsFromCart(token).getBody();

		}

	 
	
	
	

}
