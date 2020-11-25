package repository.employee;

import model.Client;
import model.Employee;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface EmployeeRepository {

    boolean add (Employee employee );

    boolean update (Employee employee, Long currId );

    Notification<Employee> viewEmployee (String nameEmployee);

    boolean delete (Employee employee,Long currId );

    void removeAll();

    List<Employee> findAll();
}
