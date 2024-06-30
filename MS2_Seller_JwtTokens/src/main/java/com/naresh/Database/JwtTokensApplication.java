package com.naresh.Database;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
 

@EnableFeignClients(basePackages ="com.naresh.Database.FiegnClient")
@EnableDiscoveryClient
@SpringBootApplication
public class JwtTokensApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokensApplication.class, args);
	}

}
