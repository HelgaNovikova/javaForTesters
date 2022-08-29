package ru.stqa.mantis.model;

public class Issue {
    private int id;
    private String summary;
    private String description;
    private Project project;

    public Issue setId(int id) {
        this.id = id;
        return this;
    }

    public Issue setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Issue setDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue setProject(Project project) {
        this.project = project;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }
}
