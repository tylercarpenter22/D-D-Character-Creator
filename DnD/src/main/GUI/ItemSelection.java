package main.GUI;

import main.Logic.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemSelection extends JFrame {

    private JTextField nameField;
    private JTextArea descriptionArea;
    private JComboBox<String> typeComboBox;
    private CharacterCreator characterCreator;

    // Constructor that accepts a reference to the CharacterCreator
    public ItemSelection(CharacterCreator characterCreator) {
        this.characterCreator = characterCreator; // Store the reference

        // Set properties for the ItemSelection frame
        setTitle("Add Item");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        // Item Name
        JLabel nameLabel = new JLabel("Item Name: ");
        nameField = new JTextField(20);

        springLayout.putConstraint(SpringLayout.WEST, nameLabel, 50, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, nameField, 120, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 20, SpringLayout.NORTH, getContentPane());

        add(nameLabel);
        add(nameField);

        // Item Description
        JLabel descriptionLabel = new JLabel("Item Description: ");
        descriptionArea = new JTextArea(5, 40);

        springLayout.putConstraint(SpringLayout.WEST, descriptionLabel, 50, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, descriptionLabel, 60, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, descriptionArea, 50, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, descriptionArea, 90, SpringLayout.NORTH, getContentPane());

        add(descriptionLabel);
        add(descriptionArea);

        // Item Type
        JLabel typeLabel = new JLabel("Item Type: ");
        String[] types = {"Weapon", "Armor", "Tool", "Consumable", "Miscellaneous"};
        typeComboBox = new JComboBox<>(types);

        springLayout.putConstraint(SpringLayout.WEST, typeLabel, 50, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 200, SpringLayout.NORTH, getContentPane());

        springLayout.putConstraint(SpringLayout.WEST, typeComboBox, 120, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, typeComboBox, 200, SpringLayout.NORTH, getContentPane());

        add(typeLabel);
        add(typeComboBox);

        // Confirm Button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Dialog", Font.PLAIN, 16));  // Set larger font

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToCharacterCreator(); // Call method to add item
            }
        });

        springLayout.putConstraint(SpringLayout.WEST, confirmButton, 50, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, confirmButton, 250, SpringLayout.NORTH, getContentPane());

        add(confirmButton);
    }

    // Method to create an Item and add it to CharacterCreator
    private void addItemToCharacterCreator() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String category = (String) typeComboBox.getSelectedItem();

        // Create a new Item object
        Item newItem = new Item(name, description, category);

        // Add the item to the CharacterCreator
        characterCreator.addItemToList(newItem);

        // Close the window
        dispose();
    }

    // Static method to launch the frame
    public static void openItemSelectionWindow(CharacterCreator characterCreator) {
        ItemSelection itemSelection = new ItemSelection(characterCreator);
        itemSelection.setVisible(true);
    }
}
