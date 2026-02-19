package com.vms.ui;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(12, 18, 6, 18));
        JLabel title = new JLabel("My Profile");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel form = Theme.cardPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));

        JLabel sectionTitle = new JLabel("Driver Details");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        form.add(sectionTitle);
        form.add(Box.createVerticalStrut(2));

        JPanel grid = new JPanel(new GridLayout(3, 2, 16, 2));
        grid.setOpaque(false);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.DARK_GRAY);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JLabel nameValue = new JLabel("Amit Sharma");
        nameValue.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JLabel licenceLabel = new JLabel("Licence Number");
        licenceLabel.setForeground(Color.DARK_GRAY);
        licenceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JLabel licenceValue = new JLabel("MH0120230001234");
        licenceValue.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JLabel expiryLabel = new JLabel("Licence Expiry");
        expiryLabel.setForeground(Color.DARK_GRAY);
        expiryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JLabel expiryValue = new JLabel("2026-05-15");
        expiryValue.setFont(new Font("Segoe UI", Font.BOLD, 13));

        grid.add(nameLabel);
        grid.add(nameValue);
        grid.add(licenceLabel);
        grid.add(licenceValue);
        grid.add(expiryLabel);
        grid.add(expiryValue);

        form.add(grid);
        form.add(Box.createVerticalStrut(3));

        JLabel photoLabel = new JLabel("Driving Licence Photo");
        photoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        photoLabel.setForeground(Color.DARK_GRAY);
        form.add(photoLabel);
        form.add(Box.createVerticalStrut(2));

        JButton upload = new JButton("  Upload Licence Photo");
        Theme.styleButtonPrimary(upload);
        form.add(upload);
        add(form, BorderLayout.CENTER);
    }
}
