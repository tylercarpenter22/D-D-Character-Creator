DnD Java Application

A desktop application built in Java that allows users to create and manage Dungeons & Dragons (DnD) characters. This application provides an intuitive graphical user interface (GUI) to assist players in managing their characters, including selecting classes, races, spells, items, and stats, making the character creation process easy and efficient.
Table of Contents

    Overview
    Features
    Technologies Used
    Installation and Setup
    How to Run
    Usage
    File Structure
    License

Overview

This DnD Java Application is designed for Dungeons & Dragons enthusiasts who want a simplified character creation and management tool. Built in Java, this application leverages Java Swing for the GUI, making it a straightforward, easy-to-use desktop tool. Users can create characters, select their race and class, manage items and spells, and review their stats—all through a user-friendly interface.

The application provides the following functionality:

    Character Creation: Create new characters by selecting race, class, and background, and assigning stats.
    Item and Spell Management: Add, remove, or view items and spells linked to the character.
    Character Selection: View and select previously created characters for further management.
    Character Stats: View and adjust the character's stats (strength, dexterity, intelligence, etc.).

Features

    Character Creation and Selection:
        Walkthrough interface to create a new character or select an existing one.
        Choose from different races and classes such as Human, Elf, Wizard, Fighter, etc.
        Assign ability scores (strength, dexterity, constitution, etc.) to your character.

    Item and Spell Management:
        Add and manage items in your character’s inventory (e.g., weapons, armor, potions).
        Manage spells, including selection, removal, and viewing spell details.

    User-Friendly GUI:
        Built using Java Swing, the application features a clean and responsive GUI.
        The interface displays character information clearly and intuitively, with dropdowns, buttons, and interactive forms to guide the user through the process.

    Character Stats Management:
        View and modify the character's stats in real-time (e.g., health, attack rolls, saving throws).
        View derived stats like hit points and attack bonuses based on the character’s race, class, and equipment.

    Backgrounds and Subclasses:
        Select a character's background (e.g., Noble, Soldier) and subclass options depending on the class chosen.
        Provides additional roleplaying and mechanical depth to the character creation process.

Technologies Used

    Java: Core programming language used to build the application.
    Java Swing: GUI framework for building the user interface.
    Object-Oriented Programming (OOP): Structured the application using classes, objects, and inheritance to represent DnD concepts like characters, items, spells, etc.
    Git: Version control system used for managing the source code and tracking changes.
    Libraries: External libraries used for additional functionality (if applicable).

How to Run
1. Clone this repository to your local machine.
2. Compile the source code (if needed):
 
   java -cp "bin;lib/*" main.App


To run the application on your local machine, follow these steps:

1. Clone the repository to your local machine using Git:
git clone https://github.com/your-username/dnd-java-application.git

2.Ensure that you have Java installed.

3. Navigate to project directory:
cd dnd-java-application

4. Compile ( javac -d bin src/main/*.java) and run!


File Structure:
DnD
├── lib/                    # External libraries (if any)
├── resources/              # Resources (e.g., images, sounds)
├── src/                    # Source code
│   ├── main/
│   │   ├── App.java        # Main application entry point
│   │   ├── commonConstants/ 
│   │   ├── main.GUI/      
│   │   │   ├── CharacterCreator.java  # Character creation GUI
│   │   │   ├── CharacterList.java     # Character selection GUI
│   │   │   ├── GUI.java               # Core GUI components
│   │   ├── main.Logic/
│   │   │   ├── Character.java         # Character data model
│   │   │   ├── ClassWrapper.java      # Class selection model
│   │   │   ├── Item.java             # Item data model
│   │   │   ├── Spell.java            # Spell data model
│   │   ├── main.resources/
│   ├── lib/                    # Any libraries or external packages
└── README.md                 # This README file

