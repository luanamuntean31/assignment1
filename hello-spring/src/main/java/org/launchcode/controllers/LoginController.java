package org.launchcode.controllers;

import org.launchcode.data.CarRepository;
import org.launchcode.data.UserRepository;
import org.launchcode.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String processLoginForm(@ModelAttribute User user) {

        User user1 = userRepository.findByUsername(user.getUsername());

        if(user1!=null) {

           User user2=userRepository.findByPassword(user.getPassword());

            if (user1.equals(user2)) {

                if (user1.getCategory().getDisplayType().equals("user")) {

                    return "cars/index";
                } else {
                    return "admin";
                }

            }
        }

        return "login";
    }


}
