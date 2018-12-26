package umucom.android.clinic_admin;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import umucom.android.clinic_admin.Activities.SingleFragmentActivity;
import umucom.android.clinic_admin.Fragments.AdminBookingFragment;

public class AdminBookingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AdminBookingFragment();
    }

}
