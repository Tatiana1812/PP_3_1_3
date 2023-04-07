package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String welcomePrint(ModelMap model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }
    @GetMapping(value = "/add")
    public String showUserForm(ModelMap model) {
        model.put("Title","Add new user");
        User newUser = new User();
        model.put("user",newUser);
        return "add";
    }
    @PostMapping(value = "/add")
    public String addUserForm(@RequestParam String name, @RequestParam String surname,
                              @RequestParam int age,@RequestParam String password, ModelMap model) {
        User newUser=new User(name,surname,age,password);
        userService.add(newUser);
        model.put("users",newUser);
        return "redirect:/admin";
    }
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/edit")
    public String showEditUserForm(@RequestParam Long id,ModelMap model) {
        model.addAttribute("user",userService.getUserById(id));
        model.put("Title","Edit user");
        return "add";
    }
    @PostMapping(value = "/edit")
    public String editUserForm(@RequestParam Long id,@RequestParam String name,@RequestParam String surname,
                               @RequestParam int age,@RequestParam String password, ModelMap model) {
        User newUser=new User(name,surname,age,password);
        newUser.setId(id);
        userService.update(newUser);

        return "redirect:/admin";
    }
}
