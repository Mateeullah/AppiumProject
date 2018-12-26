package umucom.android.clinic_admin.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import umucom.android.clinic_admin.Models.Clinic;
import umucom.android.clinic_admin.R;

public class ContactFragment extends Fragment {


    private EditText mClnicName;
    private EditText mClinicContactNo;
    private EditText mClinicAddress;
    private EditText mClinicSpeciality;
    private EditText mClinicDescription;

    private Button mSaveButton;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_contact_clinic,container,false);
        Clinic clinic = new Clinic();
        clinic.setName("Clinicx");
        clinic.setAddress("Street 1A Iqbal town near PUCIT");
        clinic.setSpeciality("Heart & sugar specialites");
        clinic.setContactNo("0320-11122233");
        mClnicName = v.findViewById(R.id.clinic_name);
        mClnicName.setText(clinic.getName());
        mClinicAddress = v.findViewById(R.id.clinic_address);
        mClinicAddress.setText(clinic.getAddress());
        mClinicSpeciality = v.findViewById(R.id.clinic_speciality);
        mClinicSpeciality.setText(clinic.getSpeciality());
        mClinicContactNo = v.findViewById(R.id.clinic_contact);
        mClinicContactNo.setText(clinic.getContactNo());

        mSaveButton = v.findViewById(R.id.update_details);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(), "Operation Successful", Snackbar.LENGTH_SHORT).show();
            }
        });




        return v;
    }
}
