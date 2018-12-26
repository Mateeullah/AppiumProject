package umucom.android.clinic_admin.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class History {

    private int ID;
    private String Detail;
    private String Comments;
    private  int Hist_Patient_ID;

    public History(int ID, String detail, String comments, int hist_Patient_ID) {
        this.ID = ID;
        Detail = detail;
        Comments = comments;
        Hist_Patient_ID = hist_Patient_ID;
    }

    public int getHist_Patient_ID() {
        return Hist_Patient_ID;
    }

    public void setHist_Patient_ID(int hist_Patient_ID) {
        Hist_Patient_ID = hist_Patient_ID;
    }

    public History(String detail, String comments) {
        Detail = detail;
        Comments = comments;
    }

    public History(int ID, String detail, String comments) {
        this.ID = ID;
        Detail = detail;
        Comments = comments;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }


}
