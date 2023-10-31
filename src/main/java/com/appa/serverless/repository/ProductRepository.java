package com.appa.serverless.repository;

import java.util.List;

import com.appa.serverless.model.Product;

public interface ProductRepository {
	
	public List<Product> retrieveAll();
}
