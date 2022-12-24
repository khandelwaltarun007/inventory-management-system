package io.javalabs.ims.service;

import java.util.List;

import io.javalabs.ims.dto.ProductRequestDTO;
import io.javalabs.ims.dto.ProductResponseDTO;
import io.javalabs.ims.model.Product;

public interface ProductService {
	public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
	public Iterable<Product> getProducts();
	public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO);
	public void deleteProduct(Long id);
	public ProductResponseDTO getProductById(Long id);
	public List<ProductResponseDTO> addProducts(List<ProductRequestDTO> products);
}
