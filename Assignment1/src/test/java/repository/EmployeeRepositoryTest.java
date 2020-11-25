package repository;

import database.DBConnectionFactory;
import model.Client;
import model.Employee;
import model.builder.ClientBuilder;
import model.builder.EmployeeBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeRepositoryTest {
    private static EmployeeRepository employeeRepository;


    @BeforeClass
    public static void setupClass() {
        employeeRepository = new EmployeeRepositoryMySQL(
                new DBConnectionFactory().getConnectionWrapper(true).getConnection());
    }


    @Before
    public void cleanUp() {
        employeeRepository.removeAll();
    }

    @Test
    public void findAll() {
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(employees.size(), 0);
    }

    @Test
    public void add() {
        assertTrue(employeeRepository.add(
                new EmployeeBuilder()
                        .setNameEmployee("Name")
                        .setAddress("Address")
                        .build()
        ));
    }

    @Test
    public void findAllWhenDbNotEmpty() {
        Employee employee = new EmployeeBuilder()
                .setNameEmployee("Name")
                .setAddress("Address")
                .build();

        employeeRepository.add(employee);
        employeeRepository.add(employee);
        employeeRepository.add(employee);

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(employees.size(), 3);
    }


    @Test
    public void update()  {
        Employee employee = new EmployeeBuilder()
                .setNameEmployee("Name")
                .setAddress("Address")
                .build();
        employeeRepository.add(employee);

        long currId = employeeRepository.viewEmployee("Name").getResult().getIdEmployee();


        Employee updateEmployee = new EmployeeBuilder()
                .setNameEmployee("NewName")
                .setAddress("NewAddress")
                .build();

        employeeRepository.update(updateEmployee, currId);

        assertTrue(employeeRepository.viewEmployee("NewName").getResult() !=null );
        assertTrue(employeeRepository.viewEmployee("Name").hasErrors());
        assertTrue(employeeRepository.viewEmployee("NewName").getResult().getIdEmployee() == currId );


    }




    @Test
    public void removeAll()  {
        Employee employee = new EmployeeBuilder()
                .setNameEmployee("Name")
                .setAddress("Address")
                .build();
        employeeRepository.add(employee);
        employeeRepository.add(employee);
        employeeRepository.add(employee);
        employeeRepository.add(employee);
        employeeRepository.add(employee);

        employeeRepository.removeAll();

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(employees.size(), 0);
    }

}
