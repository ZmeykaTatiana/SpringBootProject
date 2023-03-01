package by.zmeyka.LessonProject.controller;

import by.zmeyka.LessonProject.model.User;
import by.zmeyka.LessonProject.repository.Excel;
import by.zmeyka.LessonProject.service.UserService;
import by.zmeyka.LessonProject.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

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
        model.addAttribute("reverseSortDir", sortDir.equalsIgnoreCase("asc") ? "desc" : "asc");

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
    public String saveUser(@ModelAttribute("user") @Valid User user,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "newuser";

        }


        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") Long id,Model model){
        int charsofName=0;
        int wordsofname=0;


        User user = userService.getUserById(id);
        if(user==null){
            return "error";

        } else{
            for (int i = 0; i < user.getUserName().length(); i++) {
                charsofName++;

            }
            for (int i = 0; i < user.getUserName().trim().split("[\\s]+").length; i++) {
                wordsofname++;

            }
            
        }
        model.addAttribute("user", user);
        model.addAttribute("charsofName", charsofName);
        model.addAttribute("wordsofname", wordsofname);

        return "update_user";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/download")
    public String excel(Model model){


    //    List<User> listUsers = pagination( 1, "userName", "ASC", model);

    //    Excel.makeExcel(listUsers);
        return "welldone";
    }

}



