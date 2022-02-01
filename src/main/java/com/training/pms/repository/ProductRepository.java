package com.training.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.training.pms.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
		//keywords
		//findBy
		public List<Product> findByProductName(String productName);
		
		//hands on 
		//custom method for filter minimum and maximum by price 
		public List<Product> findByPriceBetween(int min, int max);
		
		//@Query(value = )
		//public List<Product> findByPriceBetween(int min, int max);
	
}
