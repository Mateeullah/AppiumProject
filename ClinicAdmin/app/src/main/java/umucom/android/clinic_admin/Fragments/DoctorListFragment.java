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

import umucom.android.clinic_admin.Activities.DoctorActivity;
import umucom.android.clinic_admin.Models.Doctor;
import umucom.android.clinic_admin.Models.DoctorLab;
import umucom.android.clinic_admin.R;

public class DoctorListFragment extends Fragment {

    private RecyclerView mDoctorRecyclerView;
    private DoctorLab mDoctorLab;
    private DoctorAdapter mdoctorAdpater;
    private ProgressBar mProgressBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDoctorLab = new DoctorLab(getContext());
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list,container,false);
        mDoctorRecyclerView = v.findViewById(R.id.clinic_recycler_view);
        mDoctorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressBar = v.findViewById(R.id.doctor_progressbar);

        updateUI();
        return v;
    }

    public void onResume(){
        super.onResume();
        mdoctorAdpater = null;
        updateUI();
    }

    private void updateUI(){
        mProgressBar.setVisibility(View.VISIBLE);
        DoctorLab doctorLab = new DoctorLab(getActivity());
        List<Doctor> mdoctors = doctorLab.getDoctors();
        mdoctorAdpater = new DoctorAdapter(mdoctors);
        mDoctorRecyclerView.setAdapter(mdoctorAdpater);
        if(mdoctorAdpater==null){
            mdoctorAdpater = new DoctorAdapter(mdoctors);
            mDoctorRecyclerView.setAdapter(mdoctorAdpater);
            mdoctorAdpater.notifyDataSetChanged();
        }else
            mdoctorAdpater.notifyDataSetChanged();

        mProgressBar.setVisibility(View.GONE);
    }

    public class DoctorHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mDoctorTextView;
        private ImageView mDoctorDeleteView;

        private Doctor mdoctor;

        public DoctorHolder(View itemView) {
            super(itemView);
            mDoctorTextView = itemView.findViewById(R.id.list_doctor_name);
            mDoctorDeleteView = itemView.findViewById(R.id.list_doctor_delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent  = DoctorActivity.newIntent(getContext(),mdoctor.getID());
            startActivity(intent);
        }
        public void bindView(Doctor doctor){
            mdoctor = doctor;
            mDoctorTextView.setText(doctor.getName());
            mDoctorDeleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDoctorLab.deleteDoctor(mdoctor.getID());
                    updateUI();

                }
            });
        }
    }

    public class DoctorAdapter extends RecyclerView.Adapter<DoctorHolder>{

        private List<Doctor> mdoctors;

        public DoctorAdapter(List<Doctor> doctors){
            mdoctors = doctors;
        }

        @NonNull
        @Override
        public DoctorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_doctor,parent,false);
            return new DoctorHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DoctorHolder holder, int position) {
            holder.bindView(mdoctors.get(position));



        }

        @Override
        public int getItemCount() {
            return mdoctors.size();
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){

        inflater.inflate(R.menu.doctor_list_menu,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_doctor:
                Intent intent = new Intent(getActivity(),DoctorActivity.class);
                startActivity(intent);
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
