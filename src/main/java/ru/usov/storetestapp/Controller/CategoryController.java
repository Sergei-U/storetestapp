package ru.usov.storetestapp.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.usov.storetestapp.Entity.Category;
import ru.usov.storetestapp.Entity.Views;
import ru.usov.storetestapp.Repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullCategory.class)
    public Category getOne(@PathVariable("id") Category category) {
        return category;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        category.setCreationDate(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @PutMapping("{id}")
    public Category update(
            @PathVariable("id") Category categoryFromDb,
            @RequestBody Category category
    ) {
        BeanUtils.copyProperties(category, categoryFromDb, "id");

        return categoryRepository.save(categoryFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Category category) {
        categoryRepository.delete(category);
    }
}