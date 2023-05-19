package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.ProductRequest;
import com.dto.ProductResponse;
import com.model.Product;
import com.repo.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepo repo;

//	ProductService(ProductRepo repo) {
//		this.repo = repo;
//	}

	public Product saveProduct(ProductRequest productRequest) {
		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		log.info("Product {} is saved", product.getId());
		return repo.save(product);

	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = repo.findAll();
		return products.stream().map(this::mapProductResponse).collect(Collectors.toList());
	}

	private ProductResponse mapProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
	}

}
