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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

import umucom.android.clinic_admin.Models.Report;
import umucom.android.clinic_admin.Models.ReportLab;
import umucom.android.clinic_admin.R;

public class ReportFragment extends Fragment {

    private EditText mPatientId;
    private Spinner mPatientTypeSpiner;
    private Button mReportDatebtn;
    private EditText mPatientDesception;
    private EditText mPatientTest;
    private Button mReportSubmitBtn;

    private static final int REQUEST_DATE = 0;
    public static final String DIALOG_DATE = "DialogDate";

    private Report mReport;
    private ReportLab mReportLab;

    public void onCreate(Bundle savedInstanceSate){
        super.onCreate(savedInstanceSate);
        mReportLab = new ReportLab(getContext());
        mReport = new Report();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_report,container,false);
        mPatientId = v.findViewById(R.id.report_patient_id);
        mPatientTypeSpiner = v.findViewById(R.id.report_spinner_patient);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.PatientType));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPatientTypeSpiner.setAdapter(myAdapter);




        mReportDatebtn = v.findViewById(R.id.report_date_id);
        mReportDatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dailog = new DatePickerFragment();
                dailog.setTargetFragment(ReportFragment.this,REQUEST_DATE);
                dailog.show(manager,DIALOG_DATE);


            }
        });

        mPatientDesception = v.findViewById(R.id.report_descrption);

        mPatientTest = v.findViewById(R.id.report_test);

        mReportSubmitBtn = v.findViewById(R.id.report_submit_button);
        mReportSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPatientId.getText().length()<=0 || mReportDatebtn.getText().length()<=0
                        || mPatientTest.getText().length()<0 || mPatientTest.getText().length()<=0){
                    Snackbar.make(getView(),"One or More Field is Empty",Snackbar.LENGTH_SHORT).show();
                }else {
                    mReport.setReport_P_ID(Integer.parseInt(mPatientId.getText().toString()));
                    mReport.setComments(mPatientDesception.getText().toString());
                    mReport.setTest(mPatientTest.getText().toString());
                    mReport.setTestDate(mReportDatebtn.getText().toString());
                    mReportLab.addReport(mReport);
                    mReportSubmitBtn.setEnabled(false);
                    Snackbar.make(getView(), "Report Added", Snackbar.LENGTH_SHORT).show();
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
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            String testDate = DateFormat.format("EEEE,MMM dd, yyyy",date).toString();
            mReport.setTestDate(testDate);
            mReportDatebtn.setText(mReport.getTestDate());


        }
    }


}
