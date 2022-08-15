package ru.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Column(name = "firstname")
    @Expose
    private String firstName;

    @Column(name = "lastname")
    @Expose
    private String lastName;

    @Column(name = "home")
    @Type(type = "text")
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
    @Column(name = "phone2")
    @Type(type = "text")
    private String secondPhoneNumber = "34345456";

    public String getAllPhones() {
        return allPhones;
    }

    @Expose
    @Transient
    private String allPhones;

    @Expose
    @Transient
    private String allEmails;

    @Column(name = "address")
    @Type(type = "text")
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
    @Column(name = "work")
    @Type(type = "text")
    private String workPhoneNumber = "4624235";

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhoneNumber = "344756";

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email = "dfgdfg";

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2 = "dfjghj";

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3 = "ddhrwewr";

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Transient
    private String group;

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
