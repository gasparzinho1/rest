package com.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.Product;
import com.rest.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
    public Collection<Product> getAll(){
		return productService.getAll();
	}
    
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") int id){
		return productService.getProductById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id){
		productService.deleteProductById(id);
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public void saveOrUpdate(@RequestBody Product product){
		productService.saveOrUpdate(product);
	}
}