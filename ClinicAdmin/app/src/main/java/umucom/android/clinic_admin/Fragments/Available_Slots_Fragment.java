package umucom.android.clinic_admin.Fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import umucom.android.clinic_admin.Models.Booking;
import umucom.android.clinic_admin.Models.BookingLab;
import umucom.android.clinic_admin.Models.Doctor;
import umucom.android.clinic_admin.Models.DoctorLab;
import umucom.android.clinic_admin.Models.PatientLab;
import umucom.android.clinic_admin.Models.Slot;
import umucom.android.clinic_admin.Models.SlotLab;
import umucom.android.clinic_admin.R;

public class Available_Slots_Fragment extends Fragment {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private List<String> my_list=new ArrayList<>();
    private Booking mBooking;
    private BookingLab mBookingLab;
    List<Doctor> mDoctorsList;
    List<Slot> mSlotList;
    private DoctorLab mdoctorlab;
    Doctor mDoctor;
    Slot mSlot;

    int patient_id;
    SlotLab slotlab;

    private PatientLab mPatientLab;

    List<String> slot_list;
    String[] arr;
    String temp="None";


    public static final String ARG_PATIENT_ID_Slot = "patient_id_slot";


    public static Available_Slots_Fragment newInstance(int patient_id) {

        Bundle args = new Bundle();
        args.putInt(ARG_PATIENT_ID_Slot,patient_id);
        Available_Slots_Fragment fragment = new Available_Slots_Fragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mdoctorlab=new DoctorLab(getContext());
        slotlab=new SlotLab(getContext());
        mBookingLab = new BookingLab(getContext());
        mPatientLab = new PatientLab(getContext());
        patient_id =  getArguments().getInt(ARG_PATIENT_ID_Slot);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_available__slots,container,false);
        final ListView list=(ListView) v.findViewById(R.id.slots_list);

        mDoctorsList = mdoctorlab.getDoctors();
        mSlotList = slotlab.getSlots();

        Button Search_btn=(Button) v.findViewById(R.id.slot_search);

        mDisplayDate=(TextView) v.findViewById(R.id.date_view);


        Spinner my_spinner=(Spinner) v.findViewById(R.id.doctor_spinner);
        ArrayAdapter<String> myadapter=new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,getDoctorsName(mDoctorsList));

        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        my_spinner.setAdapter(myadapter);

        my_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDoctor = mDoctorsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;

                String date=month+"/"+day+"/"+year;
                mDisplayDate.setText(date);
            }
        };

        Search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int doc_id=mDoctor.getID();
                mSlotList=slotlab.Get_Slots_of_Doctor(doc_id);

                ListAdapter listAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,getSlotTime(mSlotList));
                list.setAdapter(listAdapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mBooking = new Booking();
                        Date date = new Date();
                        String testDate = DateFormat.format("EEEE,MMM dd, yyyy",date).toString();
                        mSlot = mSlotList.get(position);
                        mBooking.setBooking_S_ID(mSlot.getID());
                        mBooking.setBooking_S_ID(mSlot.getDoc_ID());
                        mBooking.setBooking_P_ID(patient_id);
                        mBooking.setDetails("UNCONFIRMED");
                        mBooking.setDate(testDate);
                        if(mBookingLab.booking_available(mSlot.getDoc_ID(),mSlot.getID(),date.toString())){
                            mBookingLab.addBooking(mBooking);
                            Snackbar.make(getView(),"Sucessfully booked",Snackbar.LENGTH_SHORT).show();
                        }
                        else{
                            Snackbar.make(getView(),"Already Booked",Snackbar.LENGTH_SHORT).show();
                        }

                    }

                });
            }
        });
        return v;
    }
    public List<String> getDoctorsName(List<Doctor> doctorslist){
        List<String> doctorsName = new ArrayList<>();
        for (int i = 0;i<doctorslist.size();i++){
            doctorsName.add(doctorslist.get(i).getName());
        }
        return doctorsName;
    }

    public List<String> getSlotTime(List<Slot> SlotList){
        List<String> slotsTime = new ArrayList<>();
        for (int i=0;i<SlotList.size();i++){
            slotsTime.add(SlotList.get(i).getTime());
        }
        return slotsTime;
    }

}
