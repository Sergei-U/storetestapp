package ru.usov.storetestapp.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.storetestapp.Entity.Category;
import ru.usov.storetestapp.Repository.CategoryRepository;

import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public void addCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public void editCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public List<Category> allCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        return categoryRepository.getById(id);
    }
}
