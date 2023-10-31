package com.appa.serverless;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.appa.serverless.model.Product;
import com.appa.serverless.repository.ProductRepository;

@SpringBootApplication

public class Application {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	Function<String, List<Product>> saluti() {
		return value -> this.apply(value);
	}

	public List<Product> apply(String i) {
		return productRepository.retrieveAll();
	}

}
