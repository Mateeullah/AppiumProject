package umucom.android.clinic_admin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.Update_profile_Fragment;

public class Update_Profile extends SingleFragmentActivity {

    public static final String EXTRA_NUMBER = "com.umu.android.clinic.update_patient_id";
    public static Intent newIntent(Context context, int patient_id){
        Intent intent = new Intent(context, Update_Profile.class);
        intent.putExtra(EXTRA_NUMBER,patient_id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return Update_profile_Fragment.newInstance(getIntent().getIntExtra(EXTRA_NUMBER,-1));
    }
}
