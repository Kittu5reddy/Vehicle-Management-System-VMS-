package com.vms.ui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Theme {
    public static final Color SIDEBAR = new Color(9, 37, 73);
    public static final Color SIDEBAR_DARK = new Color(7, 30, 60);
    public static final Color PRIMARY = new Color(11, 86, 150);
    public static final Color CARD_BG = new Color(250, 250, 251);
    public static final Color LIGHT_GRAY = new Color(245, 246, 247);

    public static void styleButtonPrimary(JButton b) {
        b.setBackground(PRIMARY);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    }

    public static JPanel cardPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        return p;
    }

    public static JLabel badge(String text, Color bg) {
        JLabel l = new JLabel(text);
        l.setOpaque(true);
        l.setBackground(bg);
        l.setForeground(Color.decode("#07324f").darker());
        l.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return l;
    }
}
