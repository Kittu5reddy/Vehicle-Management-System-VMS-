package com.vms.ui;

import com.vms.controller.AuthController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;

public class LoginFrame extends JPanel {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private AuthController authController;

    public LoginFrame(AuthController authController) {

        this.authController = authController;

        setLayout(new GridBagLayout());
        setBackground(Theme.CARD_BG);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel card = Theme.cardPanel();
        card.setPreferredSize(new Dimension(480, 530));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // ===== ICON =====
        JLabel icon = new JLabel(createIcon(64, 64));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(20));
        card.add(icon);

        // ===== TITLE =====
        JLabel title = new JLabel("Vehicle Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        JLabel subtitle = new JLabel("Sign in to your account");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(20));
        card.add(subtitle);

        // ===== FORM =====

        txtUser = new JTextField();
        txtPass = new JPasswordField();

        addPlaceholder(txtUser, "Enter username");
        addPlaceholder(txtPass, "Enter password");

        card.add(new JLabel("Username"));
        card.add(txtUser);
        card.add(Box.createVerticalStrut(10));

        card.add(new JLabel("Password"));
        card.add(txtPass);

        // ===== LOGIN BUTTON =====

        JButton btnSign = new JButton("Sign In");
        Theme.styleButtonPrimary(btnSign);

        card.add(Box.createVerticalStrut(20));
        card.add(btnSign);

        add(card, gbc);

        // ===== CONTROLLER CALL ONLY =====

        btnSign.addActionListener((ActionEvent e) -> {

            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            if(user.equalsIgnoreCase("enter username") || user.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter username");
                return;
            }

            if(pass.isEmpty() || pass.equalsIgnoreCase("enter password")){
                JOptionPane.showMessageDialog(this,"Please enter password");
                return;
            }

            // CALL CONTROLLER
            authController.login(user, pass);
        });
    }

    private void addPlaceholder(JTextField field, String placeholder) {

        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent e) {
                if(field.getText().equals(placeholder)){
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if(field.getText().isEmpty()){
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }

    private Icon createIcon(int w, int h) {

        Image img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();

        g.setColor(new Color(10, 90, 160));
        g.fillRoundRect(0, 0, w, h, 14, 14);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, w / 3));

        FontMetrics fm = g.getFontMetrics();
        String s = "V";
        int sw = fm.stringWidth(s);

        g.drawString(s, (w - sw) / 2, (h + fm.getAscent()) / 2 - 4);

        g.dispose();

        return new ImageIcon(img);
    }
}
