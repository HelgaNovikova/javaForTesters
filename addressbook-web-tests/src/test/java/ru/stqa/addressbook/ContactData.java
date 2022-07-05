package ru.stqa.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String jobTitle;
    private final String nickname;
    private final String company;
    private final String address;
    private final String homePhoneNumber;
    private final String mobilePhoneNumber;
    private final String workPhoneNumber;
    private final String faxNumber;
    private final String email;
    private final String email2;
    private final String email3;
    private final String homepage;

    public ContactData(String firstName, String middleName, String lastName, String jobTitle, String nickname, String company, String address, String homePhoneNumber, String mobilePhoneNumber, String workPhoneNumber, String faxNumber, String email, String email2, String email3, String homepage) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.workPhoneNumber = workPhoneNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }
}
