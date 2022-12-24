package io.javalabs.ims.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductRequestDTO {

	private Long id;
	
	@NotBlank(message = "product name shouldn't be NULL OR EMPTY")
	private String name;

	private String description;

	@NotBlank(message = "product type shouldn't be NULL OR EMPTY")
	@JsonAlias(value = {"type"})
	private String productType;

	@Min(value = 1, message = "quantity is not defined !")
	private int quantity;

	@Min(value = 200, message = "product price can't be less than 200")
	@Max(value = 500000, message = "product price can't be more than 5000")
	private double price;

	private String supplierName;

	@NotBlank(message = "supplier code shouldn't be NULL OR EMPTY")
	private String supplierCode;

}
