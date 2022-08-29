package ru.stqa.mantis.model;

public class Project {

    private int id;
    private String name;

    public Project setId(int id) {
        this.id = id;
        return this;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
