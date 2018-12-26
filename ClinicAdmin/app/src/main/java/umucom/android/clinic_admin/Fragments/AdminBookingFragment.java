package umucom.android.clinic_admin.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import umucom.android.clinic_admin.Models.Booking;
import umucom.android.clinic_admin.Models.BookingLab;
import umucom.android.clinic_admin.Models.Patient;
import umucom.android.clinic_admin.Models.PatientLab;
import umucom.android.clinic_admin.Models.Slot;
import umucom.android.clinic_admin.Models.SlotLab;
import umucom.android.clinic_admin.R;

public class AdminBookingFragment extends Fragment {

    private RecyclerView BookingRecyclerView;
    private Booking mBooking;
    private BookingLab mBookingLab;
    private List<Booking> bookingList;
    private ProgressBar mProgressbar;
    private BookingAdpater mBookingAdapter;
    private Patient mPatient;
    private PatientLab mPatientLab;
    private Slot mSlot;
    private SlotLab mSlotLab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookingLab = new BookingLab(getContext());
        mPatientLab = new PatientLab(getContext());
        mSlotLab = new SlotLab(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list,container,false);
        BookingRecyclerView = v.findViewById(R.id.clinic_recycler_view);
        mProgressbar = v.findViewById(R.id.doctor_progressbar);
        BookingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    public void updateUI(){
        mProgressbar.setVisibility(View.VISIBLE);
        bookingList = mBookingLab.Get_All_Bookings();
        mBookingAdapter = new BookingAdpater(bookingList);
        BookingRecyclerView.setAdapter(mBookingAdapter);
        mProgressbar.setVisibility(View.GONE);

    }




    public class BookingHolder extends RecyclerView.ViewHolder{

        private TextView mPatientNameTextView;
        private TextView mAppointmentDateTextView;
        private TextView mAppointmentTimeTextView;
        private Button mAppointmentAcceptButton;
        private Button mAppointmentRejectButton;

        private Booking mBooking;

        public BookingHolder(View itemView) {
            super(itemView);
            mPatientNameTextView = itemView.findViewById(R.id.list_appointment_Patient_Name);
            mAppointmentDateTextView = itemView.findViewById(R.id.list_appointment_date);
            mAppointmentTimeTextView = itemView.findViewById(R.id.list_appointment_Time);
            mAppointmentAcceptButton = itemView.findViewById(R.id.approved_booking_btn);
            mAppointmentRejectButton = itemView.findViewById(R.id.reject_booking_btn);

        }

        public void bindView(Booking booking){
            mBooking = booking;
            mPatient = mPatientLab.Get_Patient(mBooking.getBooking_P_ID());
            mSlot = mSlotLab.Get_Slot(mBooking.getBooking_S_ID());
            mPatientNameTextView.setText(mPatient.getName());
            mAppointmentTimeTextView.setText(mSlot.getTime());
            mAppointmentDateTextView.setText(mBooking.getDate());

            mAppointmentAcceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(getView(),"Booking is confirmed",Snackbar.LENGTH_SHORT).show();
                    mBooking.setDetails("CONFIRMED");
                    mAppointmentAcceptButton.setEnabled(false);
                }
            });

            mAppointmentRejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(getView(),"Booking rejected",Snackbar.LENGTH_SHORT).show();
                    mBooking.setDetails("UNCONFIRMED");
                    mAppointmentRejectButton.setEnabled(false);
                }
            });


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
            View view = layoutInflater.inflate(R.layout.list_item_booking,parent,false);
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
