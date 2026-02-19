package com.vms.ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class StatusRenderer extends JLabel implements TableCellRenderer {
    public StatusRenderer() {
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

        if (s.toLowerCase().contains("active") || s.toLowerCase().contains("completed"))
            bg = new Color(200, 245, 215);
        else if (s.toLowerCase().contains("in progress") || s.toLowerCase().contains("in-progress"))
            bg = new Color(210, 245, 245);
        else if (s.toLowerCase().contains("scheduled"))
            bg = new Color(220, 235, 255);
        else if (s.toLowerCase().contains("maintenance") || s.toLowerCase().contains("pending"))
            bg = new Color(255, 240, 210);
        else if (s.toLowerCase().contains("inactive"))
            bg = new Color(255, 200, 200);

        setBackground(bg);
        setForeground(fg);
        return this;
    }
}
