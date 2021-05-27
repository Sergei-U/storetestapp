package ru.usov.storetestapp.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.usov.storetestapp.Entity.Prod;
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
    public List<Prod> list() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullProduct.class)
    public Prod getOne(@PathVariable("id") Prod prod) {
        return prod;
    }

    @PostMapping
    public Prod create(@RequestBody Prod prod) {
        prod.setCreationDate(LocalDateTime.now());
        return productRepository.save(prod);
    }

    @PutMapping("{id}")
    public Prod update(
            @PathVariable("id") Prod prodFromDb,
            @RequestBody Prod prod
    ) {
        BeanUtils.copyProperties(prod, prodFromDb, "id");

        return productRepository.save(prodFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Prod prod) {
        productRepository.delete(prod);
    }
}