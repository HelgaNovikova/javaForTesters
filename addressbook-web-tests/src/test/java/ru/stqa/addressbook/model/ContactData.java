package ru.stqa.addressbook.model;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class ContactData {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String homePhoneNumber;

    public ContactData withSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
        return this;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    @Expose
    private String secondPhoneNumber;

    public String getAllPhones() {
        return allPhones;
    }

    @Expose
    private String allPhones;
    @Expose
    private String allEmails;
    @Expose
    private String postAddress;

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPostAddress(String postAddress) {
        this.postAddress = postAddress;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public ContactData withWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
        return this;
    }

    public ContactData withAllPhones(String phones) {
        this.allPhones = phones;
        return this;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    @Expose
    private String workPhoneNumber;
    @Expose
    private String mobilePhoneNumber;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;

    private int id = Integer.MAX_VALUE;

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return this.id;
    }
}
