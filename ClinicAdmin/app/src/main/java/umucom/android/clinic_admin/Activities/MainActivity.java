package umucom.android.clinic_admin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.Main_Activity_Fragment;

public class MainActivity extends SingleFragmentActivity {


    public static final String EXTRA_NUMBER = "com.umu.android.clinic.patient_id";
    public static Intent newIntent(Context context, int patient_id){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(EXTRA_NUMBER,patient_id);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        return Main_Activity_Fragment.newInstance(getIntent().getIntExtra(EXTRA_NUMBER,-1));
    }
}