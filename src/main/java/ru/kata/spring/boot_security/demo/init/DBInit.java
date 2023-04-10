package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void postConstruct() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        roleRepository.save(user);
        roleRepository.save(admin);
        Set<Role> set1 = new HashSet<>();
        set1.add(user);
        Set<Role> set2 = new HashSet<>();
        set2.add(admin);
        User user1 = new User("Karina", "Rybak", 24, "user1", set1);
        User user2 = new User("Ivan", "Rybak", 26, "user2", set2);
        userRepository.save(user1);
        userRepository.save(user2);
        set1.add(admin);
        User user3 = new User("Tatiana", "Mukhina", 21, "user3", set1);
        userRepository.save(user3);
    }

    @PreDestroy
    public void preDestroy() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }
}
