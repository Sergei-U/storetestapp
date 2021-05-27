package ru.usov.storetestapp.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.usov.storetestapp.Entity.User;
import ru.usov.storetestapp.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("In getAll - {} userus found", result.size());
        return result;
    }

    public User findByUserName(String username) {
        User result = userRepository.findByName(username);
        log.info("In findByUserName - user: {} found by username", result, username);
        return result;
    }

    public Optional<User> findByID(String id) {
        Optional<User> result = userRepository.findById(id);
        if(result==null){
            log.warn("In findById - user: {} user not found", id);
        }
        log.info("In findById - user: {} found by username", result);
        return result;
    }
    public void deleteUser(String id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

}
