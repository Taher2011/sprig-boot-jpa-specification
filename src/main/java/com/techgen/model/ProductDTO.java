package com.techgen.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	private List<Product> products;
	

	@Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Product {
		
		private String name;

		private String description;

		private String brand;

		private String color;

		private String type;

		private float price;

		private boolean isAvailable;

		@CreationTimestamp
		private LocalDateTime purchasedDate;

		private String status;
    }

}
