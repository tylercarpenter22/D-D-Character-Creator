package main.Logic;

public class Stats {

    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int cha;


    public Stats() {
    }

    public Stats(int str, int dex, int con, int intel, int wis, int cha) {
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
    }

    // Getters and Setters
    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getIntel() {
        return intel;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getCha() {
        return cha;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "str=" + str +
                ", dex=" + dex +
                ", con=" + con +
                ", intel=" + intel +
                ", wis=" + wis +
                ", cha=" + cha +
                '}';
    }
}
