package io.javalabs.ims.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.javalabs.ims.dto.ProductRequestDTO;
import io.javalabs.ims.dto.ProductResponseDTO;
import io.javalabs.ims.model.Product;
import io.javalabs.ims.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO product){
		ProductResponseDTO createdProduct = productService.addProduct(product);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}
	
	@PostMapping("/products")
	public ResponseEntity<List<ProductResponseDTO>> addProducts(@Valid @RequestBody ArrayList<ProductRequestDTO> products){
		List<ProductResponseDTO> createdProducts = productService.addProducts(products);
		return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
	}
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> products = StreamSupport.stream(productService.getProducts().spliterator(),false).collect(Collectors.toList());
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PutMapping("/product")
	public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
		ProductResponseDTO updatedProduct = productService.updateProduct(productRequestDTO);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") Long id){
		ProductResponseDTO getProductById = productService.getProductById(id);
		return new ResponseEntity<>(getProductById,HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
	}
}
