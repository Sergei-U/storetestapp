package ru.usov.storetestapp.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.storetestapp.Entity.User;
import ru.usov.storetestapp.Repository.UserRepository;

import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public void addUser(User user) {this.userRepository.save(user);}
    public void editUser(User user) {this.userRepository.save(user);}
    public void deleteUser(Long id) {this.userRepository.deleteById(id);}
    public List<User> allUser(){return userRepository.findAll();}
    public User getUser(Long id) {return userRepository.getById(id);}
}
