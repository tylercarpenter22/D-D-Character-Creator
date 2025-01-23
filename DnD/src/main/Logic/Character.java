package main.Logic;

import java.awt.image.BufferedImage;
import java.util.List;

public class Character {

    private String name;
    private BufferedImage icon;
    private int level;
    private int currentHP;
    private int maxHP;
    private int AC;
    private Race race;
    private Subrace subrace;
    private CharClass charClass;
    private Subclass subclass;
    private Background background;
    private Stats stats;
    private List<Item> items;
    private List<Spell> spells;

    // Constructor
    public Character(String name, BufferedImage icon, int level, int currentHP, int maxHP, int AC, Race race, Subrace subrace, CharClass charClass, Subclass subclass, Background background, Stats stats, List<Item> items, List<Spell> spells) {
        this.name = name;
        this.icon = icon;
        this.level = level;
        this.currentHP = currentHP;
        this.maxHP = maxHP;
        this.AC = AC;
        this.race = race;
        this.subrace = subrace;
        this.charClass = charClass;
        this.subclass = subclass;
        this.background = background;
        this.stats = stats;
        this.items = items;
        this.spells = spells;
    }

    // Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getAC() {
        return AC;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Subrace getSubrace() {
        return subrace;
    }

    public void setSubrace(Subrace subrace) {
        this.subrace = subrace;
    }

    public CharClass getCharClass() {
        return charClass;
    }

    public void setCharClass(CharClass charClass) {
        this.charClass = charClass;
    }

    public Subclass getSubclass() {
        return subclass;
    }

    public void setSubclass(Subclass subclass) {
        this.subclass = subclass;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
}