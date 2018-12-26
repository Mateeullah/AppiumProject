package umucom.android.clinic_admin.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import umucom.android.clinic_admin.Models.Doctor;
import umucom.android.clinic_admin.Models.DoctorLab;
import umucom.android.clinic_admin.Models.Slot;
import umucom.android.clinic_admin.Models.SlotLab;
import umucom.android.clinic_admin.R;

public class SlotFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private DoctorLab mdoctorlab;
    private Doctor mDoctor;

    private Slot mSlot;
    private SlotLab mSlotLab;

    private Button mtimebutton;
    private Button mSlotSaveButton;
    private EditText mSlotDesc;

    private Calendar mTime;

    List<Doctor> mDoctorsList;

    public static final String DIALOG_TIME = "DialogTime";
    public static final int REQUEST_TIME = 0;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mdoctorlab = new DoctorLab(getContext());
        mSlot = new Slot();
        mSlotLab = new SlotLab(getContext());
        mTime = Calendar.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slot,container,false);

        mDoctorsList = mdoctorlab.getDoctors();

        Spinner mSpinner =  v.findViewById(R.id.spinner_doctor);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,
                getDoctorsName(mDoctorsList));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(myAdapter);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDoctor = mDoctorsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //mSpinner.setOnItemSelectedListener(this);


        mtimebutton = v.findViewById(R.id.slot_time_id);
        mtimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment();
                dialog.setTargetFragment(SlotFragment.this,REQUEST_TIME);
                dialog.show(manager,DIALOG_TIME);
            }
        });

        mSlotDesc = v.findViewById(R.id.add_slot_description);

        mSlotSaveButton = v.findViewById(R.id.slot_save_button);
        mSlotSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlotDesc.getText().length()>0 || mtimebutton.getText().length()>0)
                {

                    mSlot.setDoc_ID(mDoctor.getID());
                    mSlot.setDetails(mSlotDesc.getText().toString());
                    mSlot.setTime(mtimebutton.getText().toString());
                    if(mSlotLab.isBooked(mSlot,mSlot.getTime())){
                        Snackbar.make(getView(),"Slot already booked",Snackbar.LENGTH_SHORT).show();
                    }else {
                        mSlotLab.addSlot(mSlot);
                        mSlotSaveButton.setEnabled(false);
                        Snackbar.make(getView(),"Slot Added",Snackbar.LENGTH_SHORT).show();
                    }

                }else{
                    Snackbar.make(getView(),"One or More Field is Empty",Snackbar.LENGTH_SHORT).show();
                }
            }
        });


        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_TIME) {
            Calendar date = (Calendar) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            setTime(date);
            mtimebutton.setText(DateFormat.format("hh:mm aaa",mTime).toString());
        }
    }


    public void setTime(Calendar time){
        mTime.set(Calendar.HOUR_OF_DAY,time.get(Calendar.HOUR_OF_DAY));
        mTime.set(Calendar.MINUTE,time.get(Calendar.MINUTE));
    }

    public List<String> getDoctorsName(List<Doctor> doctorslist){
        List<String> doctorsName = new ArrayList<>();
        for (int i = 0;i<doctorslist.size();i++){
            doctorsName.add(doctorslist.get(i).getName());
        }
        return doctorsName;
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Doctor doctor = mdoctorlab.Get_Doctor(position);
        String name = doctor.getName();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
