package io.javalabs.ims.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.javalabs.ims.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

}
