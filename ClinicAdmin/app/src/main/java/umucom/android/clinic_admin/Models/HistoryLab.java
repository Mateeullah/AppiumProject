package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoryLab {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public HistoryLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper queryHistory(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.HistoryTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);
    }

    public void updateHistory(History h){
        int  id=h.getID();
        ContentValues values=getContentValues(h);
        mDatabase.update(DBSchema.HistoryTable.Name,values, "_id="+id,
                null);

    }

    public void addHistory(History h){
        ContentValues values=getContentValues(h);
        mDatabase.insert(DBSchema.HistoryTable.Name,null,values);
    }

    private static ContentValues getContentValues(History h) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.HistoryTable.Cols.Comments, h.getComments());
        values.put(DBSchema.HistoryTable.Cols.Details, h.getDetail());
        values.put(DBSchema.HistoryTable.Cols.History_Patient_ID, h.getHist_Patient_ID());


        return values;
    }

    public List<History> getHistories() {

        List<History> histories = new ArrayList<>();
        ClinicCursorWrapper cursor = queryHistory(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                histories.add(cursor.getHistory());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return histories;
    }

    public List<History> Get_Histories_of_Patient(int patient_id)
    {
        List<History> histories= getHistories();
        List<History> ret_hist= new ArrayList<>();

        for(int i=0; i<histories.size(); i++)
            if(histories.get(i).getHist_Patient_ID()==patient_id)
                ret_hist.add(histories.get(i));

        return ret_hist;

    }




}
