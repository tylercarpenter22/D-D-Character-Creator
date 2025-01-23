package main.Logic;

import java.util.List;

public class CharClass {
    private String name;
    private String description;
    private List<Subclass> subclasses;

    // Default constructor
    public CharClass() {}

    // Constructor
    public CharClass(String name, String description, List<Subclass> subclasses) {
        this.name = name;
        this.description = description;
        this.subclasses = subclasses;
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

    public List<Subclass> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<Subclass> subclasses) {
        this.subclasses = subclasses;
    }

    @Override
    public String toString() {
        return name;
    }
}