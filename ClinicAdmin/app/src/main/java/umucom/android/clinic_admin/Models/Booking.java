package umucom.android.clinic_admin.Models;

public class Booking {

    private int ID;
    private Doctor doctor;
    private Slot slot;
    private Patient patient;
    private String Date;
    private String Details;
    private int Booking_P_ID;
    private int Booking_D_ID;
    private int Booking_S_ID;

    public Booking(){}

    public Booking(int ID, String date, String details, int booking_P_ID, int booking_D_ID, int booking_S_ID) {
        this.ID = ID;
        Date = date;
        Details = details;
        Booking_P_ID = booking_P_ID;
        Booking_D_ID = booking_D_ID;
        Booking_S_ID = booking_S_ID;
    }

    public int getBooking_P_ID() {
        return Booking_P_ID;
    }

    public void setBooking_P_ID(int booking_P_ID) {
        Booking_P_ID = booking_P_ID;
    }

    public int getBooking_D_ID() {
        return Booking_D_ID;
    }

    public void setBooking_D_ID(int booking_D_ID) {
        Booking_D_ID = booking_D_ID;
    }

    public int getBooking_S_ID() {
        return Booking_S_ID;
    }

    public void setBooking_S_ID(int booking_S_ID) {
        Booking_S_ID = booking_S_ID;
    }

    public Booking(int ID, Doctor doctor, Slot slot, Patient patient, String date, String details) {
        this.ID = ID;
        this.doctor = doctor;
        this.slot = slot;
        this.patient = patient;
        Date = date;
        Details = details;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
