package io.javalabs.ims.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.javalabs.ims.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

	public Optional<List<Product>> findByName(String name);
	public Optional<List<Product>> findBySupplier(String supplier);
}
