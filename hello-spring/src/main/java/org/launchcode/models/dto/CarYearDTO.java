package org.launchcode.models.dto;

import org.launchcode.models.Car;
import org.launchcode.models.Year;


import javax.validation.constraints.NotNull;

public class CarYearDTO {

    @NotNull
    private Car car;

    @NotNull
    private Year year;

    public CarYearDTO() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
