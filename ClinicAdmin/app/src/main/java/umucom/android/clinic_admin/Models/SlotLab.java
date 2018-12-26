package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SlotLab {

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public SlotLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper querySlots(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.SlotTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);

    }

    public void updateSlot(Slot slot){
        int  id=slot.getID();

        ContentValues values=getContentValues(slot);

        mDatabase.update(DBSchema.SlotTable.Name,values, "_id="+id,
                null);

    }

    public void addSlot(Slot s){
        ContentValues values=getContentValues(s);
        mDatabase.insert(DBSchema.SlotTable.Name,null,values);
    }

    private static ContentValues getContentValues(Slot slot) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.SlotTable.Cols.Details, slot.getDetails());
        values.put(DBSchema.SlotTable.Cols.Slot_Doc_ID, slot.getDoc_ID());
        values.put(DBSchema.SlotTable.Cols.Time, slot.getTime());

        return values;
    }

    public List<Slot> getSlots() {

        List<Slot> slots = new ArrayList<>();
        ClinicCursorWrapper cursor = querySlots(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                slots.add(cursor.getSlot());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return slots;
    }

    public Slot Get_Slot(int id)
    {
        List<Slot> slots=getSlots();

        for (int i=0; i<slots.size(); i++)
            if(slots.get(i).getID()==id)
                return slots.get(i);

        return null;
    }

    public List<String> Get_Slots_of_Doctor_copy(int doctor_id)
    {
        List<Slot> qfs=getSlots();
        List<String> ret_qfs=new ArrayList<>();

        for (int i=0; i<qfs.size(); i++)
            if(qfs.get(i).getDoc_ID()==doctor_id)
                ret_qfs.add(qfs.get(i).getTime());

        return ret_qfs;


    }

    public List<Slot> Get_Slots_of_Doctor(int doctor_id)
    {
        List<Slot> qfs=getSlots();
        List<Slot> ret_qfs=new ArrayList<>();

        for (int i=0; i<qfs.size(); i++)
            if(qfs.get(i).getDoc_ID()==doctor_id)
                ret_qfs.add(qfs.get(i));

        return ret_qfs;


    }

    public void deleteSlot(int id){
        mDatabase.delete(DBSchema.SlotTable.Name,"_id="+id,null);
    }

    public boolean isBooked(Slot s,String Date)
    {
        BookingLab bl= new BookingLab(mContext);
        List<Booking> bookings= bl.getBookings();
        int slot_id=s.getID();

        for (int i=0; i<bookings.size(); i++)
            if (bookings.get(i).getID()==slot_id && bookings.get(i).getDate().equals(Date))
                return true;

        return false;
    }


    public boolean isBooked(int Slot_id,String Date)
    {
        BookingLab bl= new BookingLab(mContext);
        List<Booking> bookings= bl.getBookings();


        for (int i=0; i<bookings.size(); i++)
            if (bookings.get(i).getID()==Slot_id && bookings.get(i).getDate().equals(Date))
                return true;

        return false;
    }




}
