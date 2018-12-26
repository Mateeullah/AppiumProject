package umucom.android.clinic_admin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.View_Appointment_Fragment;

public class View_Appointment extends SingleFragmentActivity {


    public static final String EXTRA_NUMBER = "com.umu.android.clinic.view_appointment_patient_id";
    public static Intent newIntent(Context context, int patient_id){
        Intent intent = new Intent(context, View_Appointment.class);
        intent.putExtra(EXTRA_NUMBER,patient_id);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        return View_Appointment_Fragment.newInstance(getIntent().getIntExtra(EXTRA_NUMBER,-1));
    }
}
