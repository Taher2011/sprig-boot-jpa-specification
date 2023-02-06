package com.techgen.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techgen.model.ProductDTO;
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
	public ResponseEntity<ProductDTO> getProducts(
			@RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sort-by", defaultValue = "name", required = false) String sortBy,
			@RequestParam(value = "sort-order", defaultValue = "asc", required = false) String sortOrder) {
		ProductDTO products = productService.getProducts(pageNumber, pageSize, sortBy, sortOrder);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/filter")
	public ResponseEntity<ProductDTO> getProductsByFilter(
			@RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sort-by", defaultValue = "name", required = false) String sortBy,
			@RequestParam(value = "sort-order", defaultValue = "asc", required = false) String sortOrder,
			@RequestBody Map<String, String> filterValues) {
		ProductDTO products = productService.getProductsByFilter(pageNumber, pageSize, sortBy, sortOrder, filterValues);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
