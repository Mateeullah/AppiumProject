package umucom.android.clinic_admin.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import umucom.android.clinic_admin.R;

public class DoctorMainFragment extends Fragment {

    Button appointment_btn;
    Button manage_slot_btn;
    Button edit_profile_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_doctor_main,container,false);

        appointment_btn =(Button) v.findViewById(R.id.doctor_appointment);
        appointment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        manage_slot_btn = (Button) v.findViewById(R.id.doctor_manage_slot);
        manage_slot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        edit_profile_btn = (Button) v.findViewById(R.id.doctor_edit_profile);
        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }
}
