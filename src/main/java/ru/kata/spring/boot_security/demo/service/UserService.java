package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Transactional(readOnly = true)
public interface UserService {
    User findByUsername(String username);

    List<User> getAll();

    @Transactional
    void add(User user);

    @Transactional
    void delete(Long id);

    User getUserById(Long id);

    @Transactional
    void update(User user);
}
