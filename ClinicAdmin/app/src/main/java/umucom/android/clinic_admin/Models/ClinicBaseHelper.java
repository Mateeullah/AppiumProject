package umucom.android.clinic_admin.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClinicBaseHelper extends SQLiteOpenHelper {

    public static final int Version=1;
    public static final String DATABASE_NAME="ClinicBase.db";

    ClinicBaseHelper(Context context){
        super(context,DATABASE_NAME,null,Version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ DBSchema.UserTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.UserTable.Cols.Name + ", " +
                DBSchema.UserTable.Cols.Username + ", " +
                DBSchema.UserTable.Cols.Password +

                ")");

        db.execSQL("create table "+ DBSchema.DoctorTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.DoctorTable.Cols.Name + ", " +
                DBSchema.DoctorTable.Cols.Contact + ", " +
                DBSchema.DoctorTable.Cols.Days + "," +
                DBSchema.DoctorTable.Cols.Title + ", " +
                DBSchema.DoctorTable.Cols.Timimg +
                ")");

        db.execSQL("create table "+ DBSchema.QualificationTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.QualificationTable.Cols.Degree + ", " +
                DBSchema.QualificationTable.Cols.Details + ", " +
                DBSchema.QualificationTable.Cols.Institute + ", " +
                DBSchema.QualificationTable.Cols.year + ", " +
                DBSchema.QualificationTable.Cols.Qual_Doc_ID +", " +
                "FOREIGN KEY(`"+DBSchema.QualificationTable.Cols.Qual_Doc_ID+"`) REFERENCES `"+DBSchema.DoctorTable.Name +"`(`_id`)"+
                ")");

        db.execSQL("create table "+ DBSchema.SlotTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.SlotTable.Cols.Details + ", " +
                DBSchema.SlotTable.Cols.Time + ", " +
                DBSchema.SlotTable.Cols.Slot_Doc_ID + ", "+
                "FOREIGN KEY(`"+DBSchema.SlotTable.Cols.Slot_Doc_ID+"`) REFERENCES `"+DBSchema.DoctorTable.Name +"`(`_id`)"+

                ")");


        db.execSQL("create table "+ DBSchema.PatientTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.PatientTable.Cols.Name + ", " +
                DBSchema.PatientTable.Cols.Contact + ", " +
                DBSchema.PatientTable.Cols.Username + ", " +
                DBSchema.PatientTable.Cols.Password + ", " +
                DBSchema.PatientTable.Cols.Address +
                ")");

        db.execSQL("create table "+ DBSchema.HistoryTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.HistoryTable.Cols.Comments + ", " +
                DBSchema.HistoryTable.Cols.Details+ ", " +
                DBSchema.HistoryTable.Cols.History_Patient_ID +", " +
                "FOREIGN KEY(`"+DBSchema.HistoryTable.Cols.History_Patient_ID +"`) REFERENCES `"+DBSchema.PatientTable.Name +"`(`_id`)"+
                ")");


        db.execSQL("create table "+ DBSchema.ReportTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.ReportTable.Cols.Report_Patient_ID+ ", " +
                DBSchema.ReportTable.Cols.Comments + ", " +
                DBSchema.ReportTable.Cols.Path+ ", " +
                DBSchema.ReportTable.Cols.Test + ", " +
                DBSchema.ReportTable.Cols.TestDate +", " +
                "FOREIGN KEY(`"+ DBSchema.ReportTable.Cols.Report_Patient_ID+"`) REFERENCES `"+DBSchema.PatientTable.Name +"`(`_id`)"+
                ")");


        db.execSQL("create table "+ DBSchema.ClinicTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.ClinicTable.Cols.Name + ", " +
                DBSchema.ClinicTable.Cols.Address + ", " +
                DBSchema.ClinicTable.Cols.Contact + ", " +
                DBSchema.ClinicTable.Cols.Contact2 + ", " +
                DBSchema.ClinicTable.Cols.Details + ", " +
                DBSchema.ClinicTable.Cols.Doctor + ", " +
                DBSchema.ClinicTable.Cols.Doctor2+ ", " +
                DBSchema.ClinicTable.Cols.Speciality+
                ")");

        db.execSQL("create table "+ DBSchema.BookingTable.Name + "(" +
                " _id integer primary key autoincrement, " +
                DBSchema.BookingTable.Cols.Booking_Doctor_ID + ", " +
                DBSchema.BookingTable.Cols.Booking_Patient_ID+ ", " +
                DBSchema.BookingTable.Cols.Booking_Slot_ID + ", " +
                DBSchema.BookingTable.Cols.Date + ", " +
                DBSchema.BookingTable.Cols.Details + ", " +
                "FOREIGN KEY(`"+ DBSchema.BookingTable.Cols.Booking_Doctor_ID+"`) REFERENCES `"+DBSchema.DoctorTable.Name +"`(`_id`) , "+
                "FOREIGN KEY(`"+ DBSchema.BookingTable.Cols.Booking_Patient_ID+"`) REFERENCES `"+DBSchema.PatientTable.Name +"`(`_id`) , "+
                "FOREIGN KEY(`"+ DBSchema.BookingTable.Cols.Booking_Slot_ID +"`) REFERENCES `"+DBSchema.SlotTable.Name +"`(`_id`)"+

                ")");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
