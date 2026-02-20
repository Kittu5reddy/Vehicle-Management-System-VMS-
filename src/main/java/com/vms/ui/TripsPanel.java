package com.vms.ui;

import com.vms.controller.DashboardController;
import com.vms.controller.TripController;
import com.vms.enums.TripStatus;
import com.vms.model.Driver;
import com.vms.model.Trip;
import com.vms.model.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZoneId;
import java.util.Date;

public class TripsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private TripController controller;

    public TripsPanel() {
        DashboardPanel dashboardPanel = new DashboardPanel();
        DashboardController dashboardController =
                new DashboardController(dashboardPanel);

        controller = new TripController(this,dashboardController);

        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);

        // ================= HEADER =================

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("Trips");
        title.setFont(new Font("Segoe UI", Font.BOLD,20));

        JButton addBtn = new JButton("+ Create Trip");
        Theme.styleButtonPrimary(addBtn);

        header.add(title,BorderLayout.WEST);
        header.add(addBtn,BorderLayout.EAST);

        add(header,BorderLayout.NORTH);

        // ================= TABLE =================

        String[] cols = {
                "ID","Start","End","Driver","Vehicle","Status"
        };

        model = new DefaultTableModel(cols,0){
            public boolean isCellEditable(int r,int c){ return false; }
        };

        table = new JTable(model);
        table.setRowHeight(34);

        // hide ID column
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        // ⭐ DOUBLE CLICK EDIT
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getClickCount() == 2){

                    int row = table.getSelectedRow();

                    if(row >= 0){

                        Integer id = (Integer) model.getValueAt(row,0);

                        Trip trip = controller.getTripById(id);

                        openTripDialog(trip);
                    }
                }
            }
        });

        add(new JScrollPane(table),BorderLayout.CENTER);

        addBtn.addActionListener(e -> openTripDialog(null));

        controller.loadTrips();
    }

    // ======================================================
    // CREATE + EDIT DIALOG
    // ======================================================

    private void openTripDialog(Trip trip){

        boolean edit = (trip != null);

        JTextField startLoc = new JTextField(edit ? trip.getStartLocation() : "");
        JTextField endLoc = new JTextField(edit ? trip.getEndLocation() : "");

        JComboBox<Driver> driverCombo =
                new JComboBox<>(controller.getAllDrivers().toArray(new Driver[0]));

        JComboBox<Vehicle> vehicleCombo =
                new JComboBox<>(controller.getAllVehicles().toArray(new Vehicle[0]));

        // ===== CALENDAR =====

        JSpinner startTime = new JSpinner(new SpinnerDateModel());
        startTime.setEditor(new JSpinner.DateEditor(startTime,"yyyy-MM-dd HH:mm"));

        JSpinner endTime = new JSpinner(new SpinnerDateModel());
        endTime.setEditor(new JSpinner.DateEditor(endTime,"yyyy-MM-dd HH:mm"));

        JTextField distance = new JTextField(edit && trip.getDistance()!=null
                ? String.valueOf(trip.getDistance())
                : "");

        JComboBox<TripStatus> status =
                new JComboBox<>(TripStatus.values());

        // ===== PREFILL EDIT VALUES =====

        if(edit){

            driverCombo.setSelectedItem(trip.getDriver());
            vehicleCombo.setSelectedItem(trip.getVehicle());
            status.setSelectedItem(trip.getStatus());

            if(trip.getStartTime()!=null){

                startTime.setValue(
                        Date.from(
                                trip.getStartTime()
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()
                        )
                );
            }

            if(trip.getEndTime()!=null){

                endTime.setValue(
                        Date.from(
                                trip.getEndTime()
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()
                        )
                );
            }
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(new JLabel("Start Location")); panel.add(startLoc);
        panel.add(new JLabel("End Location")); panel.add(endLoc);
        panel.add(new JLabel("Driver")); panel.add(driverCombo);
        panel.add(new JLabel("Vehicle")); panel.add(vehicleCombo);
        panel.add(new JLabel("Start Time")); panel.add(startTime);
        panel.add(new JLabel("End Time")); panel.add(endTime);
        panel.add(new JLabel("Distance")); panel.add(distance);
        panel.add(new JLabel("Status")); panel.add(status);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                edit ? "Edit Trip" : "Create Trip",
                JOptionPane.OK_CANCEL_OPTION
        );

        if(result == JOptionPane.OK_OPTION){

            controller.createOrUpdateTrip(

                    edit ? trip.getId() : null,
                    startLoc.getText(),
                    endLoc.getText(),
                    (Driver) driverCombo.getSelectedItem(),
                    (Vehicle) vehicleCombo.getSelectedItem(),
                    (Date) startTime.getValue(),
                    (Date) endTime.getValue(),
                    Float.parseFloat(distance.getText()),
                    (TripStatus) status.getSelectedItem()
            );
        }
    }

    // ======================================================
    // TABLE HELPERS (Called by Controller)
    // ======================================================

    public void clearTable(){
        model.setRowCount(0);
    }

    public void addTripRow(Object[] row){
        model.addRow(row);
    }
}
