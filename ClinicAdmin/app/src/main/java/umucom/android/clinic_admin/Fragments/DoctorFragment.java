package umucom.android.clinic_admin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.List;

import umucom.android.clinic_admin.Models.Doctor;
import umucom.android.clinic_admin.Models.DoctorLab;
import umucom.android.clinic_admin.Models.Qualification;
import umucom.android.clinic_admin.Models.QualificationLab;
import umucom.android.clinic_admin.R;

import static java.lang.Integer.parseInt;

public class DoctorFragment extends Fragment {


    private EditText doc_name;
    private EditText doc_contact_no;
    private EditText doc_working_days;
    private EditText doc_timing;
    private Doctor mdoctor;
    private Button saveDoctorbtn;
    private Button updateDoctorBtn;
    private DoctorLab mDoctorlab;
    private Qualification mqualification;
    private QualificationLab mqualificationlab;
    private Button addqualification_btn;
    private FrameLayout mQualificationContainer;
    private List<Qualification> mQualificationList;
    int doc_id;


    private  EditText mQual_degree;
    private  EditText mQual_institute;
    private  EditText mQual_year;
    private  EditText mQual_details;

    private int doctor_id;
    private int qualification_id;

    public static final String ARG_DOCTOR_CONTACT = "doctor_phone";


    public static DoctorFragment newInstance(int doctorId) {

        Bundle args = new Bundle();
        args.putInt(ARG_DOCTOR_CONTACT,doctorId);
        DoctorFragment fragment = new DoctorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDoctorlab = new DoctorLab(getContext());
        mqualificationlab = new QualificationLab(getContext());

        mqualification = new Qualification();

        doctor_id = getArguments().getInt(ARG_DOCTOR_CONTACT,-1);

        mdoctor = mDoctorlab.Get_Doctor(doctor_id);
        //mqualification = mqualificationlab.Get_Qualifications_of_Doctor(doctor_id).get(0);
        //qualification_id = mqualification.getID();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_doctor, container, false);
        doc_name = v.findViewById(R.id.doc_name_id);
        doc_contact_no = v.findViewById(R.id.doc_contact_id);
        doc_working_days = v.findViewById(R.id.doc_days_id);
        doc_timing = v.findViewById(R.id.doc_timing_id);
        saveDoctorbtn = v.findViewById(R.id.doc_save_id);
        addqualification_btn = v.findViewById(R.id.doc_qualification);
        updateDoctorBtn = v.findViewById(R.id.doc_update_id);

        mQualificationContainer = v.findViewById(R.id.qualification_fragment_container);

        addqualification_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mQualificationContainer.addView(getView(getContext()));
                addqualification_btn.setEnabled(false);

            }
        });

        if(doctor_id>=0){
            saveDoctorbtn.setEnabled(false);
            mQualificationContainer.addView(getView(getContext()));
            addqualification_btn.setEnabled(false);

            int qulificationexits = mqualificationlab.Get_Qualifications_of_Doctor(doctor_id).size();

            if(qulificationexits>0){
                mqualification = mqualificationlab.Get_Qualifications_of_Doctor(doctor_id).get(0);
                qualification_id = mqualification.getID();
            }else {

                mqualification.setDoc_ID(doctor_id);
                mqualificationlab.addQualification(mqualification);
                mqualification = mqualificationlab.Get_Qualifications_of_Doctor(doctor_id).get(0);
            }



            doc_name.setText(mdoctor.getName());
            doc_name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mdoctor.setName(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            doc_contact_no.setText(mdoctor.getContact());
            doc_contact_no.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mdoctor.setContact(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            doc_timing.setText(mdoctor.getTiming());
            doc_timing.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mdoctor.setTiming(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            doc_working_days.setText(mdoctor.getDays());
            doc_working_days.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mdoctor.setDays(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            mQual_institute.setText(mqualification.getInstitution());
            mQual_institute.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mqualification.setInstitution(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            mQual_details.setText(mqualification.getDetails());
            mQual_details.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mqualification.setDetails(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            mQual_degree.setText(mqualification.getDegree());
            mQual_degree.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mqualification.setDegree(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            mQual_year.setText(Integer.toString(mqualification.getYear()));
            mQual_year.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mqualification.setYear(Integer.parseInt(s.toString()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });




        }else {
            updateDoctorBtn.setEnabled(false);
        }



        saveDoctorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(doc_name.getText().length()<=0 || doc_contact_no.getText().length()<=0
                        ||doc_working_days.getText().length()<=0 || doc_timing.getText().length()<=0){
                    Snackbar.make(getView(),"One or more field is Emppty",Snackbar.LENGTH_SHORT).show();
                }else {

                    mdoctor = new Doctor();
                    mdoctor.setName(doc_name.getText().toString());
                    mdoctor.setContact(doc_contact_no.getText().toString());
                    mdoctor.setDays(doc_working_days.getText().toString());
                    mdoctor.setTiming(doc_timing.getText().toString());
                    doc_id = (int) mDoctorlab.addDoctor(mdoctor);
                    Snackbar.make(getView(), "Operation Successful", Snackbar.LENGTH_SHORT).show();
                }
                if(updateDoctorBtn.isEnabled()) {
                    Addqualification();
                }
                updateDoctorBtn.setEnabled(true);
                saveDoctorbtn.setEnabled(false);
                //Snackbar.make(getView(), "Operation Successful", Snackbar.LENGTH_SHORT).show();
            }
        });

        updateDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(updateDoctorBtn.isEnabled()){
                    Addqualification(mqualification);
                }*/
                updateDoctor();
                updateQualification();
                Snackbar.make(getView(), "Operation Successful", Snackbar.LENGTH_SHORT).show();
            }
        });



        return v;
    }

    public void Addqualification(){
        mqualification = new Qualification();
        mqualification.setDegree(mQual_degree.getText().toString());
        mqualification.setDetails(mQual_details.getText().toString());
        //mqualification.setDoc_ID(doc_id);
        mqualification.setYear(parseInt(mQual_year.getText().toString()));
        mqualification.setInstitution(mQual_institute.getText().toString());
        mqualificationlab.addQualification(mqualification);

    }


    public void Addqualification(Qualification mqualification){
        mqualification.setDegree(mQual_degree.getText().toString());
        mqualification.setDetails(mQual_details.getText().toString());
        mqualification.setDoc_ID(doc_id);
        mqualification.setYear(parseInt(mQual_year.getText().toString()));
        mqualification.setInstitution(mQual_institute.getText().toString());
        mqualificationlab.addQualification(mqualification);

    }


    public  View getView(Context context){
        View v = LayoutInflater.from(context).inflate(R.layout.qualification_layout,null,false);
        mQual_degree  = v.findViewById(R.id.qualification_degree);
        mQual_year = v.findViewById(R.id.qualification_year);
        mQual_institute = v.findViewById(R.id.qualification_institute);
        mQual_details = v.findViewById(R.id.qualification_detail);
        return v;
    }

    private void updateQualification(){
        mqualificationlab.updateQualification(mqualification);
    }
    private void updateDoctor(){
        mDoctorlab.updateDoctor(mdoctor);
    }

}
