package com.tech.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tech.exceptionHandling.NoSuchProductFoundException;
import com.tech.model.Product;
import com.tech.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo rep;

	@Override
	public void addProduct(Product product) {
		product.setTotalPrice(product.getPrice() * product.getQuantity());
		rep.save(product);
	}

	@Override
	public Product getProduct(Integer id) {
		
		if(rep.findById(id).isPresent())
			return rep.findById(id).get();
		else
			throw new NoSuchProductFoundException("Product with given Id is not present");
	}

	@Override
	public void deleteProduct(Integer id) {
		rep.deleteById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return rep.findAll();
	}

	@Override
	public Page<Product> getAllProducts(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("price").ascending());
		return rep.findAll(pageable);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		Product pr = new Product();
		pr.setName(name);
		Example<Product> example = Example.of(pr);
		return rep.findAll(example);
	}

	@Override
	public List<Product> sortByPriceAsc() {
		return rep.findAll(Sort.by("price").ascending());
	}

	@Override
	public List<Product> sortByPriceDsc() {
		return rep.findAll(Sort.by("price").descending());
	}



}
