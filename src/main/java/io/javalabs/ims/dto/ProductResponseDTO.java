package io.javalabs.ims.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductResponseDTO {
	private long id;
	private String name;
	private String description;
	private String productType;
	private int quantity;
	private double price;
	private String supplierName;
	private String supplierCode;
}
