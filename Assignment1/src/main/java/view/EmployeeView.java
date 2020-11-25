package view;

import controller.EmployeeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame {


    private JButton btnAddClient;
    private JButton btnUpdateClient;
    private JButton btnViewClient;
    private JButton btnCreateAccount;
    private JButton btnUpdateAccount;
    private JButton btnDeleteAccount;
    private JButton btnViewAccount;
    private JButton btntransferMoney;
    private JButton btnprocessBills;

    private JLabel searchClientByNameLb;
    private JTextField searchClientByNameTf;

    private JLabel nrAccountTransferLb;
    private JTextField nrAccountTransferTf;

    private JLabel paymentAmountLb;
    private JTextField paymentAmountTf;

    private JLabel clientTitle;

    private JLabel clientIdLb;
    private JLabel clientNameLb;
    private JLabel clientCardNumberLb;
    private JLabel clientCNPLb;
    private JLabel clientAddressLb;

    private JLabel accountTitle;

    private JLabel nrAccountLb;

    private JLabel accTypeLb;
    private JLabel accMoneyLb;
    private JLabel accCreationDateLb;

    private JTextField clientIdTf;
    private JTextField clientNameTf;
    private JTextField clientCardNumberTf;
    private JTextField clientCNPTf;
    private JTextField clientAddressTf;

    private JTextField nrAccountTf;
    private JTextField accTypeTf;
    private JTextField accMoneyTf;
    private JTextField accCreationDateTf;



    public EmployeeView() throws HeadlessException {


        setSize(700, 800);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(1, 2));

        JPanel leftP = new JPanel();
        leftP.setLayout(new GridLayout(0, 1));

        leftP.add(btnAddClient);
        leftP.add(btnUpdateClient);
        leftP.add(btnViewClient);
        leftP.add(btnCreateAccount);
        leftP.add(btnUpdateAccount);
        leftP.add(btnDeleteAccount);
        leftP.add(btnViewAccount);
        leftP.add(btntransferMoney);
        leftP.add(btnprocessBills);

        add(leftP);


        JPanel rightP = new JPanel();
        rightP.setLayout(new GridLayout(0, 1));

        JPanel searchClientP = new JPanel();
        searchClientP.add(searchClientByNameLb);
        searchClientP.add(searchClientByNameTf);

        rightP.add(searchClientP);

        JPanel nrAccTransferP = new JPanel();
        nrAccTransferP.add(nrAccountTransferLb);
        nrAccTransferP.add(nrAccountTransferTf);

        rightP.add(nrAccTransferP);

        JPanel paymentAmountP = new JPanel();
        paymentAmountP.add(paymentAmountLb);
        paymentAmountP.add(paymentAmountTf);

        rightP.add(paymentAmountP);


        rightP.add(clientTitle);

        // Client info
        JPanel idP = new JPanel();
        idP.add(clientIdLb);
        idP.add(clientIdTf);
        JPanel nameP = new JPanel();
        nameP.add(clientNameLb);
        nameP.add(clientNameTf);
        JPanel cardNumbP = new JPanel();
        cardNumbP.add(clientCardNumberLb);
        cardNumbP.add(clientCardNumberTf);
        JPanel clientCnpP = new JPanel();
        clientCnpP.add(clientCNPLb);
        clientCnpP.add(clientCNPTf);
        JPanel clientAddrP = new JPanel();
        clientAddrP.add(clientAddressLb);
        clientAddrP.add(clientAddressTf);

        rightP.add(idP);
        rightP.add(nameP);
        rightP.add(cardNumbP);
        rightP.add(clientCnpP);
        rightP.add(clientAddrP);

        // Account info
        JPanel accIdP = new JPanel();
        JPanel accTypeP = new JPanel();
        JPanel accMoneyP = new JPanel();
        JPanel accCreationDateP = new JPanel();
        accIdP.add(nrAccountLb);
        accIdP.add(nrAccountTf);
        accTypeP.add(accTypeLb);
        accTypeP.add(accTypeTf);
        accMoneyP.add(accMoneyLb);
        accMoneyP.add(accMoneyTf);
        accCreationDateP.add(accCreationDateLb);
        accCreationDateP.add(accCreationDateTf);

        rightP.add(accountTitle);
        rightP.add(accIdP);
        rightP.add(accTypeP);
        rightP.add(accMoneyP);
        rightP.add(accCreationDateP);


        add(rightP);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setVisible(true);
    }

    private void initializeFields() {


        btnAddClient = new JButton("Add Client");
        btnUpdateClient = new JButton("Update Client");
        btnViewClient = new JButton("View Client");
        btnCreateAccount = new JButton("Create Account");
        btnUpdateAccount = new JButton("Update Account");
        btnDeleteAccount = new JButton("Delete Account");
        btnViewAccount = new JButton("View Account");
        btntransferMoney = new JButton("Transfer money between accounts");
        btnprocessBills = new JButton("Process utility bills");

        searchClientByNameLb = new JLabel("Searching client (name): ");
        searchClientByNameTf = new JTextField(30);
        searchClientByNameTf.setPreferredSize(new Dimension(300, 25));

        nrAccountTransferLb = new JLabel("Number Account (transfer): ");
        nrAccountTransferTf = new JTextField(30);
        nrAccountTransferTf.setPreferredSize(new Dimension(300, 25));


        paymentAmountLb = new JLabel("Payment Amount (bill): ");
        paymentAmountTf = new JTextField(30);
        paymentAmountLb.setPreferredSize(new Dimension(300, 25));

        clientTitle = new JLabel("-- Client information --");

        clientIdLb = new JLabel("Id Client: ");
        clientIdTf = new JTextField(30);
        clientIdTf.setPreferredSize(new Dimension(300, 25));

        clientNameLb = new JLabel("Name: ");
        clientNameTf = new JTextField(30);
        clientNameTf.setPreferredSize(new Dimension(300, 25));

        clientCardNumberLb = new JLabel("Card Number: ");
        clientCardNumberTf = new JTextField(30);
        clientCardNumberTf.setPreferredSize(new Dimension(300, 25));

        clientCNPLb = new JLabel("CNP: ");
        clientCNPTf = new JTextField(30);
        clientCNPTf.setPreferredSize(new Dimension(300, 25));

        clientAddressLb = new JLabel("Address: ");
        clientAddressTf = new JTextField(30);
        clientAddressTf.setPreferredSize(new Dimension(300, 25));

        accountTitle = new JLabel("-- Account information --");

        nrAccountLb = new JLabel("Number Account: ");
        nrAccountTf = new JTextField(30);
        nrAccountTf.setPreferredSize(new Dimension(300, 25));


        accTypeLb = new JLabel("Type:");
        accTypeTf = new JTextField(30);
        clientAddressTf.setPreferredSize(new Dimension(300, 25));

        accMoneyLb = new JLabel("Amount of money:");
        accMoneyTf = new JTextField(30);
        clientAddressTf.setPreferredSize(new Dimension(300, 25));

        accCreationDateLb = new JLabel("Creation date:");
        accCreationDateTf = new JTextField(30);
        clientAddressTf.setPreferredSize(new Dimension(300, 25));



    }

    public void setSearchClientByNameTf(JTextField searchClientByNameTf) { this.searchClientByNameTf = searchClientByNameTf; }



    public void setClientIdTf(Long clientId) {
       clientIdTf.setText(Long.toString(clientId));
    }

    public void setClientNameTf(String clientName) {
        clientNameTf.setText(clientName);
    }

    public void setClientCardNumberTf(Long cardNumber) {
        clientCardNumberTf.setText(Long.toString(cardNumber));
    }

    public void setClientCNPTf(Long clientCNP) {
        clientCNPTf.setText(Long.toString(clientCNP));
    }

    public void setClientAddressTf(String clientAddress) {
        clientAddressTf.setText(clientAddress);
    }



    public void setNrAccountTf(Long nrAccount) { nrAccountTf.setText(Long.toString(nrAccount)); }

    public void setAccTypeTf(String accType) { accTypeTf.setText(accType); }

    public void setAccMoneyTf(Long accMoney) { accMoneyTf.setText(Long.toString(accMoney)); }

    public void setAccCreationDateTf(Date accCreationDate) {

        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy ");
        String crDate = dateFormat.format(accCreationDate);

        accCreationDateTf.setText(crDate);
    }



    public JTextField getSearchClientByNameTf() { return searchClientByNameTf; }

    public JTextField getNrAccountTransferTf() { return nrAccountTransferTf; }

    public JTextField getPaymentAmountTf() { return paymentAmountTf; }

    public JTextField getClientIdTf() {
        return clientIdTf;
    }

    public JTextField getClientNameTf() {
        return clientNameTf;
    }

    public JTextField getClientCardNumberTf() {
        return clientCardNumberTf;
    }

    public JTextField getClientCNPTf() {
        return clientCNPTf;
    }

    public JTextField getClientAddressTf() {
        return clientAddressTf;
    }

    public JTextField getNrAccountTf() { return nrAccountTf; }

    public JTextField getAccTypeTf() { return accTypeTf; }

    public JTextField getAccMoneyTf() { return accMoneyTf; }

    public JTextField getAccCreationDateTf() { return accCreationDateTf; }


    public void setAddClientListener(ActionListener addClientListener) {
        btnAddClient.addActionListener(addClientListener);
    }

    public void setUpdateClientListener(ActionListener updateClientListener) {
        btnUpdateClient.addActionListener(updateClientListener);
    }

    public void setViewClientListener(ActionListener viewClientListener) {
        btnViewClient.addActionListener(viewClientListener);
    }

    public void setCreateAccountListener(ActionListener createAccountListener) {
        btnCreateAccount.addActionListener(createAccountListener);
    }

    public void setUpdateAccountListener(ActionListener updateAccountListener) {
        btnUpdateAccount.addActionListener(updateAccountListener);
    }

    public void setDeleteAccountListener(ActionListener deleteAccountListener) {
        btnDeleteAccount.addActionListener(deleteAccountListener);
    }

    public void setViewAccountListener(ActionListener viewAccountListener) {
        btnViewAccount.addActionListener(viewAccountListener);
    }

    public void setTransferMoneyListener(ActionListener transferMoneyListener) {
        btntransferMoney.addActionListener(transferMoneyListener);
    }

    public void setProcessBillsListener(ActionListener processBillsListener) {
        btnprocessBills.addActionListener(processBillsListener);
    }


}
