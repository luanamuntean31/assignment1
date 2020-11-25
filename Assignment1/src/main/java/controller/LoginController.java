package controller;

import model.Role;
import model.User;
import model.validation.Notification;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import repository.employee.EmployeeRepository;
import repository.security.RightsRolesRepository;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.AdministratorView;
import view.EmployeeView;
import view.LoginView;
import static database.Constants.Roles.EMPLOYEE;

import javax.swing.*;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final RightsRolesRepository rightsRolesRepository;
    private final EmployeeView employeeView;
    private final AdministratorView administratorView;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;



    public LoginController(LoginView loginView, AuthenticationService authenticationService, RightsRolesRepository rightsRolesRepository,ClientRepository clientRepository,
    AccountRepository accountRepository,EmployeeRepository employeeRepository) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.rightsRolesRepository=rightsRolesRepository;
        this.employeeView = new EmployeeView();
        this.administratorView=new AdministratorView();
        this.clientRepository=clientRepository;
        this.accountRepository=accountRepository;
        this.employeeRepository=employeeRepository;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");

                    User currUser = loginNotification.getResult();


                    System.out.println(currUser.getId());

                    Role role = rightsRolesRepository.findRolesForUser(currUser.getId()).get(0);
                    if(role.getRole().equals(EMPLOYEE)){
                        employeeView.setVisible(true);
                        EmployeeController employeeController=new EmployeeController(employeeView,clientRepository,accountRepository);

                    }else{
                        administratorView.setVisible(true);
                        AdministratorController administratorController=new AdministratorController(administratorView,employeeRepository);


                    }
                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


}
