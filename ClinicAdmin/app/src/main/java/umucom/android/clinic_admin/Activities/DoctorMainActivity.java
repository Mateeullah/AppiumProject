package umucom.android.clinic_admin.Activities;

import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.DoctorMainFragment;

public class DoctorMainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DoctorMainFragment();
    }

}
