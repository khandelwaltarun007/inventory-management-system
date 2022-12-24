package io.javalabs.ims.util;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalabs.ims.dto.ProductRequestDTO;
import io.javalabs.ims.dto.ProductResponseDTO;
import io.javalabs.ims.model.Product;

public class ValueMapper {

	public static Product convertProductRequestDTOToEntity(ProductRequestDTO productRequestDTO) {
		return Product.builder()
				.name(productRequestDTO.getName())
				.price(productRequestDTO.getPrice())
				.quantity(productRequestDTO.getQuantity())
				.productType(productRequestDTO.getProductType())
				.supplier(productRequestDTO.getSupplierCode())
				.supplierName(productRequestDTO.getSupplierName())
				.description(productRequestDTO.getDescription())
				.build();
	}
	
	public static List<Product> convertProductRequestDTOsToEntities(List<ProductRequestDTO> productRequestDTOs) {
		return productRequestDTOs.stream().map(productRequestDTO -> Product.builder()
				.name(productRequestDTO.getName())
				.price(productRequestDTO.getPrice())
				.quantity(productRequestDTO.getQuantity())
				.productType(productRequestDTO.getProductType())
				.supplier(productRequestDTO.getSupplierCode())
				.supplierName(productRequestDTO.getSupplierName())
				.description(productRequestDTO.getDescription())
				.build()).collect(Collectors.toList());
	}

	public static ProductRequestDTO convertProductEntitytoRequestDTO(Product product) {
		return ProductRequestDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.productType(product.getProductType())
				.supplierCode(product.getSupplier())
				.supplierName(product.getSupplierName())
				.description(product.getDescription())
				.build();
	}
	
	public static List<ProductResponseDTO> convertProductEntitiestoResponseDTOs(List<Product> products) {
		return products.stream().map(product -> ProductResponseDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.productType(product.getProductType())
				.supplierCode(product.getSupplier())
				.supplierName(product.getSupplierName())
				.description(product.getDescription())
				.build()).collect(Collectors.toList());
	}
	
	public static ProductResponseDTO convertProductEntitytoResponseDTO(Product product) {
		return ProductResponseDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.productType(product.getProductType())
				.supplierCode(product.getSupplier())
				.supplierName(product.getSupplierName())
				.description(product.getDescription())
				.build();
	}

	public static String jsonToString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
