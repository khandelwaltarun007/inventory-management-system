package io.javalabs.ims.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javalabs.ims.dto.ProductRequestDTO;
import io.javalabs.ims.dto.ProductResponseDTO;
import io.javalabs.ims.exception.EntityNotFoundException;
import io.javalabs.ims.model.Product;
import io.javalabs.ims.repository.ProductRepository;
import io.javalabs.ims.util.ValueMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
		log.info("ProductServiceImpl :: addProduct entry");
		return ValueMapper.convertProductEntitytoResponseDTO(productRepository.save(ValueMapper.convertProductRequestDTOToEntity(productRequestDTO)));
	}

	@Override
	public Iterable<Product> getProducts() {
		log.info("ProductServiceImpl :: getProducts entry");
		return productRepository.findAll();
	}

	@Override
	public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) {
		log.info("ProductServiceImpl :: updateProduct entry");
		return ValueMapper.convertProductEntitytoResponseDTO(productRepository.save(ValueMapper.convertProductRequestDTOToEntity(productRequestDTO)));
	}

	@Override
	public void deleteProduct(Long id) {
		getProductById(id);
		productRepository.deleteById(id);
	}

	@Override
	public ProductResponseDTO getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with ID: "+id+" doesn't exists."));
		return ValueMapper.convertProductEntitytoResponseDTO(product);
	}

	@Override
	public List<ProductResponseDTO> addProducts(List<ProductRequestDTO> products) {
		try {
		List<ProductResponseDTO> addProductResponse = new ArrayList<>();
		products.parallelStream().forEach(product -> {
			addProductResponse.add(addProduct(product));
		});
		return addProductResponse;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductResponseDTO> getProductByName(String name) {
		Optional<List<Product>> products = productRepository.findByName(name);
		return ValueMapper.convertProductEntitiestoResponseDTOs(products.get());
	}

	@Override
	public List<ProductResponseDTO> advanceSearch(String findBy, String operation, String value) throws Exception {
		List<ProductResponseDTO> products = null;
		switch(operation) {
		case "equals":
		case "equal":
		case "=":
		case "==":
			products = findByFieldEquals(findBy, value);
			break;
		default:
			throw new Exception("Invalid Operation.");
		}
		return products;
	}

	private List<ProductResponseDTO> findByFieldEquals(String findBy, String value) throws Exception {
		List<ProductResponseDTO> productResponseDTO = null;
		switch(findBy.toLowerCase()) {
		case "id":
			productResponseDTO = List.of(getProductById(Long.valueOf(value)));
			break;
		case "name":
			productResponseDTO = getProductByName(value);
			break;
		case "suppliercode":
			productResponseDTO = getProductBySupplier(value);
			break;
		default:
			throw new Exception("Invalid Field");
		}
		return productResponseDTO;
	}

	@Override
	public List<ProductResponseDTO> getProductBySupplier(String supplier) {
		Optional<List<Product>> products = productRepository.findBySupplier(supplier);
		return ValueMapper.convertProductEntitiestoResponseDTOs(products.get());
	}

}
