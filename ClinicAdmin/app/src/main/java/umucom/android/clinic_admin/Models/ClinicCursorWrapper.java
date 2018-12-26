package umucom.android.clinic_admin.Models;

import android.database.Cursor;
import android.database.CursorWrapper;

public class ClinicCursorWrapper extends CursorWrapper {

    public ClinicCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public User getUser() {
        int     ID = getInt(0);
        String Name = getString(getColumnIndex(DBSchema.UserTable.Cols.Name));
        String Username = getString(getColumnIndex(DBSchema.UserTable.Cols.Username));
        String Password = getString(getColumnIndex(DBSchema.UserTable.Cols.Password));

        User u= new User(ID,Name,Username,Password);

        return u;
    }

    public Doctor getDoctor() {
        int     ID = getInt(0);
        String Name = getString(getColumnIndex(DBSchema.DoctorTable.Cols.Name));
        String Contact = getString(getColumnIndex(DBSchema.DoctorTable.Cols.Contact));
        String Days = getString(getColumnIndex(DBSchema.DoctorTable.Cols.Days));
        String Title = getString(getColumnIndex(DBSchema.DoctorTable.Cols.Title));
        String Timing = getString(getColumnIndex(DBSchema.DoctorTable.Cols.Timimg));

        Doctor d= new Doctor(ID,Name,Contact,Timing,Title,Days);

        return d;
    }

    public Patient getPatient() {
        int     ID = getInt(0);
        String Name = getString(getColumnIndex(DBSchema.PatientTable.Cols.Name));
        String Username = getString(getColumnIndex(DBSchema.UserTable.Cols.Username));
        String Password = getString(getColumnIndex(DBSchema.UserTable.Cols.Password));
        String Address = getString(getColumnIndex(DBSchema.PatientTable.Cols.Address));
        String Contact = getString(getColumnIndex(DBSchema.PatientTable.Cols.Contact));


      Patient p= new Patient(ID,Name,Contact,Address,Username,Password);
      return p;
    }

    public Qualification getQualification() {
        int     ID = getInt(0);
        String Degree = getString(getColumnIndex(DBSchema.QualificationTable.Cols.Degree));
        String Details = getString(getColumnIndex(DBSchema.QualificationTable.Cols.Details));
        String Institute = getString(getColumnIndex(DBSchema.QualificationTable.Cols.Institute));
        int Qual_doc_id = getInt(getColumnIndex(DBSchema.QualificationTable.Cols.Qual_Doc_ID));
        int Year = getInt(getColumnIndex(DBSchema.QualificationTable.Cols.year));


        Qualification q= new Qualification(ID,Year,Institute,Degree,Details,Qual_doc_id);
        return q;
    }

    public Slot getSlot() {
        int     ID = getInt(0);
        String Details= getString(getColumnIndex(DBSchema.SlotTable.Cols.Details));
        int Slot_doc_id = getInt(getColumnIndex(DBSchema.SlotTable.Cols.Slot_Doc_ID));
        String Time= getString(getColumnIndex(DBSchema.SlotTable.Cols.Time));


        Slot s= new Slot(ID,Slot_doc_id,Time,Details);
        return s;
    }

    public History getHistory() {
        int     ID = getInt(0);
        String Comments = getString(getColumnIndex(DBSchema.HistoryTable.Cols.Comments));
        String Details = getString(getColumnIndex(DBSchema.HistoryTable.Cols.Details));
        int Hist_P_ID = getInt(getColumnIndex(DBSchema.HistoryTable.Cols.History_Patient_ID));

        History h= new History(ID,Details,Comments,Hist_P_ID);
        return h;


    }

    public Booking getBooking() {
        int     ID = getInt(0);
        int B_D_ID = getInt(getColumnIndex(DBSchema.BookingTable.Cols.Booking_Doctor_ID));
        int B_P_ID = getInt(getColumnIndex(DBSchema.BookingTable.Cols.Booking_Patient_ID));
        int B_S_ID = getInt(getColumnIndex(DBSchema.BookingTable.Cols.Booking_Slot_ID));
        String Date = getString(getColumnIndex(DBSchema.BookingTable.Cols.Date));
        String Details = getString(getColumnIndex(DBSchema.BookingTable.Cols.Details));

        Booking b= new Booking(ID,Date,Details,B_P_ID,B_D_ID,B_S_ID);
        return b;
    }

    public Report getReport() {
        int     ID = getInt(0);
        String Path = getString(getColumnIndex(DBSchema.ReportTable.Cols.Path));
        String Comments = getString(getColumnIndex(DBSchema.ReportTable.Cols.Comments));
        String Test = getString(getColumnIndex(DBSchema.ReportTable.Cols.Test));
        String TestDate = getString(getColumnIndex(DBSchema.ReportTable.Cols.TestDate));
        int p_id = getInt(getColumnIndex(DBSchema.ReportTable.Cols.Report_Patient_ID));

        Report r= new Report(ID,p_id,Path,Comments,Test,TestDate);
        return r;
    }


}
