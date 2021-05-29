package ru.usov.storetestapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.storetestapp.Entity.Products;

/**
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
}
