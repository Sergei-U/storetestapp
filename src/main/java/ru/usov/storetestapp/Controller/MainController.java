package ru.usov.storetestapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.usov.storetestapp.Entity.User;
import ru.usov.storetestapp.Repository.CategoryRepository;
import ru.usov.storetestapp.Repository.ProductRepository;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MainController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("product", productRepository.findAll());
        data.put("category", categoryRepository.findAll());

        model.addAttribute("frontendData", data);

        return "index";
    }
}