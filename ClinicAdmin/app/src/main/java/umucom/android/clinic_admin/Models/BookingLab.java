package umucom.android.clinic_admin.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookingLab {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public BookingLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase= new ClinicBaseHelper(mContext).getWritableDatabase();

    }

    private ClinicCursorWrapper queryBookings(String where, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                DBSchema.BookingTable.Name,
                null,
                where,
                whereArgs,
                null,
                null,
                null
        );
        return  new ClinicCursorWrapper(cursor);
    }

    public void updateBooking(Booking b){
        int  id=b.getID();
        ContentValues values=getContentValues(b);
        mDatabase.update(DBSchema.BookingTable.Name,values, "_id="+id,
                null);

    }

    public void addBooking(Booking d){
        ContentValues values=getContentValues(d);
        mDatabase.insert(DBSchema.BookingTable.Name,null,values);
    }

    private static ContentValues getContentValues(Booking b) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.BookingTable.Cols.Date, b.getDate());
        values.put(DBSchema.BookingTable.Cols.Details, b.getDetails());
        values.put(DBSchema.BookingTable.Cols.Booking_Doctor_ID, b.getBooking_D_ID());
        values.put(DBSchema.BookingTable.Cols.Booking_Patient_ID, b.getBooking_P_ID());
        values.put(DBSchema.BookingTable.Cols.Booking_Slot_ID, b.getBooking_S_ID());


        return values;
    }

    public List<Booking> getBookings() {

        List<Booking> bookings= new ArrayList<>();
        ClinicCursorWrapper cursor = queryBookings(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bookings.add(cursor.getBooking());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return bookings;
    }

    public List<Booking> Get_All_Bookings(){

        List<Booking> bookings=getBookings();
        SlotLab slotlab= new SlotLab(mContext);
        DoctorLab dl= new DoctorLab(mContext);
        PatientLab pl= new PatientLab(mContext);

        for (int i=0; i<bookings.size(); i++){


            bookings.get(i).setSlot(slotlab.Get_Slot(bookings.get(i).getID()));
            bookings.get(i).setDoctor(dl.Get_Doctor(bookings.get(i).getID()));
            bookings.get(i).setPatient(pl.Get_Patient(bookings.get(i).getID()));
        }

        return bookings;
    }

    public Booking Get_Booking_Detail(int booking_id)
    {
        List<Booking> bookings=Get_All_Bookings();

        for (int i=0; i<bookings.size(); i++){
            if(bookings.get(i).getID()==booking_id)
                return bookings.get(i);
        }

        return null;
    }

    public List<Booking> Get_Booking_of_Patient(int Patient_id)
    {
        List<Booking> bookings=Get_All_Bookings();
        List<Booking> p_bookings= new ArrayList<>();

        for (int i=0; i<bookings.size(); i++){
            if(bookings.get(i).getBooking_P_ID()==Patient_id)
               p_bookings.add(bookings.get(i));
        }

        return p_bookings;
    }

    public List<Booking> Get_Bookings_of_Doctor(int doctor_id)
    {
        List<Booking> bookings=Get_All_Bookings();
        List<Booking> d_bookings= new ArrayList<>();

        for (int i=0; i<bookings.size(); i++){
            if(bookings.get(i).getBooking_D_ID()==doctor_id)
                d_bookings.add(bookings.get(i));
        }

        return d_bookings;
    }

    public boolean booking_available(int doctor_id, int Slot_id,String Date)
    {
        List<Booking> bookings=getBookings();

        for (int i=0; i<bookings.size(); i++)
        {
            Booking b= bookings.get(i);
            if( b.getBooking_D_ID()==doctor_id && b.getBooking_S_ID()==Slot_id && Date.equals(b.getDate()))
                return false;
        }
        return true;
    }

    public List<Booking> Get_Bookings_of_Date_and_Doctor(int doctor_id,String Date)
    {
        List<Booking> bookings=Get_All_Bookings();
        List<Booking> d_bookings= new ArrayList<>();

        for (int i=0; i<bookings.size(); i++){
            if(bookings.get(i).getBooking_D_ID()==doctor_id && bookings.get(i).getDate().equals(Date))
                d_bookings.add(bookings.get(i));
        }

        return d_bookings;

    }

    public List<Booking> Get_Bookings_of_Date_and_Patient(int patient_id,String Date)
    {
        List<Booking> bookings=Get_All_Bookings();
        List<Booking> d_bookings= new ArrayList<>();

        for (int i=0; i<bookings.size(); i++){
            if(bookings.get(i).getBooking_P_ID()==patient_id && bookings.get(i).getDate().equals(Date))
                d_bookings.add(bookings.get(i));
        }

        return d_bookings;

    }

    public void deleteBooking(int id){
        mDatabase.delete(DBSchema.BookingTable.Name,"_id="+id,null);
    }


}
