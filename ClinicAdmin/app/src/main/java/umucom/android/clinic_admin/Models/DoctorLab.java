package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DoctorLab {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DoctorLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper queryDoctors(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.DoctorTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);
    }

    public void updateDoctor(Doctor d){
        int  id=d.getID();
        ContentValues values=getContentValues(d);
        mDatabase.update(DBSchema.DoctorTable.Name,values, "_id="+id,
                null);

    }
    public void deleteDoctor(int id){
        mDatabase.delete(DBSchema.DoctorTable.Name,"_id="+id,null);
    }


    public long addDoctor(Doctor d){
        ContentValues values=getContentValues(d);
        return mDatabase.insert(DBSchema.DoctorTable.Name,null,values);
    }

    private static ContentValues getContentValues(Doctor d) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.DoctorTable.Cols.Contact, d.getContact());
        values.put(DBSchema.DoctorTable.Cols.Days, d.getDays());
        values.put(DBSchema.DoctorTable.Cols.Name, d.getName());
        values.put(DBSchema.DoctorTable.Cols.Timimg, d.getTiming());
        values.put(DBSchema.DoctorTable.Cols.Title, d.getTitle());


        return values;
    }

    public List<Doctor> getDoctors() {

        List<Doctor> doctors = new ArrayList<>();
        ClinicCursorWrapper cursor = queryDoctors(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                doctors.add(cursor.getDoctor());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return doctors;
    }

    public Doctor Get_Doctor(int id)
    {
        List<Doctor> doctors=getDoctors();
        int x;
        for (int i=0; i<doctors.size(); i++) {
            x = doctors.get(i).getID();
            if (doctors.get(i).getID() == id)
                return doctors.get(i);
        }

        return null;
    }

    public List<Doctor> Get_Doctors_with_title(String title)
    {
        List<Doctor> doctors=getDoctors();
        List<Doctor> t_docs=new ArrayList<>();

        for (int i=0; i<doctors.size(); i++)
            if(doctors.get(i).getTitle().equals(title))
               t_docs.add(doctors.get(i));

        return t_docs;
    }


}
