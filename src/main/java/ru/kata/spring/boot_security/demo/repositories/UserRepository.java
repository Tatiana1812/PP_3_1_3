package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
    List<User> findAll();
    @Transactional
    User save(User user);
    @Transactional
    void deleteById(Long id);
    User getUserById(Long id);

}
