package com.techgen.model;

public enum ProductStatus {

	IN_INVENTORY("Inside Inventory"), OUT_OF_STOCK("Out of Stock"), ON_THE_WAY("On the way"), DISCARDED("Discard");

	private String productStatus;

	private ProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public static ProductStatus getProductStatus(String productStatus) {
		if (productStatus.equalsIgnoreCase(ProductStatus.IN_INVENTORY.name())) {
			return ProductStatus.IN_INVENTORY;
		} else if (productStatus.equalsIgnoreCase(ProductStatus.OUT_OF_STOCK.name())) {
			return ProductStatus.OUT_OF_STOCK;
		} else if (productStatus.equalsIgnoreCase(ProductStatus.ON_THE_WAY.name())) {
			return ProductStatus.ON_THE_WAY;
		} else if (productStatus.equalsIgnoreCase(ProductStatus.DISCARDED.name())) {
			return ProductStatus.DISCARDED;
		}
		return null;
	}

}
