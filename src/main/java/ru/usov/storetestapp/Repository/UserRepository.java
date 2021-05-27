package ru.usov.storetestapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.storetestapp.Entity.User;

/**
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);
}
