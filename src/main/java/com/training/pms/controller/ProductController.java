package com.training.pms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.model.Product;
import com.training.pms.service.ProductService;

@RestController
@RequestMapping("product") // localhost:9090/product
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping // localhost:9090/product/ -POST
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		String message = null;
		// use case : if product already there , then different status codes to sent

		if (productService.isProductExists(product.getProductId())) {
			responseEntity = new ResponseEntity<String>(
					"Product with product id :" + product.getProductId() + " already exists", HttpStatus.CONFLICT); // 409
		} else {
			// code to save the product
			message = productService.saveProduct(product);
			if (message.equals("Product cannot be saved. Price or QOH cannot be negative")) {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_ACCEPTABLE); // 406
			} else {
				// 201 - saved/created
				responseEntity = new ResponseEntity<String>(message, HttpStatus.OK); // 201
			}
		}
		return responseEntity;
	}

	@PutMapping("/{productId}") // localhost:9090/product/78 -PUT
	public ResponseEntity<String> updateProduct(@PathVariable("productId") int productId,
			@RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		String message = null;
		// use case : if product already there , then update and send different status
		// code

		if (productService.isProductExists(productId)) {
			message = productService.updateProduct(productId, product);

			if (message.equals("Product cannot be updated. Price or QOH cannot be negative")) {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED); // 304
			} else { // update successful
				responseEntity = new ResponseEntity<String>(message, HttpStatus.OK); // 409
			}

		} else {
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_FOUND); // 404
		}
		return responseEntity;
	}

	@GetMapping 
	// localhost:9090/product -GET
	//localhost:9090/product?productName=Mouse
	public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String productName) {
		System.out.println("Getting all the products");
		List<Product> products = new ArrayList<Product>();
		ResponseEntity<List<Product>> responseEntity = null;

		if (productName == null) {
			products = productService.getProducts();
			if (products.size() == 0) {
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 204
			}
			{
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 204
			}
		} else {
			products = productService.getProductByName(productName);
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 204

		}

		return responseEntity;
	}

	@GetMapping("/{productId}") // localhost:9090/product/78 -GET
	public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {

		System.out.println("Getting a single product with product id : " + productId);
		ResponseEntity<Product> responseEntity = null;
		Product product = new Product();
		String message = null;
		// use case : if product already there , then different status codes to sent

		if (productService.isProductExists(productId)) {
			product = productService.getProduct(productId);
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK); // 200
		} else {
			// code to save the product
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); // 204
		}
		return responseEntity;
	}

	@DeleteMapping("/{productId}") // localhost:9090/product/78 -DELETE
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId) {
		System.out.println("Deleting a single product with product id : " + productId);
		ResponseEntity<String> responseEntity = null;
		Product product = new Product();
		String message = null;
		// use case : if product already there , then different status codes to sent

		if (productService.isProductExists(productId)) {
			message = productService.deleteProduct(productId);
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK); // 200
		} else {
			// code to save the product
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT); // 204
		}
		return responseEntity;
	}

	// find by productName
	// localhost:9090/product/getProductByName/Lakme -GET
	// localhost:9090/product/getProductByName/Aroma -GET
	// Getting all the products with the name Aroma
	// [10:09] Vinay, Rangaraju (MB)
	// FindbyProductName
	@GetMapping("/getProductByName/{productName}")
	public ResponseEntity<List<Product>> findByName(@PathVariable("productName") String productName) {
		System.out.println("getting product based on name :" + productName);
		ResponseEntity<List<Product>> responseEntity = null;
		List<Product> products = productService.getProductByName(productName);
		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 204
		} else {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 204
		}
		return responseEntity;
	}

	// filter by price
	// localhost:9090/product/filterProductByPrice/100/200 -GET
	// Getting all the products between 100 and 200
	// [10:13] M, Dharani (MB)
	@GetMapping("/filterProductByPrice/{minimum}/{maximum}")
	public ResponseEntity<List<Product>> filterByname(@PathVariable("minimum") int minimum,
			@PathVariable("maximum") int maximum) {
		System.out.println("getting product based on price :" + minimum + " and maximum :" + maximum);
		ResponseEntity<List<Product>> responseEntity = null;
		List<Product> products = productService.filterProductByPrice(minimum, maximum);
		System.out.println("Products size :" + products.size());
		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 204
		} else {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 204
		}
		return responseEntity;
	}

}
