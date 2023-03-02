package com.tech.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tech.model.Product;

public interface ProductService {

	void addProduct(Product product);

	Product getProduct(Integer id);

	void deleteProduct(Integer id);

	List<Product> getAllProducts();
	
	Page<Product> getAllProducts(int pageNumber);

	List<Product> getProductsByName(String name);
	
	List<Product> sortByPriceAsc();

	List<Product> sortByPriceDsc();


}
