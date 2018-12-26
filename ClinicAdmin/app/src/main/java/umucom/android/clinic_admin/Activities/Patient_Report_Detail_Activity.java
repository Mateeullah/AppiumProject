package umucom.android.clinic_admin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.Patient_report_Detail_Fragment;
import umucom.android.clinic_admin.Models.Patient;

public class Patient_Report_Detail_Activity extends SingleFragmentActivity {


    public static final String EXTRA_NUMBER = "com.umu.android.clinic.extra_number";
    public static Intent newIntent(Context context, int report_id){
        Intent intent = new Intent(context, Patient_Report_Detail_Activity.class);
        intent.putExtra(EXTRA_NUMBER,report_id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return Patient_report_Detail_Fragment.newInstance(getIntent().getIntExtra(EXTRA_NUMBER,-1));
    }
}
