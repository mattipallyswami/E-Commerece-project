package com.naresh.Database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class Ms5GatewayApppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms5GatewayApppApplication.class, args);
	}

}
