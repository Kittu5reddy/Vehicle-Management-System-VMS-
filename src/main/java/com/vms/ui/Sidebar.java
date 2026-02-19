package com.vms.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sidebar extends JPanel {
    private MainFrame mainFrame;
    private JPanel navPanel;

    private JButton btnDashboard = new JButton("Dashboard");
    private JButton btnVehicles = new JButton("Vehicles");
    private JButton btnDrivers = new JButton("Drivers");
    private JButton btnTrips = new JButton("Trips");
    private JButton btnMaintenance = new JButton("Maintenance");
    private JButton btnSignOut = new JButton("Sign Out");

    private JButton btnDriverMyVehicle = new JButton("My Vehicle");
    private JButton btnDriverMyTrips = new JButton("My Trips");
    private JButton btnDriverProfile = new JButton("Profile");

    public Sidebar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(240, 0));
        setBackground(Theme.SIDEBAR);
        setLayout(new BorderLayout());

        add(buildHeader(), BorderLayout.NORTH);
        navPanel = new JPanel();
        navPanel.setOpaque(false);
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        add(navPanel, BorderLayout.CENTER);

        add(buildFooter(), BorderLayout.SOUTH);
        styleButtons();
        attachActions();
    }

    private JPanel buildHeader() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel title = new JLabel("  VMS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        title.setIcon(createSquareIcon(new Color(26, 188, 156)));
        p.add(title, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildFooter() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        btnSignOut.setBackground(Theme.SIDEBAR);
        btnSignOut.setForeground(Color.WHITE);
        btnSignOut.setFocusPainted(false);
        btnSignOut.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        btnSignOut.setHorizontalAlignment(SwingConstants.LEFT);
        p.add(btnSignOut, BorderLayout.SOUTH);
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return p;
    }

    private void styleButtons() {
        JButton[] all = { btnDashboard, btnVehicles, btnDrivers, btnTrips, btnMaintenance,
                btnDriverMyVehicle, btnDriverMyTrips, btnDriverProfile };
        for (JButton b : all) {
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            b.setBackground(Theme.SIDEBAR);
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        }
    }

    private void attachActions() {
        btnDashboard.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_DASHBOARD));
        btnVehicles.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_VEHICLES));
        btnDrivers.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_DRIVERS));
        btnTrips.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_TRIPS));
        btnMaintenance.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_MAINTENANCE));

        btnDriverMyVehicle.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_MY_VEHICLE));
        btnDriverMyTrips.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_MY_TRIPS));
        btnDriverProfile.addActionListener(e -> mainFrame.showCard(MainFrame.CARD_PROFILE));

        btnSignOut.addActionListener(e -> mainFrame.logout());
    }

    public void setRole(String role) {
        navPanel.removeAll();
        if ("ADMIN".equalsIgnoreCase(role)) {
            navPanel.add(Box.createVerticalStrut(8));
            navPanel.add(btnDashboard);
            navPanel.add(Box.createVerticalStrut(6));
            navPanel.add(btnVehicles);
            navPanel.add(Box.createVerticalStrut(6));
            navPanel.add(btnDrivers);
            navPanel.add(Box.createVerticalStrut(6));
            navPanel.add(btnTrips);
            navPanel.add(Box.createVerticalStrut(6));
            navPanel.add(btnMaintenance);
        } else if ("DRIVER".equalsIgnoreCase(role)) {
            navPanel.add(Box.createVerticalStrut(8));
            navPanel.add(btnDriverMyVehicle);
            navPanel.add(Box.createVerticalStrut(6));
            navPanel.add(btnDriverMyTrips);
            navPanel.add(Box.createVerticalStrut(6));
            navPanel.add(btnDriverProfile);
        } else {
            // Guest - minimal
            navPanel.add(Box.createVerticalStrut(8));
            navPanel.add(btnDashboard);
        }
        // add filler space and footer
        navPanel.add(Box.createVerticalGlue());
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        JLabel user = new JLabel("Guest");
        user.setForeground(Color.LIGHT_GRAY);
        user.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 8));
        footer.add(user);
        navPanel.add(footer);
        revalidate();
        repaint();
    }

    private Icon createSquareIcon(Color bg) {
        int s = 28;
        BufferedImage img = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(bg);
        g.fillRoundRect(0, 0, s, s, 8, 8);
        g.setColor(Color.WHITE);
        g.fillOval(6, 6, 16, 16);
        g.dispose();
        return new ImageIcon(img);
    }
}
