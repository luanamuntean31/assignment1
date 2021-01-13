package org.launchcode.controllers;

import org.launchcode.data.UserRepository;
import org.launchcode.models.Car;
import org.launchcode.models.User;
import org.launchcode.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String userDirection(){
        return "users/index";
    }

    @GetMapping
    public String displayAllUsers(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId == null) {
            model.addAttribute("title", "All users!");
            model.addAttribute("users", userRepository.findAll());
        }
        return "users/index";

    }

    @GetMapping("create")
    public String displayCreateUserForm(Model model){
        model.addAttribute("title","Create user" );
        model.addAttribute("categories", UserType.values());
        model.addAttribute(new User());
        return "users/create";
    }

    @PostMapping("create")
    public String processCreateUserForm(@ModelAttribute @Valid User newUser, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create User");
            return "users/create";
        }

        userRepository.save(newUser);
        return "redirect:";
    }


    @GetMapping("delete")
    public String displayDeleteUserForm(Model model){
        model.addAttribute("title","Delete User");
        model.addAttribute("users",userRepository.findAll());
        return "users/delete";
    }

    @PostMapping("delete")
    public String processDeleteUserForm(@RequestParam(required = false) int[] userIds )
    {    if (userIds != null) {
        for (int id : userIds) {
            userRepository.deleteById(id);

        }
    }
        return "redirect:";

    }

    @GetMapping("update")
    public String displayUpdateUserForm(Model model){
        model.addAttribute("title", "Update Users");
        model.addAttribute(new User());
        model.addAttribute("categories", UserType.values());
        model.addAttribute("cars",userRepository.findAll());
        return "cars/update";
    }

    @PostMapping("update")
    public String processUpdateUserForm(@RequestParam(required = false) int[] userIds,@ModelAttribute User newUser){

        if(userIds != null) {
            User newUser1 = userRepository.findById(userIds[0]).get();
            newUser1.setUsername(newUser.getUsername());
            newUser1.setPassword(newUser.getPassword());

            userRepository.save(newUser1);
        }

        return "redirect:";

    }



}
