package umucom.android.clinic_admin.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import umucom.android.clinic_admin.Fragments.SlotListFragment;
import umucom.android.clinic_admin.R;

public class SlotListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SlotListFragment();
    }



}
