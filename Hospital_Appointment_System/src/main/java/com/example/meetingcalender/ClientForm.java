package com.example.meetingcalender;

import javafx.scene.image.Image;

import java.io.File;

public class ClientForm {

    private String firstName;
    private String lastName;
    private String idNumber;
    private String meetingDay;
    private String meetingTime;

    public ClientForm(String firstName, String lastName, String idNumber, String meetingDay, String meetingTime){
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.meetingDay = meetingDay;
        this.meetingTime = meetingTime;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getMeetingDay(){return meetingDay;}

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }
}
