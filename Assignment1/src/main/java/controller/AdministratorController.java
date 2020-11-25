package controller;

import model.Client;
import model.Employee;
import model.User;
import model.builder.ClientBuilder;
import model.builder.EmployeeBuilder;
import model.validation.Notification;
import repository.employee.EmployeeRepository;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.AdministratorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class AdministratorController {

    private final AdministratorView administratorView;
    private final EmployeeRepository employeeRepository;


    public AdministratorController(AdministratorView administratorView, EmployeeRepository employeeRepository) {
        this.administratorView = administratorView;
        this.employeeRepository = employeeRepository;
        administratorView.setCreateEmployeeListener(new AdministratorController.CreateEmployeeListener());
        administratorView.setViewEmployeeListener(new AdministratorController.ViewEmployeeListener());
        administratorView.setUpdateEmployeeListener(new AdministratorController.UpdateEmployeeListener());
        administratorView.setDeleteEmployeeListener(new AdministratorController.DeleteEmployeeListener());
        administratorView.setGenerateReportsListener(new AdministratorController.GenerateReportsListener());


    }

    private class CreateEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            String nameEmployee = administratorView.getEmployeeNameTf().getText();

            String addressEmployee = administratorView.getEmployeeAddressTf().getText();


            if ((nameEmployee.isEmpty()) || (addressEmployee.isEmpty())) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Not all fields are completed!");
                return;

            }

            Employee employee = new EmployeeBuilder()
                    .setNameEmployee(nameEmployee)
                    .setAddress(addressEmployee)
                    .build();

            boolean savedEmployee = employeeRepository.add(employee);


            if (savedEmployee)

                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Employee data is saved!");
            else
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Errors to save!");


        }


    }


    private class ViewEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchEmployeeByName = administratorView.getSearchEmployeeByNameTf().getText();

            Notification<Employee> findEmployeeByNameNotification = null;
            findEmployeeByNameNotification = employeeRepository.viewEmployee(searchEmployeeByName);


            if (findEmployeeByNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), findEmployeeByNameNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "The search by name was successful!");

                Employee currEmployee = findEmployeeByNameNotification.getResult();

                administratorView.setEmployeeIdTf(currEmployee.getIdEmployee());
                administratorView.setEmployeeNameTf(currEmployee.getNameEmployee());
                administratorView.setEmployeeAddressTf(currEmployee.getAddress());


            }

        }

    }


    private class UpdateEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchEmployeeByName = administratorView.getSearchEmployeeByNameTf().getText();


            Notification<Employee> findEmployeeByNameNotification = null;
            findEmployeeByNameNotification = employeeRepository.viewEmployee(searchEmployeeByName);


            if (findEmployeeByNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), findEmployeeByNameNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "The search by name was successful!");



                Employee currEmployee = findEmployeeByNameNotification.getResult();


                long currId = currEmployee.getIdEmployee();

                String nameEmployee = administratorView.getEmployeeNameTf().getText();

                String addressEmployee = administratorView.getEmployeeAddressTf().getText();



                Employee updateEmployee = new EmployeeBuilder()
                        .setIdEmployee(currId)
                        .setNameEmployee(nameEmployee)
                        .setAddress(addressEmployee)
                        .build();


                boolean modifyEmployee = employeeRepository.update(updateEmployee, currId);


                if (modifyEmployee)

                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Employee data has changed!");
                else
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Errors to save!");


            }

        }


    }


    private class DeleteEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchEmployeeByName = administratorView.getSearchEmployeeByNameTf().getText();


            Notification<Employee> findEmployeeByNameNotification = null;
            findEmployeeByNameNotification = employeeRepository.viewEmployee(searchEmployeeByName);


            if (findEmployeeByNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), findEmployeeByNameNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "The search by name was successful!");



                Employee currEmployee = findEmployeeByNameNotification.getResult();

                //preluam id-ul angajatului pe care l-am cautat dupa nume

                long currId = currEmployee.getIdEmployee();

                boolean deleteEmployee = employeeRepository.delete(currEmployee, currId);

                if (deleteEmployee)

                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Employee data has deleted!");
                else
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "Errors at delete!");

            }
        }


    }

    public class GenerateReportsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e1) {


            String searchEmployeeByName = administratorView.getSearchEmployeeByNameTf().getText();


            Notification<Employee> findEmployeeByNameNotification = null;
            findEmployeeByNameNotification = employeeRepository.viewEmployee(searchEmployeeByName);


            if (findEmployeeByNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), findEmployeeByNameNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "The search by name was successful!");


                Employee currEmployee = findEmployeeByNameNotification.getResult();



                long currId = currEmployee.getIdEmployee();

                String nameEmployee = currEmployee.getNameEmployee();

                String addressEmployee = currEmployee.getAddress();

                 try {

                File file = new File("EmployeeReports.txt");

                if(!file.exists())
                {
                    file.createNewFile();
                }


                PrintWriter writer=new PrintWriter(file);
                writer.println("Employee with id=" + currId + " ,name="  + nameEmployee + ", address=" + addressEmployee
                + " has the following activities:");

                writer.close();


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }




            }


        }
    }
}
