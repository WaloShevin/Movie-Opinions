package de.htwberlin.webtech.webtech.web;

public class Character {

    private String name;
    private int power;

    public Character( String name, int power) {
       this.name = name;
       this.power =power;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int level) {
        this.power = level;
    }


}
