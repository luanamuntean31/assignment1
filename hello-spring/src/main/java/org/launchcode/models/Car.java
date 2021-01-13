package org.launchcode.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Car extends AbstractEntity{

    @NotBlank
    @Size(min = 1, max = 50,message = "Size should be between 10 and 50 characters!")
    private String brand;

    @ManyToOne
    @NotNull(message = "Category is required")
    private CarCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private CarDetails carDetails;

    @ManyToMany
    private final List<Year> years = new ArrayList<>();

    public Car(String brand, CarCategory category) {
        this.brand = brand;
        this.category = category;

    }

    public Car() {
    }

    public List<Year> getYears() {
        return years;
    }
    public void addYear(Year year){
        this.years.add(year);
    }
    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory carCategory) {
        this.category = carCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
