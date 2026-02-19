package com.vms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class VehicleRowRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if (value == null) return c;

        String status = value.toString().toUpperCase();

        if (!isSelected) {   // keep selection color when clicked

            switch (status) {

                case "AVAILABLE" -> {
                    c.setBackground(new Color(200, 245, 215)); // light green
                    c.setForeground(new Color(0, 100, 0));
                }

                case "ASSIGNED" -> {
                    c.setBackground(new Color(210, 235, 255)); // light blue
                    c.setForeground(new Color(0, 70, 140));
                }

                case "MAINTENANCE" -> {
                    c.setBackground(new Color(255, 240, 200)); // light orange
                    c.setForeground(new Color(160, 90, 0));
                }

                case "INACTIVE" -> {
                    c.setBackground(new Color(255, 0, 0)); // grey
                    c.setForeground(Color.DARK_GRAY);
                }

                default -> {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            }
        }

        setHorizontalAlignment(SwingConstants.CENTER);

        return c;
    }
}
