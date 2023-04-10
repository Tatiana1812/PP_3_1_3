package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    @Autowired
    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String welcomePrint(ModelMap model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping(value = "/add")
    public String showUserForm(ModelMap model) {
        model.put("Title", "Add new user");
        User newUser = new User();
        newUser.setRoles(new HashSet<>());
        model.put("user", newUser);
        model.put("roles",roleService.getAll());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUser(@RequestParam String name, @RequestParam String surname,
                          @RequestParam int age, @RequestParam String password, @RequestParam List<Long> roles, ModelMap model) {
        User newUser = new User(name, surname, age, password,roleService.getRolesById(roles));
        userService.add(newUser);
        model.put("users", newUser);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit")
    public String showEditUserForm(@RequestParam Long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        model.put("roles",roleService.getAll());
        model.put("Title", "Edit user");
        return "add";
    }

    @PostMapping(value = "/edit")
    public String editUser(@RequestParam Long id, @RequestParam String name, @RequestParam String surname,
                           @RequestParam int age, @RequestParam String password, @RequestParam List<Long> roles, ModelMap model) {
        User newUser = userService.getUserById(id);
        newUser.setId(id);
        newUser.setName(name);
        newUser.setLastName(surname);
        newUser.setAge(age);
        newUser.setPassword(password);
        newUser.setRoles(roleService.getRolesById(roles));
        userService.update(newUser);
        return "redirect:/admin";
    }
}
