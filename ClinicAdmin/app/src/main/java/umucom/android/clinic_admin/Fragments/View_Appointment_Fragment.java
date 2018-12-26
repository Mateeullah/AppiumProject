package umucom.android.clinic_admin.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import umucom.android.clinic_admin.Models.Booking;
import umucom.android.clinic_admin.Models.BookingLab;
import umucom.android.clinic_admin.Models.Patient;
import umucom.android.clinic_admin.Models.PatientLab;
import umucom.android.clinic_admin.Models.Slot;
import umucom.android.clinic_admin.Models.SlotLab;
import umucom.android.clinic_admin.R;

public class View_Appointment_Fragment extends Fragment {

    private RecyclerView mPatientAppointmentRecyclerView;
    private Booking mBooking;
    private BookingLab mBookingLab;
    private BookingAdpater mBookingAdapter;
    private Patient mPatient;
    private PatientLab mPatientLab;
    private Slot mSlot;
    private SlotLab mSlotLab;
    int patient_id;
    private ProgressBar mProgreebar;


    public static final String ARG_PATIENT_ID_APPOINTMENT = "patient_id";


    public static View_Appointment_Fragment newInstance(int patient_id) {

        Bundle args = new Bundle();
        args.putInt(ARG_PATIENT_ID_APPOINTMENT,patient_id);
        View_Appointment_Fragment fragment = new View_Appointment_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookingLab = new BookingLab(getContext());
        mPatientLab = new PatientLab(getContext());
        mSlotLab = new SlotLab(getContext());
        patient_id = getArguments().getInt(ARG_PATIENT_ID_APPOINTMENT,-1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        mPatientAppointmentRecyclerView = v.findViewById(R.id.clinic_recycler_view);
        mProgreebar = v.findViewById(R.id.doctor_progressbar);
        mPatientAppointmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();




        return v;
    }

    public void updateUI(){
        mProgreebar.setVisibility(View.VISIBLE);
        List<Booking> mBookingPatientList = mBookingLab.Get_Booking_of_Patient(patient_id);
        mBookingAdapter = new BookingAdpater(mBookingPatientList);
        mPatientAppointmentRecyclerView.setAdapter(mBookingAdapter);
        mProgreebar.setVisibility(View.GONE);

    }



    public class BookingHolder extends RecyclerView.ViewHolder{

        private TextView mPatientNameTextView;
        private TextView mAppointmentDateTextView;
        private ImageView mAppointmentDeleteView;
        private TextView mAppointmentTimeTextView;

        private Booking mBooking;

        public BookingHolder(View itemView) {
            super(itemView);
            mPatientNameTextView = itemView.findViewById(R.id.list_appointment_Patient_Name);
            mAppointmentDateTextView = itemView.findViewById(R.id.list_appointment_date);
            mAppointmentDeleteView = itemView.findViewById(R.id.list_appointment_delete);
            mAppointmentTimeTextView = itemView.findViewById(R.id.list_appointment_Time);

        }

        public void bindView(Booking booking){
            mBooking = booking;
            mPatient = mPatientLab.Get_Patient(patient_id);
            mSlot = mSlotLab.Get_Slot(booking.getBooking_S_ID());

            mPatientNameTextView.setText(mPatient.getName());
            mAppointmentTimeTextView.setText(mSlot.getTime());
            mAppointmentDateTextView.setText(mBooking.getDate());

            mAppointmentDeleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBookingLab.deleteBooking(mBooking.getID());
                    updateUI();
                }
            });


           /* Doctor doctor = mDoctorLab.Get_Doctor(mSlot.getDoc_ID());
            String nam = doctor.getName();

            //String name = mDoctorLab.Get_Doctor(mSlot.getDoc_ID()).getName();
            mSlotDoctorTextView.setText(mDoctorLab.Get_Doctor(mSlot.getDoc_ID()).getName());
            //mSlotDoctorTextView.setText("umar");
            mSlotDateTextView.setText(slot.getTime());
            mSlotDeleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSlotLab.deleteSlot(mSlot.getID());
                    updateUI();

                }
            });*/
        }
    }

    public class BookingAdpater  extends RecyclerView.Adapter<BookingHolder>{

        private List<Booking> mBooking;

        public BookingAdpater(List<Booking> bookings){
            mBooking = bookings;
        }

        @NonNull
        @Override
        public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_appointment_doctor,parent,false);
            return new BookingHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookingHolder holder, int position) {
            holder.bindView(mBooking.get(position));

        }

        @Override
        public int getItemCount() {
            return mBooking.size();
        }
    }




}
