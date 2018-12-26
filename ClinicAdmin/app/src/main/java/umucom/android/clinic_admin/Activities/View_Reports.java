package umucom.android.clinic_admin.Activities;

import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.view_report_fragment;

public class View_Reports extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new view_report_fragment();
    }


}
