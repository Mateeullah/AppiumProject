package umucom.android.clinic_admin.Activities;

import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.ReportFragment;

public class ReportActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ReportFragment();
    }

}
