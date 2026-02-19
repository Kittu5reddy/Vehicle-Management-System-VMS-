package com.vms.ui;

import com.vms.controller.MaintenanceController;
import com.vms.model.Maintenance;
import com.vms.model.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class MaintenancePanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private MaintenanceController controller;

    public MaintenancePanel(){

        controller = new MaintenanceController(this);

        setLayout(new BorderLayout());

        JButton addBtn = new JButton("+ Add Maintenance");

        add(addBtn,BorderLayout.NORTH);

        String[] cols={
                "ID","Vehicle","Service Type","Description",
                "Service Date","Cost","Service Center"
        };

        model=new DefaultTableModel(cols,0){

            public boolean isCellEditable(int r,int c){return false;}
        };

        table=new JTable(model);

        // hide id
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        add(new JScrollPane(table),BorderLayout.CENTER);

        // ADD
        addBtn.addActionListener(e->openDialog(null));

        // DOUBLE CLICK EDIT
        table.addMouseListener(new MouseAdapter(){

            public void mouseClicked(MouseEvent e){

                if(e.getClickCount()==2){

                    int row=table.getSelectedRow();

                    Integer id=(Integer)model.getValueAt(row,0);

                    Maintenance m=controller.getById(id);

                    openDialog(m);
                }
            }
        });

        controller.loadMaintenances();
    }

    private void openDialog(Maintenance m){

        boolean edit=(m!=null);

        JComboBox<Vehicle> vehicleCombo=
                new JComboBox<>(controller.getAllVehicles().toArray(new Vehicle[0]));

        JTextField serviceType=new JTextField(edit?m.getServiceType():"");
        JTextField desc=new JTextField(edit?m.getDescription():"");

        JSpinner date=new JSpinner(new SpinnerDateModel());
        date.setEditor(new JSpinner.DateEditor(date,"yyyy-MM-dd HH:mm"));

        JTextField cost=new JTextField(edit?String.valueOf(m.getCost()):"");
        JTextField center=new JTextField(edit?m.getServiceCenter():"");

        if(edit){

            vehicleCombo.setSelectedItem(m.getVehicle());
        }

        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.add(new JLabel("Vehicle"));panel.add(vehicleCombo);
        panel.add(new JLabel("Service Type"));panel.add(serviceType);
        panel.add(new JLabel("Description"));panel.add(desc);
        panel.add(new JLabel("Service Date"));panel.add(date);
        panel.add(new JLabel("Cost"));panel.add(cost);
        panel.add(new JLabel("Service Center"));panel.add(center);

        int result=JOptionPane.showConfirmDialog(

                this,
                panel,
                edit?"Edit Maintenance":"Create Maintenance",
                JOptionPane.OK_CANCEL_OPTION
        );

        if(result==JOptionPane.OK_OPTION){

            if(edit){

                controller.updateMaintenance(

                        m.getId(),
                        (Vehicle)vehicleCombo.getSelectedItem(),
                        serviceType.getText(),
                        desc.getText(),
                        (Date)date.getValue(),
                        Double.parseDouble(cost.getText()),
                        center.getText()
                );

            }else{

                controller.createMaintenance(

                        (Vehicle)vehicleCombo.getSelectedItem(),
                        serviceType.getText(),
                        desc.getText(),
                        (Date)date.getValue(),
                        Double.parseDouble(cost.getText()),
                        center.getText()
                );
            }
        }
    }

    public void clearTable(){ model.setRowCount(0); }

    public void addRow(Object[] row){ model.addRow(row); }
}
