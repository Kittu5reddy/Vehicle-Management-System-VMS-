package com.vms.ui;

import com.vms.controller.AuthController;
import com.vms.controller.DashboardController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Sidebar sidebar;

    public static final String CARD_LOGIN = "LOGIN";
    public static final String CARD_DASHBOARD = "DASHBOARD";
    public static final String CARD_VEHICLES = "VEHICLES";
    public static final String CARD_DRIVERS = "DRIVERS";
    public static final String CARD_TRIPS = "TRIPS";
    public static final String CARD_MAINTENANCE = "MAINTENANCE";
    public static final String CARD_DRIVER_DASH = "DRIVER_DASH";
    public static final String CARD_MY_VEHICLE = "MY_VEHICLE";
    public static final String CARD_MY_TRIPS = "MY_TRIPS";
    public static final String CARD_PROFILE = "PROFILE";

    private AuthController authController;

    public MainFrame() {

        setTitle("Vehicle Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 13));

        initUI();
    }

    private void initUI() {

        getContentPane().setLayout(new BorderLayout());

        sidebar = new Sidebar(this);
        getContentPane().add(sidebar, BorderLayout.WEST);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Theme.CARD_BG);

        // 🔥 Create controller HERE
        authController = new AuthController(this);

        // Panels
        LoginFrame login = new LoginFrame(authController);

        DashboardPanel dashboard = new DashboardPanel();
        VehiclesPanel vehicles = new VehiclesPanel();
        DriversPanel drivers = new DriversPanel();
        TripsPanel trips = new TripsPanel();
        MaintenancePanel maintenance = new MaintenancePanel();

        DriverDashboardPanel driverDash = new DriverDashboardPanel();
        MyVehiclePanel myVehicle = new MyVehiclePanel();
        MyTripsPanel myTrips = new MyTripsPanel();
        ProfilePanel profile = new ProfilePanel();
        DashboardController controller =
                new DashboardController(dashboard);

        controller.loadDashboardData();

        mainPanel.add(login, CARD_LOGIN);
        mainPanel.add(dashboard, CARD_DASHBOARD);
        mainPanel.add(vehicles, CARD_VEHICLES);
        mainPanel.add(drivers, CARD_DRIVERS);
        mainPanel.add(trips, CARD_TRIPS);
        mainPanel.add(maintenance, CARD_MAINTENANCE);
        mainPanel.add(driverDash, CARD_DRIVER_DASH);
        mainPanel.add(myVehicle, CARD_MY_VEHICLE);
        mainPanel.add(myTrips, CARD_MY_TRIPS);
        mainPanel.add(profile, CARD_PROFILE);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        showCard(CARD_LOGIN);
        sidebar.setRole("GUEST");
    }

    public void showCard(String card) {

        cardLayout.show(mainPanel, card);
    }

    // 🔥 Controller calls these (UI navigation only)

    public void openAdminDashboard(Object user) {

        sidebar.setRole("ADMIN");
        showCard(CARD_DASHBOARD);
    }

    public void openDriverDashboard(Object user) {

        sidebar.setRole("DRIVER");
        showCard(CARD_DRIVER_DASH);
    }

    public void logout() {

        sidebar.setRole("GUEST");
        showCard(CARD_LOGIN);
    }
}
