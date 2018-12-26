package umucom.android.clinic_admin.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.LoginFragment;

public class AccountActivity extends SingleFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }


}
