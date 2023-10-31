package com.appa.serverless.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.appa.serverless.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private static final java.util.logging.Logger log = java.util.logging.Logger
			.getLogger(ProductRepositoryImpl.class.getName());

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	@Override
	public List<Product> retrieveAll() {

		log.info("Retrieve all products");

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		return dynamoDBMapper.scan(Product.class, scanExpression);
	}

}
