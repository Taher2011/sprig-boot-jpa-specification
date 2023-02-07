package com.techgen.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.techgen.model.ProductStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "product_description")
	private String description;

	private String brand;

	private String color;

	@Column(name = "product_type")
	private String type;

	private float price;

	@Column(name = "product_available")
	private boolean isAvailable;

	@Column(name = "product_purchased_date")
	@CreationTimestamp
	private LocalDateTime purchasedDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "product_status")
	private ProductStatus productStatus;

	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	private Set<Customer> customers;

	public Product(String name, String description, String brand, String color, String type, float price,
			boolean isAvailable, LocalDateTime purchasedDate, ProductStatus productStatus) {
		super();
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.color = color;
		this.type = type;
		this.price = price;
		this.isAvailable = isAvailable;
		this.purchasedDate = purchasedDate;
		this.productStatus = productStatus;
	}

	public void addProduct(Customer customer) {
		customers.add(customer);
		customer.setProduct(this);
	}

}
