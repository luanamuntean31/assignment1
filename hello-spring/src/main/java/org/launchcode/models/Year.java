package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Year extends AbstractEntity{

    @NotBlank
    @Size(min = 1, max = 25)
    private String brand;

    @ManyToMany(mappedBy = "years")
    private final List<Car> cars = new ArrayList<>();

    public Year(@NotBlank @Size(min = 1, max = 25) String brand) {
        this.brand = brand;
    }

    public Year() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDisplayBrand(){
        return "#" + brand + " ";
    }
}
