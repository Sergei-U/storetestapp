package ru.usov.storetestapp.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.storetestapp.Entity.Prod;
import ru.usov.storetestapp.Repository.ProductRepository;

import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(Prod product) {this.productRepository.save(product);}
    public void editProduct(Prod product) {this.productRepository.save(product);}
    public void deleteProduct(Long id) {this.productRepository.deleteById(id);}
    public List<Prod> allProduct(){return productRepository.findAll();}
    public Prod getProduct(Long id) {return productRepository.getById(id);}
}
