package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class QualificationLab {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public QualificationLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper queryQulaifications(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.QualificationTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);
    }

    public void updateQualification(Qualification q){
        int  id=q.getID();

        ContentValues values=getContentValues(q);

        mDatabase.update(DBSchema.QualificationTable.Name,values, "_id="+id,
                null);

    }

    public void addQualification(Qualification q){
        ContentValues values=getContentValues(q);
        mDatabase.insert(DBSchema.QualificationTable.Name,null,values);
    }

    private static ContentValues getContentValues(Qualification q) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.QualificationTable.Cols.Degree, q.getDegree());
        values.put(DBSchema.QualificationTable.Cols.Details, q.getDetails());
        values.put(DBSchema.QualificationTable.Cols.Institute, q.getInstitution());
        values.put(DBSchema.QualificationTable.Cols.year, q.getYear());
        values.put(DBSchema.QualificationTable.Cols.Qual_Doc_ID, q.getDoc_ID());

        return values;
    }

    public List<Qualification> getQualifications() {

        List<Qualification> qs = new ArrayList<>();

        ClinicCursorWrapper cursor = queryQulaifications(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                qs.add(cursor.getQualification());
                cursor.moveToNext();
            }
        } finally {
            int size = qs.size();
            cursor.close();
        }
        return qs;
    }

    public List<Qualification> Get_Qualifications_of_Doctor(int doctor_id)
    {
        List<Qualification> qfs=getQualifications();
        List<Qualification> ret_qfs=new ArrayList<>();

        for (int i=0; i<qfs.size(); i++)
            if(qfs.get(i).getDoc_ID()==doctor_id)
                ret_qfs.add(qfs.get(i));

        return ret_qfs;


    }


}
