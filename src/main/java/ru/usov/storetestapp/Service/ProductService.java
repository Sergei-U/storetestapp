package ru.usov.storetestapp.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.storetestapp.Entity.Products;
import ru.usov.storetestapp.Repository.ProductRepository;

import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(Products product) {this.productRepository.save(product);}
    public void editProduct(Products product) {this.productRepository.save(product);}
    public void deleteProduct(Long id) {this.productRepository.deleteById(id);}
    public List<Products> allProduct(){return productRepository.findAll();}
    public Products getProduct(Long id) {return productRepository.getById(id);}
}
