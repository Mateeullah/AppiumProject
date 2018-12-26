package umucom.android.clinic_admin.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import umucom.android.clinic_admin.Models.Patient;
import umucom.android.clinic_admin.Models.PatientLab;
import umucom.android.clinic_admin.Models.User;
import umucom.android.clinic_admin.Models.UserLab;
import umucom.android.clinic_admin.R;


public class SignupFragment extends Fragment {

    private AutoCompleteTextView mNameView;
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mPasswordView;
    private AutoCompleteTextView mcPasswordView;
    private Button SignupButton;
    private Button patientSignUpbtn;
    SQLiteDatabase mDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup,container,false);

        mNameView = view.findViewById(R.id.user_name);
        mEmailView = view.findViewById(R.id.user_email);
        mPasswordView = view.findViewById(R.id.user_password);
        mcPasswordView = view.findViewById(R.id.user_cpassword);
        patientSignUpbtn = view.findViewById(R.id.sign_up_button_patient);
        SignupButton = view.findViewById(R.id.sign_up_button);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mNameView.getText().toString();
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                String cpassword = mcPasswordView.getText().toString();
               if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()){
                    Toast.makeText(getContext(),"One or more field is empty",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(cpassword)){
                    Toast.makeText(getContext(),"Password don't match",Toast.LENGTH_SHORT).show();
                }
                else{
                   User user = new User();
                   user.setName(name);
                   user.setUsername(email);
                   user.setPassword(password);
                   UserLab userLab = new UserLab(getContext());

                   userLab.addUser(user);
                   Snackbar.make(getView(),"Login ID Sucessfully Created",Snackbar.LENGTH_SHORT).show();

                }
            }
        });
        patientSignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mNameView.getText().toString();
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                String cpassword = mcPasswordView.getText().toString();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()){
                    Toast.makeText(getContext(),"One or more field is empty",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(cpassword)){
                    Toast.makeText(getContext(),"Password don't match",Toast.LENGTH_SHORT).show();
                }
                else{
                    Patient patient = new Patient();
                    patient.setName(name);
                    patient.setUsername(email);
                    patient.setPassword(password);
                    PatientLab patientLab  = new PatientLab(getContext());
                    patientLab.addPatient(patient);

                    Snackbar.make(getView(),"Login ID Sucessfully Created",Snackbar.LENGTH_SHORT).show();

                }

            }
        });

        return view;
    }

}
