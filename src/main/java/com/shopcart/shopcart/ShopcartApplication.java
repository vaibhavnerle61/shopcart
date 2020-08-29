package com.shopcart.shopcart;

import com.shopcart.shopcart.storage.StorageProperties;
import com.shopcart.shopcart.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class ShopcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopcartApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService){
		return (args )-> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
}
