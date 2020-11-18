package model.builder;

import model.Employee1;

public class Employee1Builder {
    private Employee1 employee1;

    public Employee1Builder() {
        employee1 = new Employee1();
    }

    public Employee1Builder setIdEmployee(int idEmployee) {
        employee1.setIdEmployee(idEmployee);
        return this;
    }

    public Employee1Builder setNameEmployee(String nameEmployee) {
        employee1.setNameEmployee(nameEmployee);
        return this;
    }

    public Employee1Builder setAddress(String address) {
        employee1.setAddress(address);
        return this;
    }


    public Employee1 build() {
        return employee1;
    }


}
