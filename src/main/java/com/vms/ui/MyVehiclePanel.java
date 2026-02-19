package com.vms.ui;

import javax.swing.*;
import java.awt.*;

public class MyVehiclePanel extends JPanel {
    public MyVehiclePanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(18, 18, 12, 18));
        JLabel title = new JLabel("My Vehicle");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1, 3, 12, 12));
        cards.setOpaque(false);
        cards.setBorder(BorderFactory.createEmptyBorder(12, 18, 18, 18));

        // Assigned Vehicle Card
        JPanel assignedCard = Theme.cardPanel();
        assignedCard.setLayout(new BorderLayout());
        assignedCard.setBackground(Color.WHITE);

        JLabel assignedLabel = new JLabel("Assigned Vehicle");
        assignedLabel.setForeground(new Color(150, 150, 150));
        assignedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        assignedLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 4, 12));

        JPanel assignedContent = new JPanel();
        assignedContent.setLayout(new BoxLayout(assignedContent, BoxLayout.Y_AXIS));
        assignedContent.setOpaque(false);
        assignedContent.setBorder(BorderFactory.createEmptyBorder(4, 12, 8, 12));

        JLabel vehicleNumber = new JLabel("MH-12-AB-1234");
        vehicleNumber.setFont(new Font("Segoe UI", Font.BOLD, 18));
        vehicleNumber.setForeground(Color.DARK_GRAY);

        JLabel vehicleModel = new JLabel("Tata Ace");
        vehicleModel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        vehicleModel.setForeground(new Color(150, 150, 150));

        assignedContent.add(vehicleNumber);
        assignedContent.add(Box.createVerticalStrut(2));
        assignedContent.add(vehicleModel);

        assignedCard.add(assignedLabel, BorderLayout.NORTH);
        assignedCard.add(assignedContent, BorderLayout.CENTER);

        // Active Trips Card
        JPanel tripsCard = Theme.cardPanel();
        tripsCard.setLayout(new BorderLayout());
        tripsCard.setBackground(Color.WHITE);

        JLabel tripsLabel = new JLabel("Active Trips");
        tripsLabel.setForeground(new Color(150, 150, 150));
        tripsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tripsLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 4, 12));

        JLabel tripsValue = new JLabel("0");
        tripsValue.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tripsValue.setForeground(Color.DARK_GRAY);
        tripsValue.setHorizontalAlignment(SwingConstants.CENTER);
        tripsValue.setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));

        tripsCard.add(tripsLabel, BorderLayout.NORTH);
        tripsCard.add(tripsValue, BorderLayout.CENTER);

        // Licence Expiry Card
        JPanel expiryCard = Theme.cardPanel();
        expiryCard.setLayout(new BorderLayout());
        expiryCard.setBackground(Color.WHITE);

        JLabel expiryLabel = new JLabel("Licence Expiry");
        expiryLabel.setForeground(new Color(150, 150, 150));
        expiryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        expiryLabel.setBorder(BorderFactory.createEmptyBorder(8, 12, 4, 12));

        JLabel expiryValue = new JLabel("2026-05-15");
        expiryValue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        expiryValue.setForeground(Color.DARK_GRAY);
        expiryValue.setHorizontalAlignment(SwingConstants.CENTER);
        expiryValue.setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));

        expiryCard.add(expiryLabel, BorderLayout.NORTH);
        expiryCard.add(expiryValue, BorderLayout.CENTER);

        cards.add(assignedCard);
        cards.add(tripsCard);
        cards.add(expiryCard);

        add(cards, BorderLayout.CENTER);
    }
}
