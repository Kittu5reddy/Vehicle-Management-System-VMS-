package com.vms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTripsPanel extends JPanel {
    private DefaultTableModel model;
    private List<Object[]> tripData;

    public MyTripsPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(18, 18, 12, 18));
        JLabel title = new JLabel("My Trips");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        String[] cols = { "Route", "Vehicle", "Status", "Date", "" };
        tripData = new ArrayList<>(Arrays.asList(
                new Object[] { "Mumbai → Pune", "MH-12-AB-1234", "completed", "2025-02-10" },
                new Object[] { "Delhi → Jaipur", "MH-12-AB-1234", "scheduled", "2025-02-15" }));
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Object[] row : tripData) {
            String action = row[2].toString().equals("scheduled") ? "▶  ✕" : "✕";
            model.addRow(new Object[] { row[0], row[1], row[2], row[3], action });
        }
        JTable table = new JTable(model);
        table.setRowHeight(34);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TableColumnModel cm = table.getColumnModel();
        cm.getColumn(2).setCellRenderer(new StatusRenderer());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                if (col == 4 && row >= 0) {
                    int x = e.getX() - table.getCellRect(row, col, false).x;
                    String action = table.getValueAt(row, 4).toString();
                    if (action.contains("▶") && x < 30) {
                        // Update trip status to "in progress"
                        tripData.get(row)[2] = "in progress";
                        model.setValueAt("in progress", row, 2);
                        model.setValueAt("✕", row, 4); // Remove start button, keep delete
                        JOptionPane.showMessageDialog(MyTripsPanel.this, "Trip started!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (x >= 30 || !action.contains("▶")) {
                        int confirm = JOptionPane.showConfirmDialog(MyTripsPanel.this,
                                "Delete selected trip?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            tripData.remove(row);
                            model.removeRow(row);
                        }
                    }
                }
            }
        });
        JPanel wrap = Theme.cardPanel();
        wrap.setLayout(new BorderLayout());
        wrap.add(new JScrollPane(table), BorderLayout.CENTER);
        wrap.setBorder(BorderFactory.createEmptyBorder(12, 18, 18, 18));
        add(wrap, BorderLayout.CENTER);
    }
}
