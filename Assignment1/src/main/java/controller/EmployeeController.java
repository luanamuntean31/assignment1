package controller;

import model.*;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static database.Constants.Roles.EMPLOYEE;


public class EmployeeController {


    private final EmployeeView employeeView;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;



    public EmployeeController(EmployeeView employeeView, ClientRepository clientRepository, AccountRepository accountRepository) {
        this.employeeView = employeeView;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        employeeView.setAddClientListener(new EmployeeController.AddClientListener());
        employeeView.setUpdateClientListener(new EmployeeController.UpdateClientListener());
        employeeView.setViewClientListener(new EmployeeController.ViewClientListener());
        employeeView.setCreateAccountListener(new EmployeeController.CreateAccountListener());
        employeeView.setUpdateAccountListener(new EmployeeController.UpdateAccountListener());
        employeeView.setDeleteAccountListener(new EmployeeController.DeleteAccountListener());
        employeeView.setViewAccountListener(new EmployeeController.ViewAccountListener());
        employeeView.setTransferMoneyListener(new EmployeeController.TransferMoneyListener());
        employeeView.setProcessBillsListener(new EmployeeController.ProcessBillsListener());


    }

    private class AddClientListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub


            String nameClient = employeeView.getClientNameTf().getText();

            String nrCardClient = employeeView.getClientCardNumberTf().getText();
            long nrCard = Long.parseLong(nrCardClient);

            String CNPClient = employeeView.getClientCNPTf().getText();
            long CNP = Long.parseLong(CNPClient);

            String addressClient = employeeView.getClientAddressTf().getText();

            if ((nameClient.isEmpty()) || (nrCardClient.isEmpty()) || (CNPClient.isEmpty()) || (addressClient.isEmpty())) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Not all fields are completed!");
                return;

            }

            Client client = new ClientBuilder()
                    .setName(nameClient)
                    .setIdCard(nrCard)
                    .setCNP(CNP)
                    .setAddress(addressClient)
                    .build();

            boolean savedClient = clientRepository.add(client);


            if (savedClient)

                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client data is saved!");
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors to save!");


        }

    }

    private class UpdateClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            String searchClientByName = employeeView.getSearchClientByNameTf().getText();


            Notification<Client> findClientByNameNotification = null;
            findClientByNameNotification = clientRepository.viewClient(searchClientByName);


            if (findClientByNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), findClientByNameNotification.getFormattedErrors());
            } else {


                Client currClient = findClientByNameNotification.getResult();

                //preluam id-ul clientului pe care l-am cautat dupa nume

                long currId = currClient.getId();

                String nameClient = employeeView.getClientNameTf().getText();

                String nrCardClient = employeeView.getClientCardNumberTf().getText();
                long nrCard = Long.parseLong(nrCardClient);

                String CNPClient = employeeView.getClientCNPTf().getText();
                long CNP = Long.parseLong(CNPClient);

                String addressClient = employeeView.getClientAddressTf().getText();


                Client updateClient = new ClientBuilder()
                        .setId(currId)
                        .setName(nameClient)
                        .setIdCard(nrCard)
                        .setCNP(CNP)
                        .setAddress(addressClient)
                        .build();


                boolean modifyClient = clientRepository.update(updateClient, currId);


                if (modifyClient)

                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client data has changed!");
                else
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors to save!");


            }

        }

    }


    private class ViewClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchClientByName = employeeView.getSearchClientByNameTf().getText();

            Notification<Client> findClientByNameNotification = null;
            findClientByNameNotification = clientRepository.viewClient(searchClientByName);


            if (findClientByNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), findClientByNameNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "The search by name was successful!");

                Client currClient = findClientByNameNotification.getResult();

                employeeView.setClientIdTf(currClient.getId());
                employeeView.setClientNameTf(currClient.getName());
                employeeView.setClientCardNumberTf(currClient.getIdCard());
                employeeView.setClientCNPTf(currClient.getCNP());
                employeeView.setClientAddressTf(currClient.getAddress());


            }

        }


    }


    private class CreateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchClientByName = employeeView.getSearchClientByNameTf().getText();

            Notification<Client> findClientByNameNotification = null;
            findClientByNameNotification = clientRepository.viewClient(searchClientByName);


            if (findClientByNameNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), findClientByNameNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "The search by name was successful!");

                Client currClient = findClientByNameNotification.getResult();


                long currId = currClient.getId();


                String typeAccount = employeeView.getAccTypeTf().getText();

                String amountAccount = employeeView.getAccMoneyTf().getText();
                long amount = Long.parseLong(amountAccount);

                String creationDate = employeeView.getAccCreationDateTf().getText();
                Date crDate = null;
                try {
                    crDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDate);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }


                Account account = new AccountBuilder()
                        .setAccountType(typeAccount)
                        .setAmount(amount)
                        .setCreationDate(crDate)
                        .build();

                boolean savedAccount = accountRepository.add(account, currId);


                if (savedAccount)

                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account data is saved!");
                else
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors to save!");

            }


        }
    }

    private class UpdateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


                String nrAccount = employeeView.getNrAccountTf().getText();
                long nrAcc = Long.parseLong(nrAccount);

                String typeAccount = employeeView.getAccTypeTf().getText();

                String amountAccount = employeeView.getAccMoneyTf().getText();
                long amount = Long.parseLong(amountAccount);

                String creationDate = employeeView.getAccCreationDateTf().getText();
                Date crDate = null;
                try {
                    crDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(creationDate);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }


                Account updateAccount = new AccountBuilder()
                        .setAccountType(typeAccount)
                        .setAmount(amount)
                        .setCreationDate(crDate)
                        .build();

                boolean modifyAccount = accountRepository.update(updateAccount, nrAcc);


                if (modifyAccount)

                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account data is changed!");
                else
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors to save!");



        }
    }

    private class DeleteAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


                String nrAccount = employeeView.getNrAccountTf().getText();
                long nrAcc = Long.parseLong(nrAccount);

                Notification<Account> account = accountRepository.viewAccount(nrAcc);

                boolean deleteAccount = accountRepository.delete(account, nrAcc);

                if (deleteAccount)

                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account data has deleted!");
                else
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors at delete!");


        }

    }


    private class ViewAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


                String nrAccountTf = employeeView.getNrAccountTf().getText();
                long nrAccTf = Long.parseLong(nrAccountTf);

                Notification<Account> account = accountRepository.viewAccount(nrAccTf);

                long nrAcc = account.getResult().getAccountNumber();
                String typeAcc = account.getResult().getAccountType();
                Long moneyAcc = account.getResult().getAmount();
                Date crDateAcc = account.getResult().getCreationDate();


                employeeView.setNrAccountTf(nrAcc);
                employeeView.setAccTypeTf(typeAcc);
                employeeView.setAccMoneyTf(moneyAcc);
                employeeView.setAccCreationDateTf(crDateAcc);




        }
    }


    public class TransferMoneyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //preluam nr contului din care se transfera bani
            String nrAccountTransfer = employeeView.getNrAccountTransferTf().getText();
            long nrAccTransfer = Long.parseLong(nrAccountTransfer);


                Notification<Account> account1 = accountRepository.viewAccount(nrAccTransfer);

                //datele contului clientului care transfera bani

                String typeAccTransfer = account1.getResult().getAccountType();

                Long moneyAccTransfer = account1.getResult().getAmount();

                Date crDateAccTransfer = account1.getResult().getCreationDate();



             //preluam nr contului in care se transfera bani
            String nrAccountReceive = employeeView.getNrAccountTf().getText();
            long nrAccReceive = Long.parseLong(nrAccountReceive);

                Notification<Account> account2 = accountRepository.viewAccount(nrAccReceive);
                //datele contului clientului care primeste bani

                String typeAccReceive = account2.getResult().getAccountType();

                Long moneyAccReceive = account2.getResult().getAmount();

                Date crDateAccReceive = account2.getResult().getCreationDate();

                //suma pe care vrem sa o transferam
                String amountAccount = employeeView.getAccMoneyTf().getText();
                long amountTransfer = Long.parseLong(amountAccount);


                if (moneyAccTransfer >= amountTransfer) {
                    moneyAccTransfer = moneyAccTransfer - amountTransfer;
                    //refacem campurile clientului care transfera bani
                    Account updateAccount1 = new AccountBuilder()
                            .setAccountNumber(nrAccTransfer)
                            .setAccountType(typeAccTransfer)
                            .setAmount(moneyAccTransfer)
                            .setCreationDate(crDateAccTransfer)
                            .build();

                    boolean modifyAccount1 = accountRepository.update(updateAccount1, nrAccTransfer);

                    if (modifyAccount1) {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account data that transfers money is changed!");
                    } else {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors to save!");
                    }

                    //refacem campurile clientului care primeste bani
                    moneyAccReceive = moneyAccReceive + amountTransfer;
                    Account updateAccount2 = new AccountBuilder()
                            .setAccountNumber(nrAccReceive)
                            .setAccountType(typeAccReceive)
                            .setAmount(moneyAccReceive)
                            .setCreationDate(crDateAccReceive)
                            .build();

                    boolean modifyAccount2 = accountRepository.update(updateAccount2, nrAccReceive);

                    if (modifyAccount2) {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account data that receives money is changed!");
                    } else {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors to save!");
                    }


                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Insufficient amount for transfer!");
                }


            }


        }


    public class ProcessBillsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e1) {

            String utilityBill = employeeView.getPaymentAmountTf().getText();
            long amountBill = Long.parseLong(utilityBill);


            String nrAccountExtraction = employeeView.getNrAccountTf().getText();
            long nrAccExtraction = Long.parseLong(nrAccountExtraction);

            Notification<Account> accountExtraction = accountRepository.viewAccount(nrAccExtraction);


            String typeAccExtraction = accountExtraction.getResult().getAccountType();

            Long moneyAccExtraction = accountExtraction.getResult().getAmount();

            Date crDateAccExtraction = accountExtraction.getResult().getCreationDate();


                  if(moneyAccExtraction>=amountBill)
                  {
                      moneyAccExtraction=moneyAccExtraction-amountBill;
                      Account updateAccountExtraction = new AccountBuilder()
                              .setAccountNumber(nrAccExtraction)
                              .setAccountType(typeAccExtraction)
                              .setAmount(moneyAccExtraction)
                              .setCreationDate(crDateAccExtraction)
                              .build();

                      boolean modifyAccountExtraction = accountRepository.update(updateAccountExtraction, nrAccExtraction);

                      if (modifyAccountExtraction) {
                          JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account data is changed!");
                      } else {
                          JOptionPane.showMessageDialog(employeeView.getContentPane(), "Errors to save!");
                      }

                  }
                  else
                  {
                      JOptionPane.showMessageDialog(employeeView.getContentPane(), "The bill can not be paid!");

                  }


                int nrBill=1;
                try {

                    File file = new File("Bill.txt");

                    if(!file.exists())
                    {
                        file.createNewFile();
                    }


                    PrintWriter writer=new PrintWriter(file);
                    writer.println("Bill number: " +  nrBill);
                    nrBill++;
                    writer.println("Bill with value: " + amountBill + " was paid!");
                    writer.println("The account number from which money was extraction is: " + nrAccExtraction);
                    writer.println("The amount includes the bill at : E.on, Electrica and Telekom");


                    writer.close();


                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



        }
    }
}
