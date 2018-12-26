package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReportLab {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public ReportLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper queryReports(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.ReportTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);
    }

    public void updateReport(Report r){
        int  id=r.getID();

        ContentValues values=getContentValues(r);

        mDatabase.update(DBSchema.ReportTable.Name,values, "_id="+id,
                null);

    }

    public void addReport(Report r){
        ContentValues values=getContentValues(r);
        mDatabase.insert(DBSchema.ReportTable.Name,null,values);
    }

    private static ContentValues getContentValues(Report r) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.ReportTable.Cols.Comments, r.getComments());
        values.put(DBSchema.ReportTable.Cols.Path, r.getPath());
        values.put(DBSchema.ReportTable.Cols.Report_Patient_ID, r.getReport_P_ID());
        values.put(DBSchema.ReportTable.Cols.Test, r.getTest());
        values.put(DBSchema.ReportTable.Cols.TestDate, r.getTestDate());

        return values;
    }

    public List<Report> getReports() {

        List<Report> qs = new ArrayList<>();
        ClinicCursorWrapper cursor = queryReports(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                qs.add(cursor.getReport());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return qs;
    }


    public List<Report> Get_Reports_of_Patient(int patient_id)
    {
        List<Report> reports= getReports();
        List<Report> ret_rep= new ArrayList<>();

        for(int i=0; i<reports.size(); i++)
            if(reports.get(i).getReport_P_ID()==patient_id)
                ret_rep.add(reports.get(i));

        return ret_rep;
    }
    public Report Get_Reports_byId(int report_id)
    {
        List<Report> reports= getReports();
        Report temp = new Report();

        for(int i=0; i<reports.size(); i++)
            if(reports.get(i).getID()==report_id)
                temp = reports.get(i);

        return temp;
    }

}
