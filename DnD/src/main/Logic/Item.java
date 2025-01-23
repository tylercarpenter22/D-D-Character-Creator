package main.Logic;

public class Item {
    private String name;
    private String description;
    private String category; // e.g., "tool", "weapon", "equipment", "other"

    // Constructor, getters, and setters
    public Item() {}

    public Item(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Item(String name,  String category) {
        this.name = name;
        this.description = null;
        this.category = category;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name;
    }
}