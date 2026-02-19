package com.vms.ui;

import com.vms.controller.DriverController;
import com.vms.enums.Gender;
import com.vms.model.Driver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class DriversPanel extends JPanel {

    private DefaultTableModel model;
    private DriverController controller;
    private JTable table;

    public DriversPanel() {

        controller = new DriverController(this);

        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);

        // ===== HEADER =====

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Drivers");
        title.setFont(new Font("Segoe UI", Font.BOLD,20));

        JButton addBtn = new JButton("+ Add Driver");
        Theme.styleButtonPrimary(addBtn);

        header.add(title,BorderLayout.WEST);
        header.add(addBtn,BorderLayout.EAST);

        add(header,BorderLayout.NORTH);

        // ===== TABLE =====

        String[] cols = {
                "ID","Name","Licence","Expiry","Status"
        };

        model = new DefaultTableModel(cols,0){
            public boolean isCellEditable(int r,int c){return false;}
        };

        table = new JTable(model);
        table.setRowHeight(34);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        table.getColumnModel().getColumn(4)
                .setCellRenderer(new DriverStatusRenderer());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if(e.getClickCount()==2){

                    int row = table.getSelectedRow();

                    if(row>=0){

                        Integer id = (Integer) model.getValueAt(row,0);
                        Driver d = controller.getDriverById(id);

                        openDriverDialog(d);
                    }
                }
            }
        });

        JPanel wrap = Theme.cardPanel();
        wrap.setLayout(new BorderLayout());
        wrap.add(new JScrollPane(table));

        add(wrap,BorderLayout.CENTER);

        addBtn.addActionListener(e -> openDriverDialog(null));

        controller.loadDrivers();
    }

    // ================= FORM =================

    private void openDriverDialog(Driver d){

        boolean editMode = d != null;

        JTextField fullName = createField(editMode ? d.getFullName() : "");
        JTextField userName = createField(editMode ? d.getUserName() : "");
        JTextField password = createField("");
        JTextField licenseNumber = createField(editMode ? d.getLicenseNumber() : "");

        // ⭐ Calendar for expiry
        JSpinner expiryDate = new JSpinner(new SpinnerDateModel());
        expiryDate.setEditor(new JSpinner.DateEditor(expiryDate,"yyyy-MM-dd HH:mm"));

        // ⭐ Calendar for DOB
        JSpinner dob = new JSpinner(new SpinnerDateModel());
        dob.setEditor(new JSpinner.DateEditor(dob,"yyyy-MM-dd HH:mm"));

        JComboBox<Gender> gender =
                new JComboBox<>(Gender.values());

        JTextField phone = createField(editMode ? d.getPhoneNumber() : "");
        JTextField address = createField(editMode ? d.getAddress() : "");
        JTextField email = createField(editMode ? d.getEmail() : "");
        JTextField experience = createField(editMode ? String.valueOf(d.getExperienceYears()) : "");
        JTextField rating = createField(editMode ? String.valueOf(d.getRating()) : "");
        JTextField amount = createField(editMode ? String.valueOf(d.getAmountPerTrip()) : "");

        JComboBox<String> status =
                new JComboBox<>(new String[]{"Assigned","Available","Not available"});

        if(editMode){
            status.setSelectedItem(d.getAvailable() ? "Available" : "Assigned");
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(label("Full Name")); panel.add(fullName);
        panel.add(label("Username")); panel.add(userName);
        panel.add(label("Password")); panel.add(password);
        panel.add(label("License Number")); panel.add(licenseNumber);
        panel.add(label("License Expiry")); panel.add(expiryDate);
        panel.add(label("DOB")); panel.add(dob);
        panel.add(label("Gender")); panel.add(gender);
        panel.add(label("Phone")); panel.add(phone);
        panel.add(label("Address")); panel.add(address);
        panel.add(label("Email")); panel.add(email);
        panel.add(label("Experience")); panel.add(experience);
        panel.add(label("Rating")); panel.add(rating);
        panel.add(label("Amount Per Trip")); panel.add(amount);
        panel.add(label("Status")); panel.add(status);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                editMode ? "Edit Driver" : "Add Driver",
                JOptionPane.OK_CANCEL_OPTION
        );

        if(result == JOptionPane.OK_OPTION){

            Date expiry = (Date) expiryDate.getValue();
            Date dobDate = (Date) dob.getValue();


            if(editMode){

                controller.updateDriver(
                        d.getId(),
                        fullName.getText(),
                        userName.getText(),
                        password.getText(),
                        licenseNumber.getText(),
                        expiry,
                        (Gender) gender.getSelectedItem(),
                        phone.getText(),
                        address.getText(),
                        email.getText(),
                        dobDate,
                        Integer.parseInt(experience.getText()),
                        Float.parseFloat(rating.getText()),
                        Float.parseFloat(amount.getText()),
                        status.getSelectedItem().toString()
                );


            }else{

                controller.createDriver(
                        fullName.getText(),
                        userName.getText(),
                        password.getText(),
                        licenseNumber.getText(),
                        expiry,
                        (Gender) gender.getSelectedItem(),
                        phone.getText(),
                        address.getText(),
                        email.getText(),
                        dobDate,
                        Integer.parseInt(experience.getText()),
                        Float.parseFloat(rating.getText()),
                        Float.parseFloat(amount.getText()),
                        status.getSelectedItem().toString()
                );
            }
        }
    }

    private JLabel label(String t){ return new JLabel(t); }

    private JTextField createField(String val){
        JTextField f = new JTextField(val);
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE,36));
        return f;
    }

    public void clearTable(){ model.setRowCount(0); }

    public void addDriverRow(Object[] row){ model.addRow(row); }
}
