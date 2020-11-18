package controller;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.AdministratorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorController {

    private final AdministratorView administratorView;
    private final AuthenticationService authenticationService;

    public AdministratorController(AdministratorView administratorView, AuthenticationService authenticationService) {
        this.administratorView = administratorView;
        this.authenticationService = authenticationService;
        administratorView.setCreateEmployeeListener(new AdministratorController.CreateEmployeeListener());
        administratorView.setAddEmployeeListener(new AdministratorController.AddEmployeeListener());
        administratorView.setSelectEmployeeListener(new AdministratorController.SelectEmployeeListener());
        administratorView.setUpdateEmployeeListener(new AdministratorController.UpdateEmployeeListener());
        administratorView.setDeleteEmployeeListener(new AdministratorController.DeleteEmployeeListener());



    }

    private class CreateEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class AddEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class SelectEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class UpdateEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class DeleteEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
