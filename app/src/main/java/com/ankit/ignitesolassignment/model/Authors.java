package com.ankit.ignitesolassignment.model;

public class Authors {
    private int birth_year;

    private int death_year;

    private String name;

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getBirth_year() {
        return this.birth_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public int getDeath_year() {
        return this.death_year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
