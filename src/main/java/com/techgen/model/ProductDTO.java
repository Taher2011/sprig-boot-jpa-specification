package com.techgen.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("product_name")
	private String name;

	@JsonProperty("product_description")
	private String description;

	@JsonProperty("product_brand")
	private String brand;

	@JsonProperty("product_color")
	private String color;

	@JsonProperty("product_type")
	private String type;

	@JsonProperty("product_price")
	private Float price;

	@JsonProperty("product_isAvailable")
	private Boolean isAvailable;

	@JsonProperty("product_purchased_date")
	@CreationTimestamp
	private LocalDateTime purchasedDate;

	@JsonProperty("product_status")
	private String status;

	@JsonProperty("customers")
	private List<CustomerDTO> customers;

}
