package umucom.android.clinic_admin.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;


import umucom.android.clinic_admin.Activities.AdminMainActivity;
import umucom.android.clinic_admin.Activities.DoctorMainActivity;
import umucom.android.clinic_admin.Activities.MainActivity;
import umucom.android.clinic_admin.FaceDetection.ChooserActivity;
import umucom.android.clinic_admin.Models.Patient;
import umucom.android.clinic_admin.Models.PatientLab;
import umucom.android.clinic_admin.Models.UserLab;
import umucom.android.clinic_admin.R;


public class LoginFragment extends Fragment {

    private Button signUpBtn;
    private Button logInBtn;
    private Button patient_login_btn;
    private AutoCompleteTextView userNameView;
    private AutoCompleteTextView passwordView;
    private SignupFragment mSignupFragment;
    private ProgressDialog mProgress;
    private SQLiteDatabase mDatabase;
    private UserLab mUserLab;
    private PatientLab mPatientLab;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mSignupFragment = new SignupFragment();
        mProgress = new ProgressDialog(getContext());
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);


        mUserLab = new UserLab(getContext());
        mPatientLab = new PatientLab(getContext());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login,container,false);



        userNameView = (AutoCompleteTextView) v.findViewById(R.id.login_email);

        passwordView = (AutoCompleteTextView) v.findViewById(R.id.login_password);



        patient_login_btn = v.findViewById(R.id.patient_login);


        logInBtn = (Button) v.findViewById(R.id.admin_login);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //progressBar.setVisibility(View.VISIBLE);
                String pswrd = passwordView.getText().toString();
                String userName = userNameView.getText().toString();

                boolean check = mUserLab.check_username_password(userName,pswrd);
                if(check) {

                    //Intent intent = new Intent(getActivity(), ChooserActivity.class);
                    Intent intent = new Intent(getActivity(), AdminMainActivity.class);
                    startActivity(intent);
                }else if (userName.equals("doctor") && pswrd.equals("doctor")){
                    Intent intent = new Intent(getActivity(),DoctorMainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"UserName or Password is Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
        patient_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pswrd = passwordView.getText().toString();
                String userName = userNameView.getText().toString();

                if(mPatientLab.check_username_password(userName,pswrd)){
                    int patientId = mPatientLab.get_patientId_with_username(userName);
                    Intent intent = MainActivity.newIntent(getContext(),patientId);
                    startActivity(intent);
                }

            }
        });






        signUpBtn = (Button) v.findViewById(R.id.admin_signup);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,mSignupFragment).addToBackStack(null).commit();
            }
        });



        return v;
    }


}
