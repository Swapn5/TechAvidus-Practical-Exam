package com.tech.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tech.exceptionHandling.NoSuchProductFoundException;
import com.tech.model.Product;
import com.tech.service.ProductService;

@RestController
@RequestMapping("/tech")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/")
	public String check() {
		return "Running fine";
	}

	// To Add single Product
	@PostMapping(value = "/addProduct", consumes = { "application/json" , "multipart/form-data"})
	public ResponseEntity<String> addProduct(@PathParam(value = "name") String name,
											@PathParam(value = "price") Double price, 
											@PathParam(value = "quantity") Integer quantity,
											@RequestPart MultipartFile pic) throws IOException {

		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setQuantity(quantity); 
		product.setDate(LocalDate.now());
		product.setPicture(pic.getBytes());
		
		service.addProduct(product);
		return new ResponseEntity<String>("Product added Succesfully", HttpStatus.CREATED);
	}
	
	
	// fetch single Product by ID
	@GetMapping(value = "/product/byId/{id}", produces = {"application/json"})
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
		Product product = service.getProduct(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	// Updating existing Product
	@PutMapping(value = "/updateProduct", consumes = { "application/json" , "multipart/form-data"})
	public ResponseEntity<String> updateProduct(@PathParam(value = "id") Integer id,
												@PathParam(value = "name") String name,
												@PathParam(value = "price") Double price, 
												@PathParam(value = "quantity") Integer quantity,
												@RequestPart MultipartFile pic) throws IOException {
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setDate(LocalDate.now());
		product.setPicture(pic.getBytes());
	
		service.addProduct(product);	
		return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
	}
	
	// To delete the product by using Id
	@DeleteMapping(value = "/delProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		service.deleteProduct(id);
		return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
	}

	// fetch Product list
	@GetMapping(value = "/productList", produces = {"application/json"})
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = service.getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	// fetching Product list and implementing pagination and sorting by price
	@GetMapping(value = "/listWithPaging", produces = {"application/json"})
	public Page<Product> getUsers(@RequestParam(defaultValue = "0") int pageNumber) {
		return service.getAllProducts(pageNumber);
	}



	// fetch Product list by name by using QBE
	@GetMapping(value = "/productsByName/{name}", produces = {"application/json"})
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
		List<Product> list = service.getProductsByName(name);
		System.out.println(list);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	// sort product list by price Low to High
	@GetMapping(value = "/priceLowToHigh", produces = {"application/json"})
	public ResponseEntity<List<Product>> sortByPriceAsc() {
		List<Product> list = service.sortByPriceAsc();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	// sort product list by price High to Low
	@GetMapping(value = "/priceHighToLow", produces = {"application/json"})
	public ResponseEntity<List<Product>> sortByPriceDsc() {
		List<Product> list = service.sortByPriceDsc();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

}
