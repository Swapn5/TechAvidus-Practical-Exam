package com.tech.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tech.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Query("SELECT prod FROM Product prod WHERE prod.date BETWEEN :fromDate AND :toDate")
	public List<Product> findAllByDateBetween(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

}
