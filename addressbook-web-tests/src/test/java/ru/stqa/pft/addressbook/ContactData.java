package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String homenumber;
    private final String email;

    public ContactData(String firstname, String lastname, String homenumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.homenumber = homenumber;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHomenumber() {
        return homenumber;
    }

    public String getEmail() {
        return email;
    }
}
