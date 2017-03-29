package com.rest.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.ProductDao;
import com.rest.entity.Product;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public Collection<Product> getAll(){
		return productDao.findAll();
	}	

	public Product getProductById(int id){
		return productDao.findOne(id);
	}
	
	public void deleteProductById(int id){
		productDao.delete(id);
	}
	
	public Product saveOrUpdate(Product product){
		return productDao.save(product);
	}
}