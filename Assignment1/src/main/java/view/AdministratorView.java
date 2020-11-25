package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdministratorView extends JFrame {

    private JButton btnCreateEmployee;
    private JButton btnViewEmployee;
    private JButton btnUpdateEmployee;
    private JButton btnDeleteEmployee;
    private JButton btnGenerateReports;


    private JLabel searchEmployeeByNameLb;
    private JTextField searchEmployeeByNameTf;


    private JLabel employeeTitle;
    private JLabel employeeIdLb;
    private JLabel employeeNameLb;
    private JLabel employeeAddressLb;


    private JTextField employeeIdTf;
    private JTextField employeeNameTf;
    private JTextField employeeAddressTf;

    public AdministratorView() throws HeadlessException {
        setSize(700, 600);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(1, 2));

        JPanel leftP = new JPanel();
        leftP.setLayout(new GridLayout(0, 1));

        leftP.add(btnCreateEmployee);
        leftP.add(btnViewEmployee);
        leftP.add(btnUpdateEmployee);
        leftP.add(btnDeleteEmployee);
        leftP.add(btnGenerateReports);

        add(leftP);

        JPanel rightP = new JPanel();
        rightP.setLayout(new GridLayout(0, 1));

        JPanel searchEmployeeP = new JPanel();
        searchEmployeeP.add(searchEmployeeByNameLb);
        searchEmployeeP.add(searchEmployeeByNameTf);

        rightP.add(searchEmployeeP);

        rightP.add(employeeTitle);

        // Employee info

        JPanel employeeIdP = new JPanel();
        employeeIdP.add(employeeIdLb);
        employeeIdP.add(employeeIdTf);

        JPanel nameP = new JPanel();
        nameP.add(employeeNameLb);
        nameP.add(employeeNameTf);

        JPanel employeeAddrP = new JPanel();
        employeeAddrP.add(employeeAddressLb);
        employeeAddrP.add(employeeAddressTf);

        rightP.add(employeeIdP);
        rightP.add(nameP);
        rightP.add(employeeAddrP);


        add(rightP);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setVisible(true);
    }

    private void initializeFields() {


        btnCreateEmployee = new JButton("Create Employee");
        btnViewEmployee = new JButton("View Employee");
        btnUpdateEmployee = new JButton("Update Employee");
        btnDeleteEmployee = new JButton("Delete Employee");
        btnGenerateReports = new JButton("Generate Reports");


        searchEmployeeByNameLb = new JLabel("Searching employee (name): ");
        searchEmployeeByNameTf = new JTextField(30);
        searchEmployeeByNameTf.setPreferredSize(new Dimension(300, 25));

        employeeTitle = new JLabel("-- Employee information --");

        employeeIdLb = new JLabel("Id Employee: ");
        employeeIdTf = new JTextField(30);
        employeeIdTf.setPreferredSize(new Dimension(300, 25));

        employeeNameLb = new JLabel("Name Employee: ");
        employeeNameTf = new JTextField(30);
        employeeNameTf.setPreferredSize(new Dimension(300, 25));


        employeeAddressLb = new JLabel("Address Employee: ");
        employeeAddressTf = new JTextField(30);
        employeeAddressTf.setPreferredSize(new Dimension(300, 25));




    }

    public void setSearchEmployeeByNameTf(JTextField searchEmployeeByNameTf) {
        this.searchEmployeeByNameTf = searchEmployeeByNameTf;
    }

    public void setEmployeeIdTf(Long employeeId) {
        employeeIdTf.setText(Long.toString(employeeId));
    }

    public void setEmployeeNameTf(String employeeName) {
        employeeNameTf.setText(employeeName);
    }

    public void setEmployeeAddressTf(String employeeAddress) {
        employeeAddressTf.setText(employeeAddress);
    }

    public JTextField getSearchEmployeeByNameTf() {
        return searchEmployeeByNameTf;
    }

    public JTextField getEmployeeIdTf() {
        return employeeIdTf;
    }

    public JTextField getEmployeeNameTf() {
        return employeeNameTf;
    }


    public JTextField getEmployeeAddressTf() {
        return employeeAddressTf;
    }

    public void setCreateEmployeeListener(ActionListener createEmployeeListener) {
        btnCreateEmployee.addActionListener(createEmployeeListener);
    }


    public void setViewEmployeeListener(ActionListener viewEmployeeListener) {
        btnViewEmployee.addActionListener(viewEmployeeListener);
    }

    public void setUpdateEmployeeListener(ActionListener updateEmployeeListener) {
        btnUpdateEmployee.addActionListener(updateEmployeeListener);
    }

    public void setDeleteEmployeeListener(ActionListener deleteEmployeeListener) {
        btnDeleteEmployee.addActionListener(deleteEmployeeListener);
    }

    public void setGenerateReportsListener(ActionListener generateReportsListener) {
        btnGenerateReports.addActionListener(generateReportsListener);
    }



}
