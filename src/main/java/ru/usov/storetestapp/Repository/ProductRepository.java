package ru.usov.storetestapp.Repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.storetestapp.Entity.Products;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    @Override
    List<Products> findAll();

    void deleteById(@NotNull Long id);

    @Override
    Optional<Products> findById(Long id);

    List<Products> findByNameOrDescription(String name, String description);

}
