package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> getAll();

    void add(User user);

    void delete(Long id);

    User getUserById(Long id);

    void update(User user);
}
