package main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.App;
import main.Logic.Character;
import java.util.List;

public class MainMenu extends JPanel {
    private BufferedImage backgroundImage;

    public MainMenu() {
        try {
            // Load the background image
            backgroundImage = ImageIO.read(new File("src\\main\\resources\\mainmenu.jpg")); // Update the path to your background image
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the layout and add components
        setLayout(new BorderLayout());

        JButton createCharacterButton = new JButton("Create Character");
        createCharacterButton.setFont(new Font("Dialog", Font.PLAIN, 16));
        createCharacterButton.setPreferredSize(new Dimension(175, 40));
        createCharacterButton.addActionListener(_ -> App.showPanel("CharacterCreator"));

        JButton viewCharactersButton = new JButton("View Characters");
        viewCharactersButton.setFont(new Font("Dialog", Font.PLAIN, 16));
        viewCharactersButton.setPreferredSize(new Dimension(175, 40));
        viewCharactersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get characters from the App class
                List<Character> characters = App.getCharacterList();
                
                // Update the CharacterList panel with the character list
                App.setCharacterList(characters);  // Assuming you have a method to set the character list in App
                
                // Show the CharacterList panel
                App.showPanel("CharacterList");
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Dialog", Font.PLAIN, 16));
        exitButton.setPreferredSize(new Dimension(175, 40));
        exitButton.addActionListener(_ -> {
            System.exit(0); // Close the application
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.add(createCharacterButton);
        buttonPanel.add(viewCharactersButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}