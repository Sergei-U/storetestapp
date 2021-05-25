package ru.usov.storetestapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.storetestapp.Entity.Category;

/**
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
