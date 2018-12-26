package umucom.android.clinic_admin.Models;

public class Slot {

    private int ID;
    private int Doc_ID;
    private String Time;
    private String Details;

    public Slot(){}

    public Slot(int doc_ID, String time, String details) {
        Doc_ID = doc_ID;
        Time = time;
        Details = details;
    }

    public Slot(int ID, int doc_ID, String time, String details) {
        this.ID = ID;
        Doc_ID = doc_ID;
        Time = time;
        Details = details;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDoc_ID() {
        return Doc_ID;
    }

    public void setDoc_ID(int doc_ID) {
        Doc_ID = doc_ID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
