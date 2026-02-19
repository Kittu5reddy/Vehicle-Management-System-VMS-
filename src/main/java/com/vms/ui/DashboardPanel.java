package com.vms.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private JLabel vehiclesLabel;
    private JLabel driversLabel;
    private JLabel tripsLabel;
    private JLabel maintenanceLabel;

        private JPanel recentTripsContainer;
        private JPanel maintenanceContainer;
    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);

        JPanel top = new JPanel();
        top.setBackground(Theme.CARD_BG);
        top.setLayout(new GridLayout(1, 4, 12, 12));
        top.setBorder(BorderFactory.createEmptyBorder(18, 18, 12, 18));

        // ⭐ Labels controlled by Controller
        vehiclesLabel = new JLabel("0");
        driversLabel = new JLabel("0");
        tripsLabel = new JLabel("0");
        maintenanceLabel = new JLabel("0");

        top.add(createStatCard("Vehicles", vehiclesLabel));
        top.add(createStatCard("Drivers", driversLabel));
        top.add(createStatCard("Active Trips", tripsLabel));
        top.add(createStatCard("Maintenance", maintenanceLabel));

        add(top, BorderLayout.NORTH);


        JPanel lists = new JPanel(new GridLayout(1, 2, 12, 12));
        lists.setBorder(BorderFactory.createEmptyBorder(0, 18, 18, 18));
        lists.setBackground(Theme.CARD_BG);

        recentTripsContainer = createDynamicListCard("Recent Trips");
        maintenanceContainer = createDynamicListCard("Maintenance Queue");

        lists.add(recentTripsContainer);
        lists.add(maintenanceContainer);

        add(lists, BorderLayout.CENTER);
    }

    // ⭐ FIXED method
    private JPanel createStatCard(String title, JLabel valueLabel) {

        JPanel p = Theme.cardPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);

        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(Theme.SIDEBAR);

        p.add(t, BorderLayout.NORTH);
        p.add(valueLabel, BorderLayout.CENTER);

        return p;
    }

    private JPanel createListCard(String title, String[] items) {

        JPanel p = Theme.cardPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);

        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(t, BorderLayout.NORTH);

        JPanel listWrap = new JPanel();
        listWrap.setLayout(new BoxLayout(listWrap, BoxLayout.Y_AXIS));
        listWrap.setOpaque(false);

        for (String s : items) {

            JPanel row = new JPanel(new BorderLayout());
            row.setBackground(Theme.LIGHT_GRAY);
            row.setBorder(BorderFactory.createEmptyBorder(8,12,8,12));

            String[] parts = s.split("\\|");

            JLabel l = new JLabel("<html><b>" + parts[0] +
                    "</b><br><small>" + (parts.length>1?parts[1]:"") +
                    "</small></html>");

            row.add(l, BorderLayout.WEST);

            if(parts.length>2) {
                row.add(StatusLabel(parts[2]), BorderLayout.EAST);
            }

            listWrap.add(row);
            listWrap.add(Box.createVerticalStrut(8));
        }

        JScrollPane sp = new JScrollPane(listWrap);
        sp.setBorder(null);

        p.add(sp, BorderLayout.CENTER);

        return p;
    }

    private JLabel StatusLabel(String status) {

        Color bg = new Color(220,220,220);

        String s = status.toLowerCase();

        if(s.contains("completed")) bg = new Color(200,245,215);
        else if(s.contains("in progress")) bg = new Color(210,245,245);
        else if(s.contains("scheduled")) bg = new Color(220,235,255);
        else if(s.contains("pending")) bg = new Color(255,240,210);

        JLabel l = new JLabel(status);

        l.setOpaque(true);
        l.setBackground(bg);
        l.setBorder(BorderFactory.createEmptyBorder(6,10,6,10));

        return l;
    }

    // ⭐ Controller update methods

    public void setVehicleCount(long count) {
        vehiclesLabel.setText(String.valueOf(count));
    }

    public void setDriverCount(long count) {
        driversLabel.setText(String.valueOf(count));
    }

    public void setTripCount(long count) {
        tripsLabel.setText(String.valueOf(count));
    }

    public void setMaintenanceCount(long count) {
        maintenanceLabel.setText(String.valueOf(count));
    }
    private JPanel createDynamicListCard(String title) {

        JPanel p = Theme.cardPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);

        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));
        p.add(t, BorderLayout.NORTH);

        JPanel listWrap = new JPanel();
        listWrap.setLayout(new BoxLayout(listWrap, BoxLayout.Y_AXIS));
        listWrap.setOpaque(false);

        JScrollPane sp = new JScrollPane(listWrap);
        sp.setBorder(null);

        p.add(sp, BorderLayout.CENTER);

        // store listWrap as client property
        p.putClientProperty("listWrap", listWrap);

        return p;
    }
    public void setRecentTrips(java.util.List<String[]> items){

        JPanel listWrap = (JPanel) recentTripsContainer.getClientProperty("listWrap");

        listWrap.removeAll();

        for(String[] parts : items){

            JPanel row = new JPanel(new BorderLayout());
            row.setBackground(Theme.LIGHT_GRAY);
            row.setBorder(BorderFactory.createEmptyBorder(8,12,8,12));

            JLabel l = new JLabel("<html><b>"+parts[0]+"</b><br><small>"+parts[1]+"</small></html>");

            row.add(l,BorderLayout.WEST);
            row.add(StatusLabel(parts[2]),BorderLayout.EAST);

            listWrap.add(row);
            listWrap.add(Box.createVerticalStrut(8));
        }

        listWrap.revalidate();
        listWrap.repaint();
    }
    public void setMaintenanceQueue(java.util.List<String[]> items){

        JPanel listWrap = (JPanel) maintenanceContainer.getClientProperty("listWrap");

        listWrap.removeAll();

        for(String[] parts : items){

            JPanel row = new JPanel(new BorderLayout());
            row.setBackground(Theme.LIGHT_GRAY);
            row.setBorder(BorderFactory.createEmptyBorder(8,12,8,12));

            JLabel l = new JLabel("<html><b>"+parts[0]+"</b><br><small>"+parts[1]+"</small></html>");

            row.add(l,BorderLayout.WEST);
            row.add(StatusLabel(parts[2]),BorderLayout.EAST);

            listWrap.add(row);
            listWrap.add(Box.createVerticalStrut(8));
        }

        listWrap.revalidate();
        listWrap.repaint();
    }

}
