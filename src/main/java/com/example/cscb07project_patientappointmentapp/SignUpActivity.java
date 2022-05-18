package com.example.cscb07project_patientappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.SortedList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public int verifySignUpInfoPatient(int fulln, int usern, int pass, int passConfirm, int dateOfB){

        EditText editTextFullName = (EditText) findViewById(fulln);
        String fullname = editTextFullName.getText().toString();

        System.out.println("come catch me \n");

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
        if (passwordConfirm.equals("")){
            editTextConfirmPassword.setError("Must confirm password");
            return 1;
        }
        if (!password.equals(passwordConfirm)){
            editTextConfirmPassword.setError("Passwords must match");
            return 1;
        }
        if (verifyBirthdaySignUpPatient(dateOfB) == 1){
            return 1;
        }
        return 0;
    }

    public int verifyBirthdaySignUpPatient(int dateOfB){

        EditText editTextDateOfBirth = (EditText) findViewById(dateOfB);
        String birthday = editTextDateOfBirth.getText().toString();
        if (birthday.equals("")){
            editTextDateOfBirth.setError("Must enter date of birth");
            return 1;
        }

        Pattern pattern = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}");
        Matcher matcher = pattern.matcher(birthday);
        if (!matcher.matches()){
            editTextDateOfBirth.setError("Wrong format: Must be MM/DD/YYYY. For example Jul 7, 2007 is 07/07/2007");
            return 1;
        }

        String[] arrStr = birthday.split("/");
        int month = Integer.parseInt(arrStr[0]);
        int day = Integer.parseInt(arrStr[1]);
        int year = Integer.parseInt(arrStr[2]);

        if (month < 1 || month > 12){
            editTextDateOfBirth.setError("Valid months are 01, 02, .., 12");
            return 1;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            if (day < 1 || day > 31){
                editTextDateOfBirth.setError("Not a valid day");
                return 1;
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11){
            if (day < 1 || day > 30){
                editTextDateOfBirth.setError("Not a valid day");
                return 1;
            }
        }
        if (month == 2){
            if (day < 1 || day > 28){ // should we consider leap years
                editTextDateOfBirth.setError("Not a valid day");
                return 1;
            }
        }
        if (year > 2021){
            editTextDateOfBirth.setError("Not a valid year");
            return 1;
        }
        return 0;
    }

    /** Called when the user taps the CREATE ACCOUNT button */
    public void CreatePatientAccount(View view) {
        Intent intent = new Intent(this, PatientHomePage.class);

        int success = verifySignUpInfoPatient(R.id.SignUpFullName, R.id.SignUpUsername, R.id.SignUpPassword, R.id.SignUpPasswordConfirm, R.id.DateOfBirthPatientSignUp);
        if (success == 1){
            return;
        }
        EditText editTextFullName = (EditText) findViewById(R.id.SignUpFullName);
        String fullname = editTextFullName.getText().toString();
        EditText editTextUsername = (EditText) findViewById(R.id.SignUpUsername);
        String username = editTextUsername.getText().toString();
        EditText editTextPassword = (EditText) findViewById(R.id.SignUpPassword);
        String password = editTextPassword.getText().toString();

        EditText editTextDateOfBirth = (EditText) findViewById(R.id.DateOfBirthPatientSignUp);
        String birthday = editTextDateOfBirth.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.GenderSpinner);
        String gender = String.valueOf(spinner.getSelectedItem());

        System.out.println("got here\n");
        // authentication check
        FirebaseUser f = FirebaseAuth.getInstance().getCurrentUser();
        if (f == null){
            System.out.println("CURRENT USER IS NULL\n");
        }
        else{
            System.out.println("CURRENT USER NOT NULL\n");
        }

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println("got here\n");
                if (task.isSuccessful()){
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Patient p2 = new Patient(fullname, username, password, gender, birthday, FirebaseAuth.getInstance().getCurrentUser().getUid());

                    //testing to add upcoming appts
//                    Calendar c1 = Calendar.getInstance();
//                    System.out.println("The Current Date is:" + c1.getTime());
//                    Date date = new Date(2004, 7, 19, 14, 0);
//                    Appointment app = new Appointment(new Doctor(), "broke my head", date);
//                    p2.addAppointmentToAppointments(app);
//
////                    Calendar c2 = Calendar.getInstance();
//                    Date date3 = new Date(2004, 7, 18, 12, 0);
//                    Appointment app3 = new Appointment(new Doctor(), "broke my foot", date3);
//                    p2.addAppointmentToAppointments(app3);
////                    c2.add(1, Calendar.DATE);
//                    Date date2 = new Date(2003, 8, 19, 14, 0);
//                    Appointment app2 = new Appointment(new Doctor(), "broke my nose", date2);
//                    p2.addAppointmentToAppointments(app2);
//                    System.out.println("The Current Date is:" + c2.getTime());
                    //testing done
//                    p2.str.add("hi");
//                    p2.str.add("hello");

                    ref.child("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(p2);
                    startActivity(intent);
                }
                else{
                    System.out.println("Error creating new user -by alina\n");
                    Toast.makeText(SignUpActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        //@org.jetbrains.annotations.NotNull
    }
}