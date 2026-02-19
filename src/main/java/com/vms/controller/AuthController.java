package com.vms.controller;

import com.vms.enums.Role;
import com.vms.model.User;
import com.vms.service.AuthService;
import com.vms.service.imp.AuthServiceImpl;
import com.vms.ui.MainFrame;

import javax.swing.*;

public class AuthController {

    private final AuthService authService;
    private final MainFrame mainFrame;

    public AuthController(MainFrame mainFrame) {

        this.authService = new AuthServiceImpl();
        this.mainFrame = mainFrame;
    }

    // 🔥 Called from LoginFrame
    public void login(String username, String password) {

        try {

            // Call service layer
            User user = authService.login(username, password);

            // Navigate based on role
            if (user.getRole() == Role.MANAGER) {

                mainFrame.openAdminDashboard(user);

            } else if (user.getRole() == Role.DRIVER) {

                mainFrame.openDriverDashboard(user);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    e.getMessage(),
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void logout() {

        authService.logout();
        mainFrame.showCard(MainFrame.CARD_LOGIN);
    }
}
