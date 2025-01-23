package main.Logic;

public class Spell {
    private String name;
    private int level;
    private String school;
    private String castingTime;
    private String range;
    private String duration;
    private String description;
    private boolean requiresConcentration;

    // Constructor, getters, setters, and other methods
    public Spell() {}

    public Spell(String name) {
        this.name = name;
    }

    public Spell(String name, int level, String school, String castingTime, String range, String duration, String description, boolean requiresConcentration) {
        this.name = name;
        this.level = level;
        this.school = school;
        this.castingTime = castingTime;
        this.range = range;
        this.duration = duration;
        this.description = description;
        this.requiresConcentration = requiresConcentration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequiresConcentration() {
        return requiresConcentration;
    }

    public void setRequiresConcentration(boolean requiresConcentration) {
        this.requiresConcentration = requiresConcentration;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
