package by.zmeyka.LessonProject.controller;

import by.zmeyka.LessonProject.model.User;
import by.zmeyka.LessonProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String showAllUsers(Model model){
        return pagination( 1, "userName", "ASC", model);
    }

    @GetMapping("/users/page/{pageNo}")
    public String pagination(@PathVariable(value = "pageNo") int pageNo,
                             @RequestParam("sortField") String sortField,
                             @RequestParam("sortDir") String sortDir,
                             Model model) {
        int pageSize = 5;

        Page<User> page = userService.pagination(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/add")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "newuser";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

}



