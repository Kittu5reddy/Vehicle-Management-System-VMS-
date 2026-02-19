package com.vms.ui;



import javax.swing.*;
import java.awt.*;

public class DriverDashboardPanel extends JPanel {
    public DriverDashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(18, 18, 12, 18));
        JLabel welcome = new JLabel("Welcome, John Doe");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(welcome, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(1, 3, 12, 12));
        cards.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        cards.setBackground(Theme.CARD_BG);
        cards.add(createSmallCard("Assigned Vehicle", "MH-12-AB-1234\nTata Ace"));
        cards.add(createSmallCard("Active Trips", "0"));
        cards.add(createSmallCard("Licence Expiry", "2026-05-15"));
        add(cards, BorderLayout.CENTER);

        JPanel trips = Theme.cardPanel();
        trips.setBorder(BorderFactory.createEmptyBorder(12, 18, 18, 18));
        trips.add(new JLabel("My Trips"), BorderLayout.NORTH);
        JPanel listWrap = new JPanel();
        listWrap.setLayout(new BoxLayout(listWrap, BoxLayout.Y_AXIS));
        listWrap.setOpaque(false);
        listWrap.add(makeTripRow("Mumbai → Pune", "2025-02-10", "completed"));
        listWrap.add(Box.createVerticalStrut(8));
        listWrap.add(makeTripRow("Delhi → Jaipur", "2025-02-15", "scheduled"));
        trips.add(new JScrollPane(listWrap), BorderLayout.CENTER);
        add(trips, BorderLayout.SOUTH);
    }

    private JPanel createSmallCard(String title, String value) {
        JPanel p = Theme.cardPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);
        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JLabel v = new JLabel("<html>" + value.replaceAll("\n", "<br>") + "</html>");
        v.setFont(new Font("Segoe UI", Font.BOLD, 20));
        p.add(t, BorderLayout.NORTH);
        p.add(v, BorderLayout.CENTER);
        return p;
    }

    private JPanel makeTripRow(String route, String date, String status) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(true);
        row.setBackground(Theme.LIGHT_GRAY);
        row.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel l = new JLabel("<html><b>" + route + "</b><br><small>" + date + "</small></html>");
        row.add(l, BorderLayout.WEST);
        row.add(StatusLabel(status), BorderLayout.EAST);
        return row;
    }

    private JLabel StatusLabel(String status) {
        String s = status.toLowerCase();
        Color bg = new Color(220, 220, 220);
        if (s.contains("completed"))
            bg = new Color(200, 245, 215);
        else if (s.contains("in progress"))
            bg = new Color(210, 245, 245);
        else if (s.contains("scheduled"))
            bg = new Color(220, 235, 255);
        JLabel l = new JLabel(status);
        l.setOpaque(true);
        l.setBackground(bg);
        l.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return l;
    }
}
