package umucom.android.clinic_admin.Models;

public class DBSchema {
    public static final class UserTable{
        public static final String Name="User";

        public static final class Cols{

            public static final String Name="name";
            public static final String Username="username";
            public static final String Password="password";


        }
    }

    public static final class DoctorTable{
        public static final String Name="Doctor";

        public static final class Cols{

            public static final String Name="name";
            public static final String Contact="contact";
            public static final String Timimg="timing";
            public static final String Days="days";
            public static final String Title="title";


        }
    }

    public static final class QualificationTable{
        public static final String Name="Qualification";

        public static final class Cols{

            public static final String Qual_Doc_ID="qual_doc_id";
            public static final String Degree="name";
            public static final String Institute="contact";
            public static final String year="timing";
            public static final String Details="details";


        }
    }

    public static final class SlotTable{
        public static final String Name="Slot";

        public static final class Cols{

            public static final String Slot_Doc_ID="slot_doc_id";
            public static final String Time="time";
            public static final String Details="details";


        }
    }



    public static final class PatientTable{
        public static final String Name="Patient";

        public static final class Cols{

            public static final String Name="name";
            public static final String Contact="contact";
            public static final String Address="address";
            public static final String Username="username";
            public static final String Password="password";


        }
    }

    public static final class HistoryTable{
        public static final String Name="History";

        public static final class Cols{

            public static final String History_Patient_ID="history_patient_id";
            public static final String Comments="comments";
            public static final String Details="details";


        }
    }


    public static final class ClinicTable{
        public static final String Name="Clinic";

        public static final class Cols{

            public static final String Name="name";
            public static final String Contact="contact";
            public static final String Contact2="contact2";
            public static final String Address="address";
            public static final String Doctor="doctor";
            public static final String Doctor2="doctor2";
            public static final String Speciality="speciality";
            public static final String Details="details";


        }
    }

    public static final class ReportTable{
        public static final String Name="Report";

        public static final class Cols{

            public static final String Report_Patient_ID="Report_patient_id";
            public static final String Comments="comments";
            public static final String Path="tath";
            public static final String Test="test";
            public static final String TestDate="testdate";


        }
    }

    public static final class BookingTable{
        public static final String Name="Booking";

        public static final class Cols{

            public static final String Booking_Patient_ID="Booking_patient_id";
            public static final String Booking_Doctor_ID="booking_doctor_id";
            public static final String Booking_Slot_ID="booking_slot_id";
            public static final String Date="date";
            public static final String Details="details";


        }
    }
}
