package ru.usov.storetestapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.storetestapp.Entity.Category;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
