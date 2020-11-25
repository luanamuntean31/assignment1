package model.builder;

import model.Employee;

public class EmployeeBuilder {

    private Employee employee;

    public EmployeeBuilder() {
        employee = new Employee();
    }

    public EmployeeBuilder setIdEmployee(Long idEmployee) {
        employee.setIdEmployee(idEmployee);
        return this;
    }

    public EmployeeBuilder setNameEmployee(String nameEmployee) {
        employee.setNameEmployee(nameEmployee);
        return this;
    }

    public EmployeeBuilder setAddress(String address){
        employee.setAddress(address);
        return this;
    }


    public Employee build() {
        return employee;
    }


}
