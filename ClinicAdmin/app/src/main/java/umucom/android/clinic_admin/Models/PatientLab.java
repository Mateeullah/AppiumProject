package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PatientLab {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public PatientLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper queryPatients(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.PatientTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);
    }

    public void updatePatient(Patient p){
        int  id=p.getID();
        ContentValues values=getContentValues(p);
        mDatabase.update(DBSchema.PatientTable.Name,values, "_id="+id,
                null);

    }

    public void addPatient(Patient p){
        ContentValues values=getContentValues(p);
        mDatabase.insert(DBSchema.PatientTable.Name,null,values);
    }

    private static ContentValues getContentValues(Patient patient) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.PatientTable.Cols.Address, patient.getAddress());
        values.put(DBSchema.PatientTable.Cols.Contact, patient.getContact());
        values.put(DBSchema.PatientTable.Cols.Name, patient.getName());
        values.put(DBSchema.PatientTable.Cols.Username, patient.getUsername());
        values.put(DBSchema.PatientTable.Cols.Password, patient.getPassword());

        return values;
    }

    public List<Patient> getPatients() {

        List<Patient> patients = new ArrayList<>();
        ClinicCursorWrapper cursor = queryPatients(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                patients.add(cursor.getPatient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return patients;
    }

    public Patient Get_Patient(int id)
    {
        List<Patient> Patients=getPatients();

        for (int i=0; i<Patients.size(); i++)
            if(Patients.get(i).getID()==id)
                return Patients.get(i);

        return null;
    }

    public boolean Username_exists(String Username) {

        List<Patient> users = getPatients();

        int j=users.size();
        for (int i=0; i<j; i++)
        {
            if( users.get(i).getUsername().equalsIgnoreCase(Username))
                return true;

        }

        return false;

    }

    public boolean check_username_password(String Uname, String Pass) {
        List<Patient> users=getPatients();

        int j=users.size();

        for (int i=0; i<j; i++)
        {
            if (users.get(i).getUsername().equals(Uname) && users.get(i).getPassword().equals(Pass))
                return true;

        }

        return false;
    }

    public int get_patientId_with_username(String Username)
    {

        List<Patient> users = getPatients();

        int j=users.size();
        for (int i=0; i<j; i++)
        {
            if( users.get(i).getUsername().equalsIgnoreCase(Username))
                return users.get(i).getID();

        }

        return -1;
    }

    public Patient get_patient_with_username(String Username)
    {

        int id=get_patientId_with_username(Username);
        if (id!=-1)
            return Get_Patient(id);
        else
            return null;
    }




}
