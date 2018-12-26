package umucom.android.clinic_admin.Activities;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import umucom.android.clinic_admin.Fragments.Available_Slots_Fragment;


public class Available_Slots extends SingleFragmentActivity {

    public static final String EXTRA_NUMBER = "com.umu.android.clinic.patient_id_for_booking";
    public static Intent newIntent(Context context, int doc_id){
        Intent intent = new Intent(context,Available_Slots.class);
        intent.putExtra(EXTRA_NUMBER,doc_id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return Available_Slots_Fragment.newInstance(getIntent().getIntExtra(EXTRA_NUMBER,-1));
    }

}
