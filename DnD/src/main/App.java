package main;

import main.GUI.CharacterList;
import main.GUI.MainMenu;
import main.GUI.CharacterCreator;
import main.Logic.Character;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class App {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static CardLayout cardLayout;
    private static List<Character> characterList = new ArrayList<>();
    private static CharacterList characterListPanel;

    public static void main(String[] args) {
        frame = new JFrame("DnD Character Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new MainMenu(), "MainMenu");
        characterListPanel = new CharacterList(characterList);
        mainPanel.add(characterListPanel, "CharacterList");
        mainPanel.add(new CharacterCreator(), "CharacterCreator");

        frame.add(mainPanel);
        frame.setVisible(true);

        showPanel("MainMenu");
    }

    public static List<Character> getCharacterList() {
        return characterList;
    }

    public static void addCharacter(Character character) {
        characterList.add(character);
        characterListPanel.updateCharacterList(characterList);
    }

    public static void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void setCharacterList(List<Character> characters) {
        // Update the character list and refresh the CharacterList panel
        characterList = characters;
        characterListPanel.updateCharacterList(characterList);  // Refresh the character list display
    }

}