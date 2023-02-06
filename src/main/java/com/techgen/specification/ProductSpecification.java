package com.techgen.specification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.techgen.entity.Product;
import com.techgen.model.ProductStatus;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.persistence.criteria.Predicate;

@Component
public class ProductSpecification {

	private ProductSpecification() {
		super();
	}

	public static Specification<Product> getProductSpecification(Map<String, String> filterValues) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			filterValues.forEach((key, value) -> {

				if (key.equalsIgnoreCase("name")) {
					predicates.add(criteriaBuilder.equal(root.get("name"), value));
				}

				if (key.equalsIgnoreCase("description")) {
					predicates.add(criteriaBuilder.equal(root.get("description"), value));
				}

				if (key.equalsIgnoreCase("brand")) {
					predicates.add(criteriaBuilder.equal(root.get("brand"), value));
				}

				if (key.equalsIgnoreCase("color")) {
					predicates.add(criteriaBuilder.equal(root.get("color"), value));
				}

				if (key.equalsIgnoreCase("type")) {
					predicates.add(criteriaBuilder.equal(root.get("type"), value));
				}

				if (key.equalsIgnoreCase("price")) {
					predicates.add(criteriaBuilder.equal(root.get("price"), value));
				}

				if (key.equalsIgnoreCase("available")) {
					predicates.add(criteriaBuilder.equal(root.get("isAvailable"), Boolean.valueOf(value)));
				}

				if (key.equalsIgnoreCase("purchasedDate")) {
					LocalDateTime fromDate = LocalDateTime.parse(value);
					LocalDateTime toDate = getCurrentLocalDateTime();
					predicates.add(criteriaBuilder.between(root.get("purchasedDate"), fromDate, toDate));
				}

				if (key.equalsIgnoreCase("status")) {
					List<Predicate> criteriaProductStatus = new ArrayList<>();
					List<ProductStatus> productStatuses = new ArrayList<>();
					for (String productStatus : value.split(",")) {
						productStatuses.add(ProductStatus.getProductStatus(productStatus));
					}
					In<Object> in = criteriaBuilder.in(root.get("productStatus"));
					in.value(productStatuses);
					criteriaProductStatus.add(in);
					Predicate[] p = new Predicate[criteriaProductStatus.size()];
					predicates.add(criteriaBuilder.or(criteriaProductStatus.toArray(p)));
				}
			});

			return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).getRestriction();
		};
	}

	private static LocalDateTime getCurrentLocalDateTime() {
		String pattern = "yyyy-MM-dd'T'HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date today = Calendar.getInstance().getTime();
		String currentDate = dateFormat.format(today);
		return LocalDateTime.parse(currentDate);
	}

}
