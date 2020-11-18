package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdministratorView extends JFrame {

    private JButton btnCreateEmployee;
    private JButton btnAddEmployee;
    private JButton btnSelectEmployee;
    private JButton btnUpdateEmployee;
    private JButton btnDeleteEmployee;


    private JLabel employeeTitle;
    private JLabel employeeNameLb;
    private JLabel employeeIdLb;
    private JLabel employeeAddressLb;


    private JTextField employeeNameTf;
    private JTextField employeeIdTf;
    private JTextField employeeAddressTf;

    public AdministratorView() throws HeadlessException {
        setSize(700, 600);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(1, 2));

        JPanel leftP = new JPanel();
        leftP.setLayout(new GridLayout(0, 1));

        leftP.add(btnCreateEmployee);
        leftP.add(btnAddEmployee);
        leftP.add(btnSelectEmployee);
        leftP.add(btnUpdateEmployee);
        leftP.add(btnDeleteEmployee);

        add(leftP);

        JPanel rightP = new JPanel();
        rightP.setLayout(new GridLayout(0, 1));
        rightP.add(employeeTitle);

        // Client info
        JPanel nameP = new JPanel();
        nameP.add(employeeNameLb);
        nameP.add(employeeNameTf);

        JPanel employeeIdP = new JPanel();
        employeeIdP.add(employeeIdLb);
        employeeIdP.add(employeeIdTf);

        JPanel employeeAddrP = new JPanel();
        employeeAddrP.add(employeeAddressLb);
        employeeAddrP.add(employeeAddressTf);

        rightP.add(nameP);
        rightP.add(employeeIdP);
        rightP.add(employeeAddrP);


        add(rightP);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {


        btnCreateEmployee = new JButton("Create Employee");
        btnAddEmployee = new JButton("Add Employee");
        btnSelectEmployee = new JButton("Select Employee");
        btnUpdateEmployee = new JButton("Update Employee");
        btnDeleteEmployee = new JButton("Delete Employee");


        employeeTitle = new JLabel("-- Employee information --");
        employeeNameLb = new JLabel("Name Employee: ");
        employeeNameTf = new JTextField(30);
        employeeNameTf.setPreferredSize(new Dimension(300, 25));

        employeeIdLb = new JLabel("Id Employee: ");
        employeeIdTf = new JTextField(30);
        employeeIdTf.setPreferredSize(new Dimension(300, 25));

        employeeAddressLb = new JLabel("Address Employee: ");
        employeeAddressTf = new JTextField(30);
        employeeAddressTf.setPreferredSize(new Dimension(300, 25));




    }



    public void setCreateEmployeeListener(ActionListener createEmployeeListener) {
        btnCreateEmployee.addActionListener(createEmployeeListener);
    }

    public void setAddEmployeeListener(ActionListener addEmployeeListener) {
        btnAddEmployee.addActionListener(addEmployeeListener);
    }

    public void setSelectEmployeeListener(ActionListener selectEmployeeListener) {
        btnSelectEmployee.addActionListener(selectEmployeeListener);
    }

    public void setUpdateEmployeeListener(ActionListener updateEmployeeListener) {
        btnUpdateEmployee.addActionListener(updateEmployeeListener);
    }

    public void setDeleteEmployeeListener(ActionListener deleteEmployeeListener) {
        btnDeleteEmployee.addActionListener(deleteEmployeeListener);
    }



}
