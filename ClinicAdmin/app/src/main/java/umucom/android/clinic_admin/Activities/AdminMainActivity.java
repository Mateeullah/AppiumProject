package umucom.android.clinic_admin.Activities;

import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.AdminMainFragment;

public class AdminMainActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new AdminMainFragment();
    }

}
