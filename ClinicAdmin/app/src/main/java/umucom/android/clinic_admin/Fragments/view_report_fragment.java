package umucom.android.clinic_admin.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import umucom.android.clinic_admin.Activities.Patient_Report_Detail_Activity;
import umucom.android.clinic_admin.Models.Report;
import umucom.android.clinic_admin.Models.ReportLab;
import umucom.android.clinic_admin.Models.Slot;
import umucom.android.clinic_admin.R;

import static android.content.ContentValues.TAG;

public class view_report_fragment extends Fragment {

    ListView list;
//    String[] items={"Apple","Mango","Lychee","Orange"};
    String report="No";
    int id;
    List<String> report1=new ArrayList<>();
    List<Report> reports=new ArrayList<>();
    private EditText patient_id_text;
    private Report mreport;
    private ReportLab mreportlab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mreportlab = new ReportLab(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view__reports,container,false);

        list= v.findViewById(R.id.report_list);
        patient_id_text=v.findViewById(R.id.report_text);





        Button Search_btn=(Button) v.findViewById(R.id.report_search_btn);



        Search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id= Integer.parseInt(patient_id_text.getText().toString());
                reports=mreportlab.Get_Reports_of_Patient(id);

                ListAdapter listAdapter1 = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,getReportDate(reports));
                Log.d(TAG, "After declaring adapter");
                list.setAdapter(listAdapter1);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mreport = reports.get(position);
                        Intent intent = Patient_Report_Detail_Activity.newIntent(getContext(),mreport.getID());
                        startActivity(intent);
                    }
                });
            }
        });
        return v;
    }

    public List<String> getReportDate(List<Report> ReportList){
        List<String> ReportDate = new ArrayList<>();
        for (int i=0;i<ReportList.size();i++){
            ReportDate.add(ReportList.get(i).getTestDate());
        }
        return ReportDate;
    }
}
