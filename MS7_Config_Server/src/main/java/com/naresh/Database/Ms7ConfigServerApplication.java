package com.naresh.Database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class Ms7ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms7ConfigServerApplication.class, args);
	}

}
