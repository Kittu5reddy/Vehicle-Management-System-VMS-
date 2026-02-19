package com.vms.ui;

import com.vms.controller.VehicleController;
import com.vms.model.Bus;
import com.vms.model.Car;
import com.vms.model.Truck;
import com.vms.model.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class VehiclesPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private VehicleController controller;

    public VehiclesPanel() {

        controller = new VehicleController(this);

        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(18, 18, 12, 18));

        JLabel title = new JLabel("Vehicles");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JButton addBtn = new JButton("+ Add Vehicle");
        Theme.styleButtonPrimary(addBtn);

        header.add(title, BorderLayout.WEST);
        header.add(addBtn, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ===== TABLE =====
        String[] cols = {
                "ID",                 // hidden
                "Vehicle Number",
                "Brand",
                "Model",
                "Fuel Type",
                "Status",
                "Purchase Date"
        };

        model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(34);

        TableColumnModel cm = table.getColumnModel();

        // Hide ID column
        cm.getColumn(0).setMinWidth(0);
        cm.getColumn(0).setMaxWidth(0);
        cm.getColumn(0).setWidth(0);

        // Status renderer
        cm.getColumn(5).setCellRenderer(new VehicleRowRenderer());

        JPanel wrap = Theme.cardPanel();
        wrap.setLayout(new BorderLayout());
        wrap.add(new JScrollPane(table));

        add(wrap, BorderLayout.CENTER);

        addBtn.addActionListener(e -> showAddDialog());

        // Load data from DB
        controller.loadVehicles();
        table.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int row = table.getSelectedRow();

                    if (row >= 0) {

                        Integer id = (Integer) model.getValueAt(row, 0);

                        Vehicle vehicle = controller.getVehicleById(id);

                        showEditDialog(vehicle);

                    }
                }
            }
        });

    }
    private void showEditDialog(Vehicle v) {

        JTextField numberField = new JTextField(v.getRegistrationNumber());
        JTextField brandField = new JTextField(v.getBrandName());
        JTextField modelField = new JTextField(v.getModel());

        JComboBox<String> fuelCombo =
                new JComboBox<>(new String[]{"PETROL","DIESEL","CNG","EV"});
        fuelCombo.setSelectedItem(v.getFuelType().name());

        JComboBox<String> statusCombo =
                new JComboBox<>(new String[]{"AVAILABLE","ASSIGNED","MAINTENANCE","INACTIVE"});
        statusCombo.setSelectedItem(v.getStatus().name());

        JComboBox<String> typeCombo =
                new JComboBox<>(new String[]{"CAR","TRUCK","BUS"});

        JTextField purchaseField =
                new JTextField(v.getPurchaseDate().toString());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(new JLabel("Vehicle Number"));
        panel.add(numberField);
        panel.add(new JLabel("Brand"));
        panel.add(brandField);
        panel.add(new JLabel("Model"));
        panel.add(modelField);
        panel.add(new JLabel("Fuel Type"));
        panel.add(fuelCombo);
        panel.add(new JLabel("Vehicle Type"));
        panel.add(typeCombo);
        panel.add(new JLabel("Status"));
        panel.add(statusCombo);
        panel.add(new JLabel("Purchase Date"));
        panel.add(purchaseField);

        JPanel dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel,BoxLayout.Y_AXIS));
        panel.add(dynamicPanel);

        JTextField doorsField = new JTextField();
        JTextField bootField = new JTextField();
        JTextField loadField = new JTextField();
        JTextField axleField = new JTextField();
        JTextField seatingField = new JTextField();
        JTextField standingField = new JTextField();

        // 🔥 Detect actual subtype
        if(v instanceof Car car){

            typeCombo.setSelectedItem("CAR");

            doorsField.setText(String.valueOf(car.getNumberOfDoors()));
            bootField.setText(String.valueOf(car.getBootSpace()));

            dynamicPanel.add(new JLabel("Number Of Doors"));
            dynamicPanel.add(doorsField);
            dynamicPanel.add(new JLabel("Boot Space"));
            dynamicPanel.add(bootField);

        }
        else if(v instanceof Truck truck){

            typeCombo.setSelectedItem("TRUCK");

            loadField.setText(String.valueOf(truck.getLoadCapacity()));
            axleField.setText(String.valueOf(truck.getNumberOfAxles()));

            dynamicPanel.add(new JLabel("Load Capacity"));
            dynamicPanel.add(loadField);
            dynamicPanel.add(new JLabel("Number Of Axles"));
            dynamicPanel.add(axleField);

        }
        else if(v instanceof Bus bus){

            typeCombo.setSelectedItem("BUS");

            seatingField.setText(String.valueOf(bus.getSeatingCapacity()));
            standingField.setText(String.valueOf(bus.getStandingCapacity()));

            dynamicPanel.add(new JLabel("Seating Capacity"));
            dynamicPanel.add(seatingField);
            dynamicPanel.add(new JLabel("Standing Capacity"));
            dynamicPanel.add(standingField);
        }

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Vehicle",
                JOptionPane.OK_CANCEL_OPTION
        );

        if(result == JOptionPane.OK_OPTION){

            controller.updateVehicleWithType(
                    v.getId(),
                    typeCombo.getSelectedItem().toString(),
                    numberField.getText(),
                    brandField.getText(),
                    modelField.getText(),
                    fuelCombo.getSelectedItem().toString(),
                    statusCombo.getSelectedItem().toString(),
                    purchaseField.getText(),
                    doorsField.getText(),
                    bootField.getText(),
                    loadField.getText(),
                    axleField.getText(),
                    seatingField.getText(),
                    standingField.getText()
            );
        }
    }

    // ==============================
    // ADD VEHICLE DIALOG
    // ==============================
    private void showAddDialog() {

        JTextField numberField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField modelField = new JTextField();

        JComboBox<String> fuelCombo =
                new JComboBox<>(new String[]{"PETROL","DIESEL","CNG","EV"});

        JComboBox<String> statusCombo =
                new JComboBox<>(new String[]{"AVAILABLE","ASSIGNED","MAINTENANCE","INACTIVE"});

        JComboBox<String> typeCombo =
                new JComboBox<>(new String[]{"CAR","TRUCK","BUS"});

        JTextField purchaseField = new JTextField("2026-02-20T10:00");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(new JLabel("Vehicle Number"));
        panel.add(numberField);

        panel.add(new JLabel("Brand"));
        panel.add(brandField);

        panel.add(new JLabel("Model"));
        panel.add(modelField);

        panel.add(new JLabel("Fuel Type"));
        panel.add(fuelCombo);

        panel.add(new JLabel("Vehicle Type"));
        panel.add(typeCombo);

        panel.add(new JLabel("Status"));
        panel.add(statusCombo);

        panel.add(new JLabel("Purchase Date"));
        panel.add(purchaseField);

        // ⭐ dynamic fields container
        JPanel dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel,BoxLayout.Y_AXIS));
        panel.add(dynamicPanel);

        // CAR fields
        JTextField doorsField = new JTextField();
        JTextField bootField = new JTextField();

        // TRUCK fields
        JTextField loadField = new JTextField();
        JTextField axleField = new JTextField();

        // BUS fields
        JTextField seatingField = new JTextField();
        JTextField standingField = new JTextField();

        Runnable updateDynamicFields = () -> {

            dynamicPanel.removeAll();

            String type = typeCombo.getSelectedItem().toString();

            if(type.equals("CAR")){

                dynamicPanel.add(new JLabel("Number Of Doors"));
                dynamicPanel.add(doorsField);

                dynamicPanel.add(new JLabel("Boot Space"));
                dynamicPanel.add(bootField);

            } else if(type.equals("TRUCK")){

                dynamicPanel.add(new JLabel("Load Capacity"));
                dynamicPanel.add(loadField);

                dynamicPanel.add(new JLabel("Number Of Axles"));
                dynamicPanel.add(axleField);

            } else if(type.equals("BUS")){

                dynamicPanel.add(new JLabel("Seating Capacity"));
                dynamicPanel.add(seatingField);

                dynamicPanel.add(new JLabel("Standing Capacity"));
                dynamicPanel.add(standingField);
            }

            dynamicPanel.revalidate();
            dynamicPanel.repaint();
        };

        typeCombo.addActionListener(e -> updateDynamicFields.run());

        updateDynamicFields.run();

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add Vehicle",
                JOptionPane.OK_CANCEL_OPTION
        );

        if(result == JOptionPane.OK_OPTION){

            controller.createVehicleWithType(

                    typeCombo.getSelectedItem().toString(),
                    numberField.getText(),
                    brandField.getText(),
                    modelField.getText(),
                    fuelCombo.getSelectedItem().toString(),
                    statusCombo.getSelectedItem().toString(),
                    purchaseField.getText(),

                    doorsField.getText(),
                    bootField.getText(),
                    loadField.getText(),
                    axleField.getText(),
                    seatingField.getText(),
                    standingField.getText()
            );
        }
    }

    // ==============================
    // METHODS CALLED BY CONTROLLER
    // ==============================

    public void clearTable() {
        model.setRowCount(0);
    }

    public void addVehicleRow(Object[] row) {
        model.addRow(row);
    }
}
