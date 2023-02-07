package com.techgen.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ProductDTO {

	private String name;

	private String description;

	private String brand;

	private String color;

	private String type;

	private Float price;

	private Boolean isAvailable;

	@CreationTimestamp
	private LocalDateTime purchasedDate;

	private String status;

	private List<CustomerDTO> customers;

}
