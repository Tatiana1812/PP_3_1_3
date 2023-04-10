package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp {
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }
    public Set<Role> getRolesById(List<Long> roles){
        Set<Role> roleSet = new HashSet<>();
        for (Long role : roles) {
            roleSet.add(getRoleById(role));
        }
        return roleSet;
    }
}
