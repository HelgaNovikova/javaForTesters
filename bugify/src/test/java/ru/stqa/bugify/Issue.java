package ru.stqa.bugify;

import java.util.Objects;

public class Issue {

    public Issue setId(int id) {
        this.id = id;
        return this;
    }

    public Issue setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Issue setDescription(String description) {
        this.description = description;
        return this;
    }

    private int state;

    public Issue setState(int statusCode) {
        this.state = statusCode;
        return this;
    }

    public int getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(subject, issue.subject) && Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, description);
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    private int id;
    private String subject;
    private String description;

}
