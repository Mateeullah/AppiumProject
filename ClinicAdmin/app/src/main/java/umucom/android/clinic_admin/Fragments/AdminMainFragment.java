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

import umucom.android.clinic_admin.Activities.DoctorListActivity;
import umucom.android.clinic_admin.Activities.ReportActivity;
import umucom.android.clinic_admin.Activities.SlotListActivity;
import umucom.android.clinic_admin.Activities.contactActivity;
import umucom.android.clinic_admin.AdminBookingActivity;
import umucom.android.clinic_admin.R;

public class AdminMainFragment extends Fragment {

    private Button Manage_doc_btn;
    private Button slot_btn;
    private Button manage_con_btn;
    private Button manage_report;
    private Button manage_booking;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_main, container, false);

        Manage_doc_btn = (Button) v.findViewById(R.id.manage_doctor);
        Manage_doc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DoctorListActivity.class);
                startActivity(intent);
            }
        });

        slot_btn = v.findViewById(R.id.manage_slot);
        slot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SlotListActivity.class);
                startActivity(intent);
            }
        });

        manage_con_btn = v.findViewById(R.id.contact_details);
        manage_con_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getActivity(),contactActivity.class);
                startActivity(intent);
            }
        });

        manage_report = (Button) v.findViewById(R.id.manage_report);
        manage_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getActivity(),ReportActivity.class);
                startActivity(intent);
            }
        });

        manage_booking = v.findViewById(R.id.manage_booking);
        manage_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AdminBookingActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }
}
