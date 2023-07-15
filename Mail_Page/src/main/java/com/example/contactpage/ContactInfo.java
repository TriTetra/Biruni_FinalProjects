package com.example.contactpage;

import java.io.Serializable;

public class ContactInfo implements Serializable {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;
    private String Address;
    private String subject;

    public ContactInfo(String firstName, String lastName, String phoneNumber, String eMail, String address, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.Address = address;
        this.subject = subject;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String geteMail() {
        return eMail;
    }


    public String getAddress() {
        return Address;
    }


    public String getSubject() {
        return subject;
    }


}
