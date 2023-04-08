package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User findByUsername(String username){
        return userRepository.findByName(username);
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User user){
        if(userRepository.findByName(user.getName()) == null) {
            userRepository.save(user);
        }
    }

    @Override
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    public User getUserById(Long id){
        return userRepository.getUserById(id);
    }

    public void update(User user){
        userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}