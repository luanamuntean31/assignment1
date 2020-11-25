package repository.employee;

import model.Client;
import model.Employee;
import model.builder.ClientBuilder;
import model.builder.EmployeeBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryMySQL implements EmployeeRepository {

    private final Connection connection;

    public EmployeeRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean add(Employee employee) {
        try {
            PreparedStatement insertEmployeeStatement = connection
                    .prepareStatement("INSERT INTO employee values (null,null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertEmployeeStatement.setString(1, employee.getNameEmployee());
            insertEmployeeStatement.setString(2, employee.getAddress());
            insertEmployeeStatement.executeUpdate();

            ResultSet rs = insertEmployeeStatement.getGeneratedKeys();
            rs.next();
            long idEmployee = rs.getLong(1);
            employee.setIdEmployee(idEmployee);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Employee employee, Long currId) {

        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE employee SET nameEmployee = ?, addressEmployee=?\n" +
                            "WHERE idEmployee = ?");
            updateStatement.setString(1, employee.getNameEmployee());
            updateStatement.setString(2, employee.getAddress());
            updateStatement.setLong(3, currId);

            updateStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Notification<Employee> viewEmployee(String nameEmployee) {

        Notification<Employee> findByNameNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM  employee  WHERE nameEmployee=\'" + nameEmployee + "\'";
            ResultSet employeeResultSet = statement.executeQuery(sql);

            if (employeeResultSet.next()) {
                Employee employee = new EmployeeBuilder()
                        .setIdEmployee(employeeResultSet.getLong("idEmployee"))
                        .setNameEmployee(employeeResultSet.getString("nameEmployee"))
                        .setAddress(employeeResultSet.getString("addressEmployee"))
                        .build();

                findByNameNotification.setResult(employee);
                return findByNameNotification;

            } else {
                findByNameNotification.addError("Invalid name!");
                return findByNameNotification;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findByNameNotification;
    }

    @Override
    public boolean delete(Employee employee, Long currId) {
        try {
            PreparedStatement deleteStatement = connection
                    .prepareStatement("DELETE FROM employee WHERE idEmployee = ?");
            deleteStatement.setLong(1, currId);

            deleteStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from employee ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from employee";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                employees.add(getEmployeeFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        return new EmployeeBuilder()
                .setIdEmployee(rs.getLong("idEmployee"))
                .setNameEmployee(rs.getString("nameEmployee"))
                .setAddress(rs.getString("addressEmployee"))
                .build();
    }
}