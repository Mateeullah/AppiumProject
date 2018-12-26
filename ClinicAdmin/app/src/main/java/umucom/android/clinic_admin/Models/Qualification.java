package umucom.android.clinic_admin.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import umucom.android.clinic_admin.R;

public class Qualification {

    private int ID;
    private int Year;
    private String Institution;
    private String Degree;
    private String Details;
    private int Doc_ID;

    public Qualification(){}

    public Qualification(int year, String institution, String degree, String details) {
        Year = year;
        Institution = institution;
        Degree = degree;
        Details = details;
    }

    public Qualification(int ID, int year, String institution, String degree, String details) {
        this.ID = ID;
        Year = year;
        Institution = institution;
        Degree = degree;
        Details = details;
    }

    public Qualification(int ID, int year, String institution, String degree, String details, int doc_ID) {
        this.ID = ID;
        Year = year;
        Institution = institution;
        Degree = degree;
        Details = details;
        Doc_ID = doc_ID;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public int getDoc_ID() {
        return Doc_ID;
    }

    public void setDoc_ID(int doc_ID) {
        Doc_ID = doc_ID;
    }
}
