package umucom.android.clinic_admin.Activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import umucom.android.clinic_admin.R;
import umucom.android.clinic_admin.Fragments.SlotFragment;

public class SlotActivity extends SingleFragmentActivity {

        @Override
        protected Fragment createFragment() {
            return new SlotFragment();
        }


}
