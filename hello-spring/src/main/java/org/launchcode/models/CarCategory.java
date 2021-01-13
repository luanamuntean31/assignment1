package org.launchcode.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class CarCategory extends AbstractEntity{

    @Size(min = 1, message = "name must be at least 1 character long")
    private String brand;

    @OneToMany(mappedBy = "brand")
    private final List<Car> cars = new ArrayList<>();

    public CarCategory(@Size(min = 1, message = "name must be at least 1 character long") String brand){
        this.brand = brand;
    }

    public CarCategory(){}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Car> getCars() {
        return cars;
    }
}
