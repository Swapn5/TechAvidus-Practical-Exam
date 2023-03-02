package com.tech.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@SequenceGenerator(name = "abc", sequenceName = "product_sequence",initialValue = 1001 , allocationSize = 1)
	@GeneratedValue(generator = "abc" , strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer quantity;
	
	private Double totalPrice;
	
	@NotNull
	private LocalDate date;
	
	@Lob
	@NotNull
	private byte[] picture;
}
