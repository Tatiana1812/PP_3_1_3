package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface UserService {
    User findByUsername(String username);
    List<User> getAll();
    void add(User user);
    void delete(Long id);
    User getUserById(Long id);
    void update(User user);
}
