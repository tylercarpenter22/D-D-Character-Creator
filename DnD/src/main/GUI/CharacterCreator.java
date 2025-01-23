package main.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import main.App;
import main.Logic.Character;
import main.Logic.Race;
import main.Logic.RaceWrapper;
import main.Logic.Spell;
import main.Logic.Stats;
import main.Logic.Subrace;
import main.Logic.CharClass;
import main.Logic.ClassWrapper;
import main.Logic.Subclass;
import main.Logic.Background;
import main.Logic.BackgroundWrapper;
import main.Logic.Item;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class CharacterCreator extends JPanel {

    // Instance Variables
    private JTextField nameField;
    private JLabel iconLabel;
    private JSpinner levelSpinner;
    private JTextField currentHPField;
    private JTextField maxHPField;
    private JSpinner acSpinner;
    private BufferedImage characterIcon;
    private JComboBox<Race> raceComboBox;
    private JLabel subraceLabel;
    private JComboBox<Subrace> subraceComboBox;
    private JComboBox<CharClass> classComboBox;
    private JLabel subclassLabel;
    private JComboBox<Subclass> subclassComboBox;
    private JComboBox<Background> backgroundComboBox;
    private JTable statsTable;
    private List<JCheckBox> itemList;
    private JPanel itemsPanel;
    private JScrollPane itemsScrollPane;
    private JButton addItemButton;
    private JButton removeItemButton;
    private List<Spell> spellList;
    private JPanel spellPanel;
    private JScrollPane spellScrollPane;
    private JButton removespellsButton;
    private JButton createCharacterButton;

    // Constructor
    public CharacterCreator() {
        addGUIComponents();
        loadRaces();
        loadClasses();
        loadBackgrounds();
    }

    // Methods
    public void addGUIComponents() {
        SpringLayout springLayout = new SpringLayout();
        JPanel characterCreatorPanel = new JPanel(springLayout);

        // Name Field
        JLabel nameLabel = new JLabel("Name: ");
        nameField = new JTextField(20);

        springLayout.putConstraint(SpringLayout.WEST, nameLabel, 50, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.WEST, nameField, 90, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 20, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(nameLabel);
        characterCreatorPanel.add(nameField);

        // Level Field
        JLabel levelLabel = new JLabel("Level: ");
        SpinnerNumberModel levelModel = new SpinnerNumberModel(1, 1, 99, 1); // Start at 1, min 1, max 99, step size 1
        levelSpinner = new JSpinner(levelModel);

        springLayout.putConstraint(SpringLayout.WEST, levelLabel, 320, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, levelLabel, 20, SpringLayout.NORTH, characterCreatorPanel);

        springLayout.putConstraint(SpringLayout.WEST, levelSpinner, 360, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, levelSpinner, 20, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(levelLabel);
        characterCreatorPanel.add(levelSpinner);

        // HP Field (Hit Points)
        JLabel hpLabel = new JLabel("HP: ");
        currentHPField = new JTextField(3);
        JLabel slashLabel = new JLabel("/");
        maxHPField = new JTextField(3);

        springLayout.putConstraint(SpringLayout.WEST, hpLabel, 420, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, hpLabel, 20, SpringLayout.NORTH, characterCreatorPanel);

        springLayout.putConstraint(SpringLayout.WEST, currentHPField, 445, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, currentHPField, 20, SpringLayout.NORTH, characterCreatorPanel);

        springLayout.putConstraint(SpringLayout.WEST, slashLabel, 485, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, slashLabel, 20, SpringLayout.NORTH, characterCreatorPanel);

        springLayout.putConstraint(SpringLayout.WEST, maxHPField, 495, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, maxHPField, 20, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(hpLabel);
        characterCreatorPanel.add(currentHPField);
        characterCreatorPanel.add(slashLabel);
        characterCreatorPanel.add(maxHPField);

        // AC (armor class) Field
        JLabel acLabel = new JLabel("AC: ");
        SpinnerNumberModel acModel = new SpinnerNumberModel(1, 1, 99, 1); // Start at 1, min 1, max 99, step size 1
        acSpinner = new JSpinner(acModel);

        springLayout.putConstraint(SpringLayout.WEST, acLabel, 560, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, acLabel, 20, SpringLayout.NORTH, characterCreatorPanel);

        springLayout.putConstraint(SpringLayout.WEST, acSpinner, 585, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, acSpinner, 20, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(acLabel);
        characterCreatorPanel.add(acSpinner);
        

        // Icon Field
        iconLabel = new JLabel("+", SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(100, 100)); // Set size for the circle
        iconLabel.setOpaque(true);
        iconLabel.setBackground(Color.LIGHT_GRAY);  // Background color for the circle
        iconLabel.setFont(new Font("Dialog", Font.BOLD, 50));  // Larger font for the "+"
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));  // Border around the circle

        // When the iconLabel is clicked, open the file chooser
        iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseIconImage();
            }
        });

        springLayout.putConstraint(SpringLayout.WEST, iconLabel, 550, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, iconLabel, 50, SpringLayout.NORTH, characterCreatorPanel);
        characterCreatorPanel.add(iconLabel);

        JLabel iconText = new JLabel("Click to choose icon");
        iconText.setFont(new Font("Dialog", Font.PLAIN, 12));

        springLayout.putConstraint(SpringLayout.WEST, iconText, 545, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, iconText, 150, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(iconText);


        // Race Field
        JLabel raceLabel = new JLabel("Race: ");
        raceComboBox = new JComboBox<>();  // Parameterized JComboBox
        raceComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        raceComboBox.setPreferredSize(new Dimension(150, 25));
        raceComboBox.addActionListener(_ -> updateSubraces()); // Update subraces when race is selected

        springLayout.putConstraint(SpringLayout.WEST, raceLabel, 50, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, raceLabel, 50, SpringLayout.NORTH, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.WEST, raceComboBox, 90, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, raceComboBox, 50, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(raceLabel);
        characterCreatorPanel.add(raceComboBox);

        // Subrace Field
        subraceLabel = new JLabel("Subrace: ");
        subraceComboBox = new JComboBox<>();  // Parameterized JComboBox
        subraceComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        subraceComboBox.setPreferredSize(new Dimension(150, 25));
        subraceLabel.setVisible(false);
        subraceComboBox.setVisible(false);

        springLayout.putConstraint(SpringLayout.WEST, subraceLabel, 270, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, subraceLabel, 50, SpringLayout.NORTH, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.WEST, subraceComboBox, 330, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, subraceComboBox, 50, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(subraceLabel);
        characterCreatorPanel.add(subraceComboBox);

        // Class Field
        JLabel classLabel = new JLabel("Class: ");
        classComboBox = new JComboBox<>();
        classComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        classComboBox.setPreferredSize(new Dimension(150, 25));
        classComboBox.addActionListener(_ -> updateSubclasses()); // Update subclasses when class is selected

        springLayout.putConstraint(SpringLayout.WEST, classLabel, 50, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, classLabel, 90, SpringLayout.NORTH, characterCreatorPanel);

        springLayout.putConstraint(SpringLayout.WEST, classComboBox, 90, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, classComboBox, 90, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(classLabel);
        characterCreatorPanel.add(classComboBox);

        // Subclass Field
        subclassLabel = new JLabel("Subclass: ");
        subclassComboBox = new JComboBox<>();  // Parameterized JComboBox
        subclassComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        subclassComboBox.setPreferredSize(new Dimension(150, 25));
        subclassLabel.setVisible(false);
        subclassComboBox.setVisible(false);

        springLayout.putConstraint(SpringLayout.WEST, subclassLabel, 270, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, subclassLabel, 90, SpringLayout.NORTH, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.WEST, subclassComboBox, 330, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, subclassComboBox, 90, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(subclassLabel);
        characterCreatorPanel.add(subclassComboBox);

        // Background Field
        JLabel backgroundLabel = new JLabel("Background: ");
        backgroundComboBox = new JComboBox<>();
        backgroundComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        backgroundComboBox.setPreferredSize(new Dimension(150, 25));

        springLayout.putConstraint(SpringLayout.WEST, backgroundLabel, 13, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, backgroundLabel, 130, SpringLayout.NORTH, characterCreatorPanel);

        springLayout.putConstraint(SpringLayout.WEST, backgroundComboBox, 90, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, backgroundComboBox, 130, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(backgroundLabel);
        characterCreatorPanel.add(backgroundComboBox);

        // Stats Field
        String[] columnNames = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        Object[][] data = {
            {10, 10, 10, 10, 10, 10}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are uneditable
            }
        };

        statsTable = new JTable(tableModel);

        JTableHeader tableHeader = statsTable.getTableHeader();
        springLayout.putConstraint(SpringLayout.WEST, tableHeader, 90, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, tableHeader, 200, SpringLayout.NORTH, characterCreatorPanel);
        characterCreatorPanel.add(tableHeader);

        springLayout.putConstraint(SpringLayout.WEST, statsTable, 90, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, statsTable, 0, SpringLayout.SOUTH, tableHeader);
        characterCreatorPanel.add(statsTable);

        JButton selectStatsButton = new JButton("Select Stats");
        selectStatsButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        selectStatsButton.setPreferredSize(new Dimension(100, 25));
        selectStatsButton.addActionListener(_ -> StatsSelection.openStatsWindow(statsTable));

        springLayout.putConstraint(SpringLayout.WEST, selectStatsButton, 260, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, selectStatsButton, 170, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(selectStatsButton);

        // Items Field
        JLabel itemsLabel = new JLabel("Items: ");
        itemsLabel.setFont(new Font("Dialog", Font.BOLD, 12));

        springLayout.putConstraint(SpringLayout.WEST, itemsLabel, 100, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, itemsLabel, 250, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(itemsLabel);

        itemList = new ArrayList<>();
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS)); // Vertical layout for items
        itemsScrollPane = new JScrollPane(itemsPanel);
        itemsScrollPane.setPreferredSize(new Dimension(400, 70)); // Scrollable area

        springLayout.putConstraint(SpringLayout.WEST, itemsScrollPane, 120, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, itemsScrollPane, 280, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(itemsScrollPane);

         addItemButton = new JButton("Add Item");
         addItemButton.setFont(new Font("Dialog", Font.PLAIN, 12));
         addItemButton.setPreferredSize(new Dimension(150, 25));
         
         addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the ItemSelection window
                ItemSelection.openItemSelectionWindow(CharacterCreator.this);
            }
        });

        springLayout.putConstraint(SpringLayout.WEST, addItemButton, 170, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, addItemButton, 250, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(addItemButton);
        
        // Remove Item Button
        removeItemButton = new JButton("Remove Item");
        removeItemButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        removeItemButton.setPreferredSize(new Dimension(150, 25));
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove selected items from the list
                for (int i = itemList.size() - 1; i >= 0; i--) {
                    JCheckBox itemCheckBox = itemList.get(i);
                    if (itemCheckBox.isSelected()) {
                        itemsPanel.remove(itemCheckBox);
                        itemList.remove(itemCheckBox);
                    }
                }
                itemsPanel.revalidate();  // Refresh the panel to display the new item
                itemsPanel.repaint();
            }
        });

        springLayout.putConstraint(SpringLayout.WEST, removeItemButton, 350, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, removeItemButton, 250, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(removeItemButton);


        // Spells Field
        JLabel spellsLabel = new JLabel("Spells: ");
        spellsLabel.setFont(new Font("Dialog", Font.BOLD, 12));

        springLayout.putConstraint(SpringLayout.WEST, spellsLabel, 100, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, spellsLabel, 355, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(spellsLabel);

        spellList = new ArrayList<>();
        spellPanel = new JPanel();
        spellPanel.setLayout(new BoxLayout(spellPanel, BoxLayout.Y_AXIS)); // Vertical layout for items
        spellScrollPane = new JScrollPane(spellPanel);
        spellScrollPane.setPreferredSize(new Dimension(400, 70)); // Scrollable area

        springLayout.putConstraint(SpringLayout.WEST, spellScrollPane, 120, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, spellScrollPane, 380, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(spellScrollPane);

        JButton selectSpellsButton = new JButton("Select Spells");
        selectSpellsButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        selectSpellsButton.setPreferredSize(new Dimension(150, 25));
        selectSpellsButton.addActionListener(_ -> {
            // Open the SpellSelection window and pass the character's spell list and the CharacterCreator instance
            SpellSelection.openSpellSelectionWindow(spellList, this);
        });

        springLayout.putConstraint(SpringLayout.WEST, selectSpellsButton, 170, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, selectSpellsButton, 350, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(selectSpellsButton);

        // Remove Spell Button
        removespellsButton = new JButton("Remove Spell");
        removespellsButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        removespellsButton.setPreferredSize(new Dimension(150, 25));
        removespellsButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Remove selected spells from the list
            for (int i = spellList.size() - 1; i >= 0; i--) {
                JCheckBox spellCheckBox = (JCheckBox) spellPanel.getComponent(i);
                if (spellCheckBox.isSelected()) {
                    spellPanel.remove(spellCheckBox);
                    spellList.remove(i);
                }
            }
            spellPanel.revalidate();  // Refresh the panel to display the new item
            spellPanel.repaint();
        }
        });

        springLayout.putConstraint(SpringLayout.WEST, removespellsButton, 350, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, removespellsButton, 350, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(removespellsButton);


        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        backButton.addActionListener(_ -> App.showPanel("MainMenu"));  // Switch to MainMenu panel

        springLayout.putConstraint(SpringLayout.WEST, backButton, 150, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, backButton, 500, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(backButton);

        // Create Character Button
        createCharacterButton = new JButton("Create Character");
        createCharacterButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        createCharacterButton.addActionListener(_ -> {
            createCharacter();  // Call createCharacter method
            App.showPanel("MainMenu");  // Navigate back to the main menu
        });

        springLayout.putConstraint(SpringLayout.WEST, createCharacterButton, 300, SpringLayout.WEST, characterCreatorPanel);
        springLayout.putConstraint(SpringLayout.NORTH, createCharacterButton, 500, SpringLayout.NORTH, characterCreatorPanel);

        characterCreatorPanel.add(createCharacterButton);

        // Wrap the main panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(characterCreatorPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to the frame
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // Supporting methods
    
    public void createCharacter() {
        try {
            // Create a new character
            String name = nameField.getText();
            BufferedImage icon = characterIcon;
            int level = (int) levelSpinner.getValue();
            int currentHP = Integer.parseInt(currentHPField.getText());
            int maxHP = Integer.parseInt(maxHPField.getText());
            int AC = (int) acSpinner.getValue();
            Race charRace = (Race) raceComboBox.getSelectedItem();
            Subrace charSubrace = null;
            if (charRace != null && charRace.getSubraces() != null && !charRace.getSubraces().isEmpty()) {
                charSubrace = (Subrace) subraceComboBox.getSelectedItem();
            }
            CharClass charClass = (CharClass) classComboBox.getSelectedItem();
            Subclass charSubclass = null;
            if (charClass != null && charClass.getSubclasses() != null && !charClass.getSubclasses().isEmpty()) {
                charSubclass = (Subclass) subclassComboBox.getSelectedItem();
            }
            Background charBackground = (Background) backgroundComboBox.getSelectedItem();
            Stats charStats = new Stats(
                (int) statsTable.getValueAt(0, 0),
                (int) statsTable.getValueAt(0, 1),
                (int) statsTable.getValueAt(0, 2),
                (int) statsTable.getValueAt(0, 3),
                (int) statsTable.getValueAt(0, 4),
                (int) statsTable.getValueAt(0, 5)
            );
            List<Item> charItems = new ArrayList<>();
            for (JCheckBox itemCheckBox : itemList) {
                String[] itemInfo = itemCheckBox.getText().split(" \\(");
                String itemName = itemInfo[0];
                String itemCategory = itemInfo[1].substring(0, itemInfo[1].length() - 1);
                String itemDescription = "Default description"; // Replace this with the actual description if available
                charItems.add(new Item(itemName, itemDescription, itemCategory));
            }
    
            List<Spell> spellList = new ArrayList<>();
            for (int i = 0; i < spellPanel.getComponentCount(); i++) {
                JCheckBox spellCheckBox = (JCheckBox) spellPanel.getComponent(i);
                spellList.add(new Spell(spellCheckBox.getText()));
            }
    
            // Create a new character object
            Character newCharacter = new Character(name, icon, level, currentHP, maxHP, AC, charRace, charSubrace, charClass, charSubclass, charBackground, charStats, charItems, spellList);
            App.addCharacter(newCharacter);

            // Display the character in the console
            System.out.println(newCharacter);
    
            // Optionally, you can add code to save the character to a file or database here
    
            // Show a success message
            JOptionPane.showMessageDialog(this, "Character created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for HP and AC.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while creating the character: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    

    private void loadRaces() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Adjust the path based on where you store the JSON file
            File jsonFile = new File("src/resources/races.json"); // Update this path if necessary
            RaceWrapper raceWrapper = objectMapper.readValue(jsonFile, new TypeReference<RaceWrapper>() {});
            List<Race> races = raceWrapper.getRaces();
            for (Race race : races) {
                raceComboBox.addItem(race); // Add each race to the JComboBox
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadClasses() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Adjust the path based on where you store the JSON file
            File jsonFile = new File("src/resources/classes.json"); // Update this path if necessary
            ClassWrapper classWrapper = objectMapper.readValue(jsonFile, new TypeReference<ClassWrapper>() {});
            List<CharClass> charClasses = classWrapper.getClasses();
            for (CharClass charClass : charClasses) {
                classComboBox.addItem(charClass); // Add each class to the JComboBox
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBackgrounds() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Adjust the path based on where you store the JSON file
            File jsonFile = new File("src/resources/backgrounds.json"); // Update this path if necessary
            BackgroundWrapper backgroundWrapper = objectMapper.readValue(jsonFile, new TypeReference<BackgroundWrapper>() {});
            List<Background> backgrounds = backgroundWrapper.getBackgrounds();
            for (Background background : backgrounds) {
                backgroundComboBox.addItem(background); // Add each background to the JComboBox
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateSubraces() {
        Race selectedRace = (Race) raceComboBox.getSelectedItem();
        if (selectedRace != null && selectedRace.getSubraces() != null && !selectedRace.getSubraces().isEmpty()) {
            subraceComboBox.removeAllItems();
            for (Subrace subrace : selectedRace.getSubraces()) {
                subraceComboBox.addItem(subrace);
            }
            subraceLabel.setVisible(true);
            subraceComboBox.setVisible(true);
        } else {
            subraceLabel.setVisible(false);
            subraceComboBox.setVisible(false);
        }
    }

    private void updateSubclasses() {
        CharClass selectedClass = (CharClass) classComboBox.getSelectedItem();
        if (selectedClass != null && selectedClass.getSubclasses() != null && !selectedClass.getSubclasses().isEmpty()) {
            subclassComboBox.removeAllItems();
            for (Subclass subclass : selectedClass.getSubclasses()) {
                subclassComboBox.addItem(subclass);
            }
            subclassLabel.setVisible(true);
            subclassComboBox.setVisible(true);
        } else {
            subclassLabel.setVisible(false);
            subclassComboBox.setVisible(false);
        }
    }

    // Method to add a new item to the item list
    public void addItemToList(Item item) {
        JCheckBox itemCheckBox = new JCheckBox(item.getName() + " (" + item.getCategory() + ")");
        itemList.add(itemCheckBox);
        itemsPanel.add(itemCheckBox);
        itemsPanel.revalidate();  // Refresh the panel to display the new item
        itemsPanel.repaint();
    }

    public void refreshSpellPanel() {
        // Run the UI update asynchronously on the EDT
        SwingUtilities.invokeLater(() -> {
            spellPanel.removeAll(); // Clear the existing display
    
            // Display the current spells in the spell list
            for (Spell spell : spellList) {
                JCheckBox spellCheckBox = new JCheckBox(spell.getName());
                spellPanel.add(spellCheckBox);
            }
    
            // Revalidate and repaint the panel to update the UI
            spellPanel.revalidate();
            spellPanel.repaint();
        });
    }

    // Method to handle icon image selection
    private void chooseIconImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Read the selected image file
                characterIcon = ImageIO.read(selectedFile);

                // Resize and update the icon label
                updateIconDisplay(characterIcon);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to load the image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to update the iconLabel with the selected image
    private void updateIconDisplay(BufferedImage icon) {
        // Resize the image to fit the circle label
        Image scaledImage = icon.getScaledInstance(iconLabel.getWidth(), iconLabel.getHeight(), Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledImage));  // Set the icon
        iconLabel.setText("");  // Remove the "+" text
    }
    
}
