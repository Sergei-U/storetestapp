package ru.usov.storetestapp.Repository;

import org.jetbrains.annotations.NotNull;
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

    @NotNull
    @Override
    List<Category> findAll();

    @NotNull
    @Override
    Optional<Category> findById(Long aLong);

    @Override
    void deleteById(@NotNull Long aLong);

}
