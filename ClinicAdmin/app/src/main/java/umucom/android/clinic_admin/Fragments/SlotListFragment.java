package umucom.android.clinic_admin.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import umucom.android.clinic_admin.Activities.SlotActivity;
import umucom.android.clinic_admin.Models.Doctor;
import umucom.android.clinic_admin.Models.DoctorLab;
import umucom.android.clinic_admin.Models.Slot;
import umucom.android.clinic_admin.Models.SlotLab;
import umucom.android.clinic_admin.R;

public class SlotListFragment extends Fragment {


    private RecyclerView mSlotRecyclerView;
    private SlotLab mSlotLab;
    private SlotAdapter mSlotAdapter;
    private ProgressBar mProgressbBar;
    private DoctorLab mDoctorLab;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSlotLab = new SlotLab(getContext());
        mDoctorLab = new DoctorLab(getContext());
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list,container,false);
        mSlotRecyclerView = v.findViewById(R.id.clinic_recycler_view);
        mSlotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressbBar = v.findViewById(R.id.doctor_progressbar);

        updateUI();


        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
       // MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.slot_list_menu,menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_slot:
                Intent intent = new Intent(getActivity(),SlotActivity.class);
                startActivity(intent);
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onResume(){
        super.onResume();
        updateUI();
    }

    public void updateUI(){
        mProgressbBar.setVisibility(View.VISIBLE);
        List<Slot> mSlotList = mSlotLab.getSlots();
        mSlotAdapter = new SlotAdapter(mSlotList);
        mSlotRecyclerView.setAdapter(mSlotAdapter);
        if(mSlotAdapter==null) {
            mSlotAdapter = new SlotAdapter(mSlotList);
            mSlotRecyclerView.setAdapter(mSlotAdapter);
        }else
        {
            mSlotAdapter.notifyDataSetChanged();
        }
        mProgressbBar.setVisibility(View.GONE);

    }

    public class SlotHolder extends RecyclerView.ViewHolder{

        private TextView mSlotDoctorTextView;
        private TextView mSlotDateTextView;
        private ImageView mSlotDeleteView;

        private Slot mSlot;

        public SlotHolder(View itemView) {
            super(itemView);
            mSlotDoctorTextView = itemView.findViewById(R.id.item_slot_doctor_Name);
            mSlotDateTextView = itemView.findViewById(R.id.item_slot_date_view);
            mSlotDeleteView = itemView.findViewById(R.id.item_slot_delete);

        }

        public void bindView(Slot slot){
            mSlot = slot;
            Doctor doctor = mDoctorLab.Get_Doctor(mSlot.getDoc_ID());
            String nam = doctor.getName();

            //String name = mDoctorLab.Get_Doctor(mSlot.getDoc_ID()).getName();
            mSlotDoctorTextView.setText(mDoctorLab.Get_Doctor(mSlot.getDoc_ID()).getName());
            //mSlotDoctorTextView.setText("umar");
            mSlotDateTextView.setText(slot.getTime());
            mSlotDeleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSlotLab.deleteSlot(mSlot.getID());
                    updateUI();

                }
            });
        }
    }

    public class SlotAdapter extends RecyclerView.Adapter<SlotHolder>{

        private List<Slot> mSlot;

        public SlotAdapter(List<Slot> slots){
            mSlot = slots;
        }

        @NonNull
        @Override
        public SlotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_slot,parent,false);
            return new SlotHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SlotHolder holder, int position) {
            holder.bindView(mSlot.get(position));

        }

        @Override
        public int getItemCount() {
            return mSlot.size();
        }
    }




}
