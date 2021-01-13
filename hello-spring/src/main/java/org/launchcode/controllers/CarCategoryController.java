package org.launchcode.controllers;

import org.launchcode.data.CarCategoryRepository;
import org.launchcode.models.CarCategory;
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
@RequestMapping("category")
public class CarCategoryController {

    @Autowired
    private CarCategoryRepository carCategoryRepository;
    @GetMapping("carCategory")
    public String displayAllCategories(Model model){
        model.addAttribute("categories", carCategoryRepository.findAll());
        model.addAttribute("title", "All categories!");
        return "carCategory/index";
    }

    @RequestMapping("carCategory/create")
    public String displayCreateCategoryForm(Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute(new CarCategory());
        return "carCategory/create";
    }

    @PostMapping("carCategory/create")
    public String processCreateCategoryForm(@ModelAttribute @Valid CarCategory carCategory, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Car");
            return "carCategory/create";
        }
        carCategoryRepository.save(carCategory);
        return "redirect:";
    }

}
