package umucom.android.clinic_admin.Models;

import java.util.ArrayList;

public class Doctor {

    private int ID;
    private String Name;
    private String Contact;
    private String Timing;
    private String Title;
    private String Days;
    private ArrayList<Qualification> Qualifications;
    private ArrayList<Slot> Slots;

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    public  Doctor(){}

    public Doctor(String name, String contact, String timing, String title,String days) {
        Name = name;
        Contact = contact;
        Timing = timing;
        Title = title;
        Days=days;
    }

    public Doctor(int ID, String name, String contact, String timing, String title,String days) {
        this.ID = ID;
        Name = name;
        Contact = contact;
        Timing = timing;
        Title = title;
        Days=days;
    }

    public Doctor(int ID, String name, String contact, String timing, String title,String days, ArrayList<Qualification> qualifications, ArrayList<Slot> slots) {
        this.ID = ID;
        Name = name;
        Contact = contact;
        Timing = timing;
        Title = title;
        Qualifications = qualifications;
        Slots = slots;
        Days=days;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Qualification> getQualifications() {
        return Qualifications;
    }

    public void setQualifications(ArrayList<Qualification> qualifications) {
        Qualifications = qualifications;
    }

    public ArrayList<Slot> getSlots() {
        return Slots;
    }

    public void setSlots(ArrayList<Slot> slots) {
        Slots = slots;
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

    public String getTiming() {
        return Timing;
    }

    public void setTiming(String timing) {
        Timing = timing;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

}
