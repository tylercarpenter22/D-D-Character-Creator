package main.GUI;

import main.Logic.Character;
import main.App;
import main.Logic.Item;
import main.Logic.Spell;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CharacterList extends JPanel {

    private List<Character> characters;
    private JPanel characterListPanel; // Panel to hold all character panels

    public CharacterList(List<Character> characters) {
        this.characters = characters;
        setLayout(new BorderLayout()); // Use BorderLayout to position the scroll panel and button
        initializePanel(); // Ensure the panel is set up properly
    }

    // Initialize panel with all characters
    private void initializePanel() {
        characterListPanel = new JPanel();
        characterListPanel.setLayout(new BoxLayout(characterListPanel, BoxLayout.Y_AXIS)); // Stack characters vertically

        // Add each character to the character list panel
        for (Character character : characters) {
            characterListPanel.add(createCharacterPanel(character));
        }

        // Add a JScrollPane around the character list panel to make it scrollable
        JScrollPane scrollPane = new JScrollPane(characterListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Set the preferred size for the scroll area
        add(scrollPane, BorderLayout.CENTER);

        // Create "Back" button and set its preferred size
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30)); // Set a custom size (width: 100px, height: 30px)
        backButton.addActionListener(_ -> App.showPanel("MainMenu"));

        // Optionally, center the button by wrapping it in a JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        // Add the button panel to the bottom of the layout (BorderLayout.SOUTH)
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Create a panel for each character
    private JPanel createCharacterPanel(Character character) {
        JPanel characterPanel = new JPanel();
        characterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Use FlowLayout for horizontal layout
        characterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Information panel (Name, stats, etc.)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Name
        JLabel nameLabel = new JLabel(character.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(nameLabel);

        // General info (Level, HP, AC)
        JLabel generalInfoLabel = new JLabel("Level: " + character.getLevel() +
                ", HP: " + character.getCurrentHP() + "/" + character.getMaxHP() +
                ", AC: " + character.getAC());
        generalInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoPanel.add(generalInfoLabel);

        // Race, Subrace, Class, Subclass, Background
        JLabel detailsLabel = new JLabel(
    "<html>Race: " + character.getRace() + 
    ", Subrace: " + character.getSubrace() + "<br>" +  // Line break after Race and Subrace
    "Class: " + character.getCharClass() + 
    ", Subclass: " + character.getSubclass() + "<br>" +  // Line break after Class and Subclass
    "Background: " + character.getBackground() + "</html>");
detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
infoPanel.add(detailsLabel);

        // Stats (assuming comma-separated string)
        JLabel statsLabel = new JLabel("Stats: " + character.getStats());
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoPanel.add(statsLabel);

        // Items (bullet point list)
        JLabel itemsLabel = new JLabel("Items:");
        itemsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(itemsLabel);

        // Add each item in a bullet point format
        for (Item item : character.getItems()) {
            JLabel itemLabel = new JLabel("• " + item.getName());  // Assuming item.getName() returns the item's name
            itemLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            infoPanel.add(itemLabel);
        }

        // Spells (bullet point list)
        JLabel spellsLabel = new JLabel("Spells:");
        spellsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(spellsLabel);

        // Add each spell in a bullet point format
        for (Spell spell : character.getSpells()) {
            JLabel spellLabel = new JLabel("• " + spell.getName());  // Assuming spell.getName() returns the spell's name
            spellLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            infoPanel.add(spellLabel);
        }

        // Icon with fixed size (80x80)
        JLabel iconLabel = new JLabel();
        if (character.getIcon() != null) {
            ImageIcon originalIcon = new ImageIcon(character.getIcon());  // Assuming character.getIcon() returns an image or file path
            Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            iconLabel.setPreferredSize(new Dimension(80, 80));  // If no icon, still set the space
        }

        // Add the info panel and the icon to the characterPanel
        characterPanel.add(infoPanel);
        characterPanel.add(iconLabel);

        return characterPanel;
    }

    public void updateCharacterList(List<Character> characters) {
        this.characters = characters; // Update the internal list
        characterListPanel.removeAll(); // Clear the previous list
        for (Character character : characters) {
            characterListPanel.add(createCharacterPanel(character)); // Add new panels
        }
        revalidate(); // Revalidate the panel
        repaint(); // Repaint the panel to reflect the changes
    }
}
