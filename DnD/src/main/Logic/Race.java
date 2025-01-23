package main.Logic;

import java.util.List;

public class Race {
    private String name;
    private String description;
    private List<Subrace> subraces;

    // Default constructor
    public Race() {}

    // Constructor
    public Race(String name, String description, List<Subrace> subraces) {
        this.name = name;
        this.description = description;
        this.subraces = subraces;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Subrace> getSubraces() {
        return subraces;
    }

    public void setSubraces(List<Subrace> subraces) {
        this.subraces = subraces;
    }

    @Override
    public String toString() {
        return name;
    }
}