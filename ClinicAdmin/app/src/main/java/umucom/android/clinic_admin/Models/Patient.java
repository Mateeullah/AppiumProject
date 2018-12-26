package umucom.android.clinic_admin.Models;

import java.util.ArrayList;

public class Patient {

    private int ID;
    private String Name;
    private String Contact;
    private String Address;
    private String Username;
    private String Password;
    private ArrayList<History> Histories;
    private  ArrayList<Report> Reports;


    public Patient(){};

    public Patient(String name, String contact, String address, String username, String password) {
        Name = name;
        Contact = contact;
        Address = address;
        Username = username;
        Password = password;
    }

    public Patient(int ID, String name, String contact, String address, String username, String password) {
        this.ID = ID;
        Name = name;
        Contact = contact;
        Address = address;
        Username = username;
        Password = password;
    }

    public Patient(int ID, String name, String contact, String address, String username, String password, ArrayList<History> histories, ArrayList<Report> reports) {
        this.ID = ID;
        Name = name;
        Contact = contact;
        Address = address;
        Username = username;
        Password = password;
        Histories = histories;
        Reports = reports;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



    public ArrayList<History> getHistories() {
        return Histories;
    }

    public void setHistories(ArrayList<History> histories) {
        Histories = histories;
    }

    public ArrayList<Report> getReports() {
        return Reports;
    }

    public void setReports(ArrayList<Report> reports) {
        Reports = reports;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

}
