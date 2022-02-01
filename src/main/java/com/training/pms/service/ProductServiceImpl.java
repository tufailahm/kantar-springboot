package com.training.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.model.Product;
import com.training.pms.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public String saveProduct(Product product) {
		if (product.getPrice() < 0 || product.getQuantityOnHand() < 0) {
			return "Product cannot be saved. Price or QOH cannot be negative";
		} else {
			productRepository.save(product);
			return "Product with product id :" + product.getProductId() + "  saved successfully";
		}
	}
	
	@Override
	public List<Product> getProducts() {
		return (List<Product>) productRepository.findAll();
	}
	@Override
	public String updateProduct(int productId, Product product) {
		if (product.getPrice() < 0 || product.getQuantityOnHand() < 0) {
			return "Product cannot be updated. Price or QOH cannot be negative";
		} else {
			productRepository.save(product);
			return "Product with product id :" + product.getProductId() + "  updated successfully";
		}
	}
	@Override
	public Product getProduct(int productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product.get();
	}

	@Override
	public boolean isProductExists(int productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product.isPresent();
	}

	@Override
	public String deleteProduct(int productId) {
		
		productRepository.deleteById(productId);
		return "Deletion of product with product id : "+productId+ "is successful";
	}
	
	@Override
	public List<Product> getProductByName(String productName) {
		return productRepository.findByProductName(productName);
	}

	@Override
	public List<Product> filterProductByPrice(int minimum, int maximum) {
		// TODO Auto-generated method stub
		return productRepository.findByPriceBetween(minimum, maximum);
	}

}
