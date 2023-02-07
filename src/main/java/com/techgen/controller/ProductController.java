package com.techgen.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techgen.model.CustomerDTO;
import com.techgen.model.ProductDTO;
import com.techgen.model.ResponseDTO;
import com.techgen.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/")
	public ResponseEntity<ResponseDTO<List<ProductDTO>>> getProducts(
			@RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sort-by", defaultValue = "name", required = false) String sortBy,
			@RequestParam(value = "sort-order", defaultValue = "asc", required = false) String sortOrder) {
		ResponseDTO<List<ProductDTO>> products = productService.getProducts(pageNumber, pageSize, sortBy, sortOrder);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/filter")
	public ResponseEntity<ResponseDTO<List<ProductDTO>>> getProductsByFilter(
			@RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sort-by", defaultValue = "name", required = false) String sortBy,
			@RequestParam(value = "sort-order", defaultValue = "asc", required = false) String sortOrder,
			@RequestBody Map<String, String> filterValues) {
		ResponseDTO<List<ProductDTO>> products = productService.getProductsByFilter(pageNumber, pageSize, sortBy,
				sortOrder, filterValues);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{product-id}")
	public ResponseEntity<ResponseDTO<List<CustomerDTO>>> getCustomersAssociatedWithProduct(
			@RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
			@PathVariable(name = "product-id") long productId) {
		ResponseDTO<List<CustomerDTO>> customers = productService.getCustomersAssociatedWithProduct(pageNumber,
				pageSize, productId);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@PostMapping("/customer")
	public ResponseEntity<ResponseDTO<List<CustomerDTO>>> getCustomerJoinProduct(
			@RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
			@RequestBody Map<String, String> filterValues) {
		ResponseDTO<List<CustomerDTO>> customersProduct = productService.getCustomerJoinProduct(pageNumber, pageSize, filterValues);
		return new ResponseEntity<>(customersProduct, HttpStatus.OK);
	}
	
	@PostMapping("/customer/join")
	public ResponseEntity<ResponseDTO<List<ProductDTO>>> getProductJoinCustomer(
			@RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
			@RequestBody Map<String, String> filterValues) {
		ResponseDTO<List<ProductDTO>> productCustomers = productService.getProductJoinCustomer(pageNumber, pageSize, filterValues);
		return new ResponseEntity<>(productCustomers, HttpStatus.OK);
	}
}
