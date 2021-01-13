package org.launchcode.controllers;

import org.launchcode.data.YearRepository;
import org.launchcode.models.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("years")
public class YearController {

    @Autowired
    private YearRepository yearRepository;

    @GetMapping
    public String displayYears(Model model){
        model.addAttribute("title", "All years");
        model.addAttribute("years", yearRepository.findAll());

        return "years/index";
    }

    @GetMapping("create")
    public String displayCreateYearForm(Model model){
        model.addAttribute("title", "Create Year");
        model.addAttribute(new Year());
        return "years/create";
    }

    @PostMapping("create")
    public String processCreateyearForm(@ModelAttribute @Valid Year year, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Create year");
            model.addAttribute(year);
            return "years/create";
        }
        yearRepository.save(year);
        return "redirect:";
    }
}
