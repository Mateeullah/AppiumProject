package umucom.android.clinic_admin.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import umucom.android.clinic_admin.Activities.Available_Slots;
import umucom.android.clinic_admin.Activities.Update_Profile;
import umucom.android.clinic_admin.Activities.View_Appointment;
import umucom.android.clinic_admin.Activities.View_Reports;
import umucom.android.clinic_admin.R;

public class Main_Activity_Fragment extends Fragment {


    public static final String ARG_PATIENT_ID = "patient_id";
    int patientId;

    public static Main_Activity_Fragment newInstance(int patientid) {

        Bundle args = new Bundle();
        args.putInt(ARG_PATIENT_ID,patientid);
        Main_Activity_Fragment fragment = new Main_Activity_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        patientId = getArguments().getInt(ARG_PATIENT_ID,-1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);



        Button my_btn=(Button) v.findViewById(R.id.slots_btn);
        Button my_btn1=(Button) v.findViewById(R.id.report_btn);
        Button my_btn2=(Button) v.findViewById(R.id.appoint_btn);
        Button my_btn3=(Button) v.findViewById(R.id.profile_btn);

        my_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= Available_Slots.newIntent(getContext(),patientId);
                startActivity(i);
            }
        });

        my_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),View_Reports.class);
                startActivity(i);
            }
        });

        my_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = View_Appointment.newIntent(getContext(),patientId);
                startActivity(i);
            }
        });

        my_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = Update_Profile.newIntent(getContext(),patientId);
                startActivity(i);
            }
        });



        return v;
    }
}
