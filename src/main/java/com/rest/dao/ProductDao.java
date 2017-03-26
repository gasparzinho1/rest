package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.Product;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Integer>{

	public void delete(Product product);
	
	public List<Product> findAll();
	
	public Product findOne(Integer id);
	
	public <S extends Product> Product save(Product product);
	
}