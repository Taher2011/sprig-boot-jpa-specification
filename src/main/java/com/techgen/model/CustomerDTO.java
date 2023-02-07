package com.techgen.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CustomerDTO {

	@JsonProperty("customer_name")
	private String customerName;

	@JsonProperty("customer_id")
	private String customerId;

	@JsonProperty("customer_type")
	private String customerType;

	@JsonProperty("product")
	private ProductDTO productDTO;

}
