package com.naresh.Database;





import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.naresh.Database.Service.CartServiceImpl;


@EnableDiscoveryClient
@ComponentScan(basePackages = "com.naresh.Database")
@EnableFeignClients(basePackages= "com.naresh.Database.feignClient")
@SpringBootApplication
public class Ms8CartAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(Ms8CartAppApplication.class, args);
		
		CartServiceImpl cartServiceImpl	=context.getBean(CartServiceImpl.class);
		
	
		
		 
	}

}
