
package umucom.android.clinic_admin.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import umucom.android.clinic_admin.R;

public class DoctorAppointmentsListFragment extends Fragment {

    private RecyclerView mDoctorAppointmentsList;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list,container,false);
        mDoctorAppointmentsList = v.findViewById(R.id.clinic_recycler_view);
        mDoctorAppointmentsList.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

/*
    public class BookingHolder extends RecyclerView.ViewHolder{

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

    public class BookingAdapter extends RecyclerView.Adapter<BookingHolder>{

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




}*/


}

