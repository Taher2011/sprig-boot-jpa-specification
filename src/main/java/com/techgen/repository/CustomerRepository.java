package com.techgen.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.techgen.entity.Customer;
import com.techgen.entity.Product;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	/* All 3 below queries are same */

	Page<Customer> findByProductId(Long productId, Pageable pageable);

	@Query("select customer from Customer customer where customer.product.id = :productId")
	Page<Customer> findByProductId1(Long productId, Pageable pageable);

	@Query("select customer from Customer customer where customer.product = :product")
	Page<Customer> findByProductId2(Product product, Pageable pageable);

}
