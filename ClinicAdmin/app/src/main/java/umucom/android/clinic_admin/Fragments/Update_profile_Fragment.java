package umucom.android.clinic_admin.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import umucom.android.clinic_admin.Models.Patient;
import umucom.android.clinic_admin.Models.PatientLab;
import umucom.android.clinic_admin.R;

public class Update_profile_Fragment extends Fragment {

    private EditText name;
    private EditText contact;
    private EditText address;
    private Button update_btn;
    private PatientLab mpatientlab;
    private Patient mpatient;

    public static final String ARG_PATIENT_ID = "patient_id";


    public static Update_profile_Fragment newInstance(int patient_id) {

        Bundle args = new Bundle();
        args.putInt(ARG_PATIENT_ID,patient_id);
        Update_profile_Fragment fragment = new Update_profile_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mpatientlab=new PatientLab(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_update__profile,container,false);

        name=v.findViewById(R.id.text_name);
        address=v.findViewById(R.id.text_address);
        contact=v.findViewById(R.id.text_phone);

        update_btn=v.findViewById(R.id.update_profile_btn);

        int patient_id =  getArguments().getInt(ARG_PATIENT_ID);
        //mpatient=new Patient();

        mpatient = mpatientlab.Get_Patient(patient_id);
        name.setText(mpatient.getName());
        address.setText(mpatient.getAddress());
        contact.setText(mpatient.getContact());


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_Name=name.getText().toString();
                String str_address=address.getText().toString();
                String str_contact=contact.getText().toString();


                mpatient.setName(str_Name);
                mpatient.setContact(str_contact);
                mpatient.setAddress(str_address);


                mpatientlab.updatePatient(mpatient);

                Toast.makeText(getContext(),"Patient Successfully Updated",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
