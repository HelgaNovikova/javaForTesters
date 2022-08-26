package ru.stqa.mantis.model;

import java.util.Objects;

public class Account {
    private String username;
    private String realName;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public Account setUsername(String username) {
        this.username = username;
        return this;
    }

    public Account setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(realName, account.realName) && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, realName, email);
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
