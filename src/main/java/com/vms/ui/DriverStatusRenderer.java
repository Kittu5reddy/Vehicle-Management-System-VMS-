package com.vms.ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DriverStatusRenderer extends JLabel implements TableCellRenderer {
    public DriverStatusRenderer() {
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        setHorizontalAlignment(CENTER);
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        String s = value == null ? "" : value.toString();
        setText(s);
        Color bg = new Color(220, 220, 220);
        Color fg = Color.DARK_GRAY;

        if (s.equals("Available")) {
            bg = new Color(200, 245, 215); // Green
        } else if (s.equals("Assigned")) {
            bg = new Color(210, 240, 255); // Blue
            fg = new Color(0, 51, 102); // Dark blue text
        } else if (s.equals("Not available")) {
            bg = new Color(255, 200, 200); // Red
            fg = new Color(102, 0, 0); // Dark red text
        }

        setBackground(bg);
        setForeground(fg);
        return this;
    }
}
