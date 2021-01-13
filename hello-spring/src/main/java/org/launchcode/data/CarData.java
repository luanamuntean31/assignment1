package org.launchcode.data;

import org.launchcode.models.Car;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CarData {

    private static final Map<Integer, Car> cars = new HashMap<>();

    public static Collection<Car> getAll(){
        return cars.values();
    }

    public static Car getById(int id){
        return cars.get(id);
    }

    public static void add(Car car){
        cars.put(car.getId(), car);
    }

    public static void remove(int id){
        cars.remove(id);
    }

}
