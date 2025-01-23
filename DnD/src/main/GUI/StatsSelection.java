package main.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import main.Logic.Stats;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsSelection extends JFrame {
    public static void openStatsWindow(JTable characterStatsTable) {
        // Create a new JFrame for stat selection
        JFrame statsFrame = new JFrame("Select Stats");
        statsFrame.setSize(600, 400);
        statsFrame.setLocationRelativeTo(null);
        statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SpringLayout layout = new SpringLayout();
        statsFrame.setLayout(layout);

        String[] columnNames = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        Object[][] data = {
            {null, null, null, null, null, null}  // One row with default values
        };

        // Create the table model, allowing all cells to be editable
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable statsTable = new JTable(tableModel);
        statsTable.setFont(new Font("Dialog", Font.PLAIN, 16));  // Set larger font
        statsTable.setRowHeight(30);  // Set row height to make it easier to read

        JTableHeader tableHeader = statsTable.getTableHeader();
        tableHeader.setFont(new Font("Dialog", Font.BOLD, 14));  // Set larger font for header

        layout.putConstraint(SpringLayout.WEST, tableHeader, 20, SpringLayout.WEST, statsFrame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, tableHeader, 20, SpringLayout.NORTH, statsFrame.getContentPane());
        layout.putConstraint(SpringLayout.EAST, tableHeader, -20, SpringLayout.EAST, statsFrame.getContentPane());

        layout.putConstraint(SpringLayout.WEST, statsTable, 20, SpringLayout.WEST, statsFrame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, statsTable, 0, SpringLayout.SOUTH, tableHeader);
        layout.putConstraint(SpringLayout.EAST, statsTable, -20, SpringLayout.EAST, statsFrame.getContentPane());

        statsFrame.add(tableHeader);
        statsFrame.add(statsTable);

        // Confirm Button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate and create Stats object
                if (validateStatsInput(statsTable)) {
                    Stats stats = createStatsFromTable(statsTable);
                    System.out.println("Created Stats object: " + stats.toString());

                    // Update the characterStatsTable with the confirmed stats
                    updateCharacterStatsTable(stats, characterStatsTable);
                    
                    // Close the frame
                    statsFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(statsFrame,
                            "Invalid input! Make sure all stats are entered.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        layout.putConstraint(SpringLayout.WEST, confirmButton, 250, SpringLayout.WEST, statsFrame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, confirmButton, 250, SpringLayout.SOUTH, statsTable);

        statsFrame.add(confirmButton);

        // Set the frame to be visible
        statsFrame.setVisible(true);
    }

    // Validate the stats input
    private static boolean validateStatsInput(JTable statsTable) {
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object value = model.getValueAt(row, col);
                if (value == null) {
                    return false;
                }
            }
        }
        return true;
    }

    // Create a Stats object from the table data
    private static Stats createStatsFromTable(JTable statsTable) {
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        int str = Integer.parseInt(model.getValueAt(0, 0).toString());
        int dex = Integer.parseInt(model.getValueAt(0, 1).toString());
        int con = Integer.parseInt(model.getValueAt(0, 2).toString());
        int intel = Integer.parseInt(model.getValueAt(0, 3).toString());
        int wis = Integer.parseInt(model.getValueAt(0, 4).toString());
        int cha = Integer.parseInt(model.getValueAt(0, 5).toString());
        return new Stats(str, dex, con, intel, wis, cha);
    }

    // Update the stats table in CharacterCreator with the confirmed stats
    private static void updateCharacterStatsTable(Stats stats, JTable characterStatsTable) {
        DefaultTableModel model = (DefaultTableModel) characterStatsTable.getModel();
        model.setValueAt(stats.getStr(), 0, 0);
        model.setValueAt(stats.getDex(), 0, 1);
        model.setValueAt(stats.getCon(), 0, 2);
        model.setValueAt(stats.getIntel(), 0, 3);
        model.setValueAt(stats.getWis(), 0, 4);
        model.setValueAt(stats.getCha(), 0, 5);
    }
}
