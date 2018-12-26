package umucom.android.clinic_admin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import umucom.android.clinic_admin.Fragments.DoctorFragment;
import umucom.android.clinic_admin.Models.Doctor;
import umucom.android.clinic_admin.R;

public class DoctorActivity extends SingleFragmentActivity {

    public static final String EXTRA_NUMBER = "com.umu.android.clinic.extra_number";
    public static Intent newIntent(Context context, int doc_id){
        Intent intent = new Intent(context,DoctorActivity.class);
        intent.putExtra(EXTRA_NUMBER,doc_id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        return DoctorFragment.newInstance(getIntent().getIntExtra(EXTRA_NUMBER,-1));
    }

}
