package com.techgen.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.techgen.entity.Customer;
import com.techgen.entity.Product;
import com.techgen.model.CustomerDTO;
import com.techgen.model.ProductDTO;
import com.techgen.model.ResponseDTO;
import com.techgen.repository.CustomerRepository;
import com.techgen.repository.ProductRepository;
import com.techgen.specification.ProductSpecification;

@Service
public class ProductService {

	private ProductRepository productRepository;

	private CustomerRepository customerRepository;

	public ProductService(ProductRepository productRepository, CustomerRepository customerRepository) {
		super();
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
	}

	public ResponseDTO<List<ProductDTO>> getProducts(int pageNo, int pageSize, String sortBy, String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Product> pageProducts = productRepository.findAll(pageable);
		List<ProductDTO> productDTOs = createProductDtos(pageProducts.get().toList());
		return new ResponseDTO<>(pageProducts.getNumber(), pageProducts.getSize(), pageProducts.getTotalPages(),
				pageProducts.getTotalElements(), true, "Success", productDTOs);
	}

	public ResponseDTO<List<ProductDTO>> getProductsByFilter(int pageNo, int pageSize, String sortBy, String sortOrder,
			Map<String, String> filterValues) {
		Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Specification<Product> productSpecification2 = ProductSpecification.getProductSpecification(filterValues);
		Page<Product> pageProducts = productRepository.findAll(productSpecification2, pageable);
		List<ProductDTO> productDTOs = createProductDtos(pageProducts.get().toList());
		return new ResponseDTO<>(pageProducts.getNumber(), pageProducts.getSize(), pageProducts.getTotalPages(),
				pageProducts.getTotalElements(), true, "Success", productDTOs);
	}

	private List<ProductDTO> createProductDtos(List<Product> products) {
		if (!products.isEmpty()) {
			List<ProductDTO> productDTOs = new ArrayList<>();
			products.forEach(product -> {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setBrand(product.getBrand());
				productDTO.setColor(product.getColor());
				productDTO.setDescription(product.getDescription());
				productDTO.setName(product.getName());
				productDTO.setPrice(product.getPrice());
				productDTO.setIsAvailable(Boolean.valueOf(product.isAvailable()));
				productDTO.setPurchasedDate(product.getPurchasedDate());
				productDTO.setType(product.getType());
				productDTO.setStatus(product.getProductStatus().name());
				productDTOs.add(productDTO);
			});
			return productDTOs;
		}
		return Collections.EMPTY_LIST;
	}

	public ResponseDTO<List<CustomerDTO>> getCustomersAssociatedWithProduct(int pageNo, int pageSize, long productId) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);

		Page<Customer> pageCustomers = customerRepository.findByProductId(productId, pageable);

		/*
		 * Above(findByProductId) and Below(findByProductId1,findByProductId2) queries
		 * are same
		 */

		// Page<Customer> pageCustomers = customerRepository.findByProductId1(productId,
		// pageable);

		// Optional<Product> product = productRepository.findById(productId);
		// Page<Customer> pageCustomers =
		// customerRepository.findByProductId2(product.get(), pageable);

		List<CustomerDTO> customerDTOs = createCustomerDtos(pageCustomers.stream().toList());
		return new ResponseDTO<>(pageCustomers.getNumber(), pageCustomers.getSize(), pageCustomers.getTotalPages(),
				pageCustomers.getTotalElements(), true, "Success", customerDTOs);
	}

	private List<CustomerDTO> createCustomerDtos(List<Customer> customers) {
		if (!customers.isEmpty()) {
			List<CustomerDTO> customerDTOs = new ArrayList<>();
			customers.forEach(customer -> {
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setCustomerId(customer.getCustId());
				customerDTO.setCustomerName(customer.getName());
				customerDTO.setCustomerType(customer.getType());
				customerDTOs.add(customerDTO);
			});
			return customerDTOs;
		}
		return Collections.EMPTY_LIST;
	}

	public ResponseDTO<List<CustomerDTO>> getCustomerJoinProduct(int pageNo, int pageSize,
			Map<String, String> filterValues) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Specification<Customer> customerProductSpecification = ProductSpecification
				.getCustomerJoinProductSpecification(filterValues);
		Page<Customer> customerProductPage = customerRepository.findAll(customerProductSpecification, pageable);
		List<Customer> customers = customerProductPage.stream().toList();
		List<CustomerDTO> customerDTOs = createCustomerJoinProductDtos(customers);
		return new ResponseDTO<>(customerProductPage.getNumber(), customerProductPage.getSize(),
				customerProductPage.getTotalPages(), customerProductPage.getTotalElements(), true, "Success",
				customerDTOs);
	}

	private List<CustomerDTO> createCustomerJoinProductDtos(List<Customer> customers) {
		if (!customers.isEmpty()) {
			List<CustomerDTO> customerDTOs = new ArrayList<>();
			customers.forEach(customer -> {
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setCustomerId(customer.getCustId());
				customerDTO.setCustomerName(customer.getName());
				customerDTO.setCustomerType(customer.getType());
				if (customer.getProduct() != null) {
					ProductDTO productDTO = new ProductDTO();
					productDTO.setName(customer.getProduct().getName());
					customerDTO.setProductDTO(productDTO);
				}
				customerDTOs.add(customerDTO);
			});
			return customerDTOs;
		}
		return Collections.EMPTY_LIST;
	}

	public ResponseDTO<List<ProductDTO>> getProductJoinCustomer(int pageNo, int pageSize,
			Map<String, String> filterValues) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Specification<Product> productCustomerSpecification = ProductSpecification
				.getProductJoinCustomerSpecification(filterValues);
		Page<Product> productCustomerPage = productRepository.findAll(productCustomerSpecification, pageable);
		List<Product> products = productCustomerPage.stream().toList();
		List<ProductDTO> productDTOs = createProductJoinCustomerDtos(products, filterValues);
		return new ResponseDTO<>(productCustomerPage.getNumber(), productCustomerPage.getSize(),
				productCustomerPage.getTotalPages(), productCustomerPage.getTotalElements(), true, "Success",
				productDTOs);
	}

	private List<ProductDTO> createProductJoinCustomerDtos(List<Product> products, Map<String, String> filterValues) {
		if (!products.isEmpty()) {
			List<ProductDTO> productDTOs = new ArrayList<>();
			products.forEach(product -> {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setName(product.getName());
				productDTO.setBrand(product.getBrand());
				productDTO.setColor(product.getColor());
				productDTO.setDescription(product.getDescription());
				productDTO.setPrice(product.getPrice());
				if (product.getCustomers() != null) {
					List<CustomerDTO> customerDTOs = new ArrayList<>();
					Set<Customer> customers = product.getCustomers();
					for (Customer customer : customers) {
						if (filterValues.get("customer_name").equals(customer.getName())
								&& filterValues.get("customer_type").equals(customer.getType())) {
							CustomerDTO customerDTO = new CustomerDTO();
							customerDTO.setCustomerId(customer.getCustId());
							customerDTO.setCustomerName(customer.getName());
							customerDTO.setCustomerType(customer.getType());
							customerDTOs.add(customerDTO);
						}
					}
					productDTO.setCustomers(customerDTOs);
				}
				productDTOs.add(productDTO);
			});
			return productDTOs;
		}
		return Collections.EMPTY_LIST;
	}

}
