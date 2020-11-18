package controller;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeController  {

    private final EmployeeView employeeView;
    private final AuthenticationService authenticationService;

    public EmployeeController(EmployeeView employeeView, AuthenticationService authenticationService) {
        this.employeeView = employeeView;
        this.authenticationService = authenticationService;
        employeeView.setAddClientListener(new EmployeeController.AddClientListener(employeeView.getClientCardNumberTf()));
        employeeView.setUpdateClientListener(new EmployeeController.UpdateClientListener());
        employeeView.setViewClientListener(new EmployeeController.ViewClientListener());
        employeeView.setCreateAccountListener(new EmployeeController.CreateAccountListener());
        employeeView.setUpdateAccountListener(new EmployeeController.UpdateAccountListener());
        employeeView.setDeleteAccountListener(new EmployeeController.DeleteAccountListener());
        employeeView.setViewAccountListener(new EmployeeController.ViewAccountListener());


    }

    private class AddClientListener implements ActionListener {

        private JTextField employeeView;

        public AddClientListener(JTextField employeeView) {
            super();
            this.employeeView = employeeView;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            String nameClient =employeeView.getText();

           String nrCardClient =employeeView.getText();
            int nrCard=Integer.parseInt(nrCardClient);

            String CNPClient =employeeView.getText();
            long CNP=Long.parseLong(CNPClient);

            String addressClient =employeeView.getText();


            System.out.println(nameClient);

        }

    }

    private class UpdateClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ViewClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class CreateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class UpdateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class DeleteAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ViewAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
