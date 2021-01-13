package org.launchcode.controllers;

import org.launchcode.data.CarCategoryRepository;
import org.launchcode.data.CarRepository;
import org.launchcode.data.YearRepository;
import org.launchcode.models.*;
import org.launchcode.models.dto.CarYearDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("cars")

public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarCategoryRepository carCategoryRepository;

    @Autowired
    private YearRepository yearRepository;

    @GetMapping
    public String displayAllCars(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId == null) {
            model.addAttribute("title", "All cars!");
            model.addAttribute("cars", carRepository.findAll());
        } else{
            Optional<CarCategory> result = carCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title", "Invalid category: " + categoryId);
            }else{
                CarCategory category = result.get();
                model.addAttribute("title", "Cars in category: " + category.getBrand());
                model.addAttribute("cars", category.getCars());
            }
        }
        return "cars/index";

    }


    @PostMapping
    public String carDirection(){
        return "cars/index";
    }


    @GetMapping("create")
    public String renderCreateCarForm(Model model){
        model.addAttribute("title", "Create Car");
        model.addAttribute(new Car());
        model.addAttribute("categories", carCategoryRepository.findAll());
        return "cars/create";
    }

    @PostMapping("create")
    public String createCar(@ModelAttribute @Valid Car newCar, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Car");
            return "cars/create";
        }
        carRepository.save(newCar);
        return "redirect:";
    }



    @GetMapping("delete")
    public String displayDeleteCarForm(Model model){
        model.addAttribute("title", "Delete Cars");
        model.addAttribute("cars",carRepository.findAll());
        return "cars/delete";
    }

    @PostMapping("delete")
    public String processDeleteCarForm(@RequestParam(required = false) int[] carIds){

        if(carIds != null) {
            for (int id : carIds) {
                carRepository.deleteById(id);
            }
        }
        return "redirect:";

    }


    @GetMapping("update")
    public String displayUpdateCarForm(Model model){
        model.addAttribute("title", "Update Cars");
        model.addAttribute(new Car());
        model.addAttribute("categories", carCategoryRepository.findAll());
        model.addAttribute("cars",carRepository.findAll());
        return "cars/update";
    }

    @PostMapping("update")
    public String processUpdateCarForm(@RequestParam(required = false) int[] carIds,@ModelAttribute Car newCar){

        if(carIds != null) {
            Car newCar1 = carRepository.findById(carIds[0]).get();
            newCar1.setBrand(newCar.getBrand());
            newCar1.setCarDetails(newCar.getCarDetails());
            newCar1.setCategory(newCar.getCategory());
            carRepository.save(newCar1);
        }

        return "redirect:";

    }


    @GetMapping("detail")
    public String displayCarDetails(@RequestParam Integer carId, Model model){

        Optional<Car> result = carRepository.findById(carId);

        if(result.isEmpty()){
            model.addAttribute("title" + "Invalid car Id : " + carId);
        }else{
            Car car = result.get();
            model.addAttribute("title", car.getBrand() + " details ");
            model.addAttribute("car", car);
            model.addAttribute("years", car.getYears());
        }
        return "cars/detail";
    }

    @GetMapping("add-year")
    public String displayAddYearForm(@RequestParam Integer carId, Model model){
        Optional<Car> result = carRepository.findById(carId);
        Car car = result.get();

        model.addAttribute("title", "Add year to: " + car.getBrand());
        model.addAttribute("years", yearRepository.findAll());
        model.addAttribute("car", car);
        CarYearDTO carYear = new CarYearDTO();
        carYear.setCar(car);
        model.addAttribute("carYear", carYear);

        return "cars/add-year";
    }

    @PostMapping("add-year")
    public String processAddYearForm(@ModelAttribute @Valid CarYearDTO carYear, Errors errors, Model model){
        if(!errors.hasErrors()){
            Car car = carYear.getCar();
            Year year = carYear.getYear();

            if( !car.getYears().contains(year)){
                car.addYear(year);
                carRepository.save(car);
            }

            return "redirect:detail?carId=" + car.getId();
        }

        return "cars/add-year";
    }

}
