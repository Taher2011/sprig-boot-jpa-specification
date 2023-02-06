package com.techgen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.techgen.entity.Product;
import com.techgen.model.ProductDTO;
import com.techgen.repository.ProductRepository;
import com.techgen.specification.ProductSpecification;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public ProductDTO getProducts(int pageNo, int pageSize, String sortBy, String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Product> pageProducts = productRepository.findAll(pageable);
		ProductDTO productDTO = createProductDto(pageProducts.get().toList());
		productDTO.setPageNo(pageProducts.getNumber());
		productDTO.setPageSize(pageProducts.getSize());
		productDTO.setTotalElements(pageProducts.getTotalElements());
		productDTO.setTotalPages(pageProducts.getTotalPages());
		productDTO.setLast(pageProducts.isLast());
		return productDTO;
	}

	public ProductDTO getProductsByFilter(int pageNo, int pageSize, String sortBy, String sortOrder,
			Map<String, String> filterValues) {
		Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Specification<Product> productSpecification2 = ProductSpecification.getProductSpecification(filterValues);
		Page<Product> pageProducts = productRepository.findAll(productSpecification2, pageable);
		ProductDTO productDTO = createProductDto(pageProducts.get().toList());
		productDTO.setPageNo(pageProducts.getNumber());
		productDTO.setPageSize(pageProducts.getSize());
		productDTO.setTotalElements(pageProducts.getTotalElements());
		productDTO.setTotalPages(pageProducts.getTotalPages());
		productDTO.setLast(pageProducts.isLast());
		return productDTO;
	}

	private ProductDTO createProductDto(List<Product> products) {
		ProductDTO productDTO = new ProductDTO();
		if (!products.isEmpty()) {
			List<ProductDTO.Product> productDTOs = new ArrayList<>();
			products.forEach(product -> {
				ProductDTO.Product innerProduct = new ProductDTO().new Product();
				innerProduct.setBrand(product.getBrand());
				innerProduct.setColor(product.getColor());
				innerProduct.setDescription(product.getDescription());
				innerProduct.setName(product.getName());
				innerProduct.setPrice(product.getPrice());
				innerProduct.setAvailable(product.isAvailable());
				innerProduct.setPurchasedDate(product.getPurchasedDate());
				innerProduct.setType(product.getType());
				innerProduct.setStatus(product.getProductStatus().name());
				productDTOs.add(innerProduct);
			});
			productDTO.setProducts(productDTOs);
		}
		return productDTO;
	}

}
