package main.GUI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import main.Logic.Spell;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class SpellSelection extends JFrame {
    private JComboBox<String> levelFilter;
    private JComboBox<String> schoolFilter;
    private JCheckBox[] spellCheckBoxes;
    private JPanel spellPanel;
    private JLabel spellCounterLabel;
    private JButton addSpellButton;
    private List<Spell> allSpells; // Holds all spells loaded from JSON
    private List<Spell> characterSpellList; // Reference to CharacterCreator's spell list
    private CharacterCreator characterCreator; // Reference to CharacterCreator

    public SpellSelection(List<Spell> characterSpellList, CharacterCreator characterCreator) {
        this.characterSpellList = characterSpellList;
        this.characterCreator = characterCreator;
        setTitle("Manage Spells");
        setLayout(new BorderLayout());
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize spell lists
        allSpells = new ArrayList<>();

        // Load spell data from JSON
        loadSpellsFromJSON();

        // Panel at top containing filters and spell counter
        JPanel filterPanel = new JPanel(new FlowLayout());
        spellCounterLabel = new JLabel("0/" + allSpells.size()); // Displaying 0/# of total spells
        filterPanel.add(spellCounterLabel);

        levelFilter = new JComboBox<>(new String[] { "All Levels", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5" });
        schoolFilter = new JComboBox<>(new String[] { "All Schools", "Evocation", "Necromancy", "Abjuration", "Conjuration", "Divination" });
        filterPanel.add(new JLabel("Filter by Level:"));
        filterPanel.add(levelFilter);
        filterPanel.add(new JLabel("Filter by School:"));
        filterPanel.add(schoolFilter);

        // Adding filter listeners
        levelFilter.addActionListener(_ -> filterSpells());
        schoolFilter.addActionListener(_ -> filterSpells());

        add(filterPanel, BorderLayout.NORTH);

        // Spell list panel
        spellPanel = new JPanel();
        spellPanel.setLayout(new BoxLayout(spellPanel, BoxLayout.Y_AXIS));
        JScrollPane spellScrollPane = new JScrollPane(spellPanel);
        add(spellScrollPane, BorderLayout.CENTER);

        // Bottom panel with Add Spell button
        JPanel bottomPanel = new JPanel(new FlowLayout());
        addSpellButton = new JButton("Add Spells");
        addSpellButton.addActionListener(_ -> addSelectedSpells());
        bottomPanel.add(addSpellButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initial display of all spells
        displaySpells(allSpells);

        setVisible(true);
    }

    // Modify the addSelectedSpells method to update the CharacterCreator's spell list
    private void addSelectedSpells() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                for (int i = 0; i < spellCheckBoxes.length; i++) {
                    if (spellCheckBoxes[i].isSelected()) {
                        characterSpellList.add(allSpells.get(i)); // Add to character's spell list
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                characterCreator.refreshSpellPanel(); // Refresh the spell panel in CharacterCreator
                dispose(); // Close the window
            }
        };
        worker.execute();
    }

    // Loads spell data from JSON file using Jackson
    private void loadSpellsFromJSON() {
        // Adjust the path to where your spells.json file is stored
        File jsonFile = new File("src/resources/spells.json");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Use a wrapper class or directly map to List<Spell>
            allSpells = objectMapper.readValue(jsonFile, new TypeReference<List<Spell>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Filters spells based on level and school
    private void filterSpells() {
        String selectedLevel = (String) levelFilter.getSelectedItem();
        String selectedSchool = (String) schoolFilter.getSelectedItem();

        List<Spell> filteredSpells = new ArrayList<>();

        for (Spell spell : allSpells) {
            boolean matchesLevel = selectedLevel.equals("All Levels") || ("Level " + spell.getLevel()).equals(selectedLevel);
            boolean matchesSchool = selectedSchool.equals("All Schools") || spell.getSchool().equals(selectedSchool);

            if (matchesLevel && matchesSchool) {
                filteredSpells.add(spell);
            }
        }

        displaySpells(filteredSpells);
    }

    // Displays spells in the spell panel
    private void displaySpells(List<Spell> spellsToDisplay) {
        spellPanel.removeAll();
        spellCheckBoxes = new JCheckBox[spellsToDisplay.size()];

        for (int i = 0; i < spellsToDisplay.size(); i++) {
            Spell spell = spellsToDisplay.get(i);
            spellCheckBoxes[i] = new JCheckBox(spell.getName());

            // Add tooltip with spell information
            spellCheckBoxes[i].setToolTipText("<html><b>" + spell.getName() + "</b><br/>"
                    + "Level: " + spell.getLevel() + "<br/>"
                    + "School: " + spell.getSchool() + "<br/>"
                    + spell.getDescription() + "</html>");

            spellPanel.add(spellCheckBoxes[i]);
        }

        spellPanel.revalidate();
        spellPanel.repaint();
    }

    // Static method to open the SpellSelection window
    public static void openSpellSelectionWindow(List<Spell> characterSpellList, CharacterCreator characterCreator) {
        SpellSelection spellSelection = new SpellSelection(characterSpellList, characterCreator);
        spellSelection.setVisible(true);
    }
}