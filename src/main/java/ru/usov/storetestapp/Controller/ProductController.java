package ru.usov.storetestapp.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.usov.storetestapp.Entity.Products;
import ru.usov.storetestapp.Entity.Views;
import ru.usov.storetestapp.Repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Products> list() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullProduct.class)
    public Products getOne(@PathVariable("id") Products products) {
        return products;
    }

    @PostMapping
    public Products create(@RequestBody Products products) {
        products.setCreationDate(LocalDateTime.now());
        return productRepository.save(products);
    }

    @PutMapping("{id}")
    public Products update(
            @PathVariable("id") Products productsFromDb,
            @RequestBody Products products
    ) {
        BeanUtils.copyProperties(products, productsFromDb, "id");

        return productRepository.save(productsFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Products products) {
        productRepository.delete(products);
    }
}