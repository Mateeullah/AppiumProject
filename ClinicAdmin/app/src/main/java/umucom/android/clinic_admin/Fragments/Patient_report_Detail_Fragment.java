package umucom.android.clinic_admin.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import umucom.android.clinic_admin.Models.Patient;
import umucom.android.clinic_admin.Models.PatientLab;
import umucom.android.clinic_admin.Models.Report;
import umucom.android.clinic_admin.Models.ReportLab;
import umucom.android.clinic_admin.R;

public class Patient_report_Detail_Fragment extends Fragment {

    private EditText patient_name;
    private EditText report_date;
    private EditText report_comments;
    private EditText report_test;

    private Report mreport;
    private ReportLab mreportlab;

    private Patient mpatient;
    private PatientLab mpatientLab;



    public static final String ARG_REPORT_ID = "report_id";


    public static Patient_report_Detail_Fragment newInstance(int reportId) {

        Bundle args = new Bundle();
        args.putInt(ARG_REPORT_ID,reportId);
        Patient_report_Detail_Fragment fragment = new Patient_report_Detail_Fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mreportlab = new ReportLab(getContext());
        mpatientLab = new PatientLab(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.patient_report_details, container, false);

       patient_name=v.findViewById(R.id.patient_name);
       report_test=v.findViewById(R.id.report_test);
       report_date=v.findViewById(R.id.test_date);
       report_comments=v.findViewById(R.id.report_comments);

       int Report_id =  getArguments().getInt(ARG_REPORT_ID);

       mreport = mreportlab.Get_Reports_byId(Report_id);

       mpatient = mpatientLab.Get_Patient(mreport.getReport_P_ID());
       patient_name.setText(mpatient.getName());
       report_date.setText(mreport.getTestDate());
       report_comments.setText(mreport.getComments());
       report_test.setText(mreport.getTest());

        return v;
    }
}
