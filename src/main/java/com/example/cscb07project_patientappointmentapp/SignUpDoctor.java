package com.example.cscb07project_patientappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class SignUpDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);
    }

    public int SignupDocCheckIfCurrUserNull(){
        FirebaseUser f = FirebaseAuth.getInstance().getCurrentUser();
        if (f == null){
            System.out.println("SIGNUP DOC: CURRENT USER IS NULL\n");
            return 0;

        }
        else{
            System.out.println("SIGNUP DOC: CURRENT USER NOT NULL ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
            return 1;
        }
    }

    public int verifySignUpInfoDoc(int fulln, int usern, int pass, int passConfirm){

        EditText editTextFullName = (EditText) findViewById(fulln);
        String fullname = editTextFullName.getText().toString();
        EditText editTextUsername = (EditText) findViewById(usern);
        String username = editTextUsername.getText().toString();
        EditText editTextPassword = (EditText) findViewById(pass);
        String password = editTextPassword.getText().toString();
        EditText editTextConfirmPassword = (EditText) findViewById(passConfirm);
        String passwordConfirm = editTextConfirmPassword.getText().toString();

        if (fullname.equals("")){
            editTextFullName.setError("Must enter fullname");
            return 1;
        }
        if (username.equals("")) {
            editTextUsername.setError("Must enter username");
            return 1;
        }
        if (password.equals("")){
            editTextPassword.setError("Must enter password");
            return 1;
        }
        if (password.length() < 6){
            editTextPassword.setError("Password must be at least 6 characters long");
            return 1;
        }
        if (passwordConfirm.equals("")){
            editTextConfirmPassword.setError("Must confirm password");
            return 1;
        }
        if (!password.equals(passwordConfirm)){
            editTextConfirmPassword.setError("Passwords must match");
            return 1;
        }
        return 0;
    }

    /** Called when the user taps the CREATE ACCOUNT button */
    public void CreateDoctorAccount(View view) {
        Intent intent = new Intent(this, DoctorMain.class);

        int success = verifySignUpInfoDoc(R.id.signUpFullNameDoc, R.id.signUpUsernameDoc, R.id.signUpPasswordDoc, R.id.signUpPasswordConfirmDoc);
        if (success == 1){
            return;
        }
        EditText editTextFullName = (EditText) findViewById(R.id.signUpFullNameDoc);
        String fullname = editTextFullName.getText().toString();
        EditText editTextUsername = (EditText) findViewById(R.id.signUpUsernameDoc);
        String username = editTextUsername.getText().toString();
        EditText editTextPassword = (EditText) findViewById(R.id.signUpPasswordDoc);
        String password = editTextPassword.getText().toString();

        Spinner genderSpinner = (Spinner) findViewById(R.id.genderSpinnerDoc);
        String gender = String.valueOf(genderSpinner.getSelectedItem());
        Spinner specializationSpinner = (Spinner) findViewById(R.id.doctorSpecializationSignUpDoc);
        String specialization = String.valueOf(specializationSpinner.getSelectedItem());


        SignupDocCheckIfCurrUserNull();

        System.out.println("want to create uder : username: " + username + ", password: " + password + "\n");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                    // new
//                    Date date1 = new Date();
//                    Timestamp t = new Timestamp(date1);
//                    UID doc_uid = new UID(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    //done


//                    Doctor p1 = new Doctor(fullname, username, password, gender, specialization, t, t, doc_uid);
                    // alina changed to go with new constructor for testing purposes
                    Doctor p1 = new Doctor(fullname, username, password, gender, specialization, FirebaseAuth.getInstance().getCurrentUser().getUid());
                    ref.child("Doctors").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(p1);


//                    p1.docs = new ArrayList<Doctor>();
//                    Doctor d1 = new Doctor("Stefan", "alina.buzila@mail.utoronto.ca", "stefan1", "MALE", "IMMUNOLOGY");
//                    p1.docs.add(d1);

//                    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Doctors/Wzhu5WtsOgMKe4j9WYdWhmljR7S2/");
//                    HashMap<String,Object> map = new HashMap<String,Object>();//Creating HashMap
//                    map.put("fullName","SamWinchester");  //Put elements in Map
//                    map.put("isInSupernatural","yesforsure");
//                    ref2.updateChildren(map);

                    startActivity(intent);

                }
                else{
                    System.out.println("Error creating new user -by alina\n");
//                    Log.w("ALINA'S MAIN ACTIVITY", "createUserWithEmail : failure - by alina", task.getException());
                    Toast.makeText(SignUpDoctor.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
//        System.out.println("EMAIL: " + FirebaseAuth.getInstance().getCurrentUser().getEmail() + ", SPECIALIZATION: " + specialization + "\n");
        //@org.jetbrains.annotations.NotNull
    }
}