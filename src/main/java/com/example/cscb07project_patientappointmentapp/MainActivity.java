package com.example.cscb07project_patientappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cscb07project_patientappointmentapp.quickstart.Contract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;


//package com.google.firebase.quickstart.auth;
//import com.google.firebase.auth.AuthResult;


public class MainActivity extends AppCompatActivity implements Contract.View, View.OnClickListener {
    Button btnLogin;
    EditText edtEmail, edtPassword;
    private Presenter mLoginPresenter;
    ProgressDialog mProgressDialog;

    private static final String TAG = "MainActivity";
    public static final String FULLNAME_KEY = "fullname";
    public static final String USERNAME_KEY = "username";
    public static final String GENDER_KEY = "gender";
    public static final String APPOINTMENTS_KEY = "appointment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }
    private void initViews() {
        Toast.makeText(getApplicationContext(), "Internet Required" , Toast.LENGTH_SHORT).show();
        btnLogin = (Button)findViewById(R.id.LoginButton);
        btnLogin.setOnClickListener(this);
        edtEmail = (EditText)findViewById(R.id.MainUsername);
        edtPassword = (EditText)findViewById(R.id.MainPassword);

        mLoginPresenter = new Presenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait, Logging in..");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LoginButton:
                LoginCheckIfCurrUserNull();
                checkLoginDetails();
                //checkIfDocOrPat();
        }
    }


    private void checkLoginDetails() {
        if(!TextUtils.isEmpty(edtEmail.getText().toString()) && !TextUtils.isEmpty(edtPassword.getText().toString())){
            initLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
        }else{
            if(TextUtils.isEmpty(edtEmail.getText().toString())){
                edtEmail.setError("Please enter a valid email");
            }if(TextUtils.isEmpty(edtPassword.getText().toString())){
                edtPassword.setError("Please enter password");
            }
        }
    }

    public int LoginCheckIfCurrUserNull() {
        FirebaseUser f = FirebaseAuth.getInstance().getCurrentUser();
        if (f == null) {
            System.out.println("IN LOGIN CURRENT USER IS NULL\n");
            return 0;
        } else {
            System.out.println("IN LOGIN CURRENT USER NOT NULL ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
            return 1;
        }
    }


    private void initLogin(String email, String password) {
        mProgressDialog.show();
        mLoginPresenter.login(this, email, password);
    }

    @Override
    public void onLoginSuccessDoctor(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Logged in as Doctor" , Toast.LENGTH_LONG).show();
        updateUIDoc();
    }

    @Override
    public void onLoginSuccessPatient(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Successfully Logged in as Patient" , Toast.LENGTH_LONG).show();
        updateUIPatient();
    }


    @Override
    public void onLoginFailure(String message) {
        mProgressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Login Failure. Please try again or Sign up as a Doctor or Patient", Toast.LENGTH_LONG).show();
    }

    /** Called when the user taps the SIGN UP as PATIENT button */
    public void SignUpAsPatient(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the SIGN UP as DOCTOR button */
    public void SignUpAsDoctor(View view) {
        Intent intent = new Intent(this, SignUpDoctor.class);
        startActivity(intent);
    }

    public void updateUIDoc(){
        Intent intent = new Intent(this, DoctorMain.class);
        startActivity(intent);
    }

    public void updateUIPatient(){
        Intent intent = new Intent(this, PatientHomePage.class);
        startActivity(intent);
    }



}


// ------------------------ALINA'S CODE ------------------------------------------------------------
//
//    private static final String TAG = "MainActivity";
//    public static final String FULLNAME_KEY = "fullname";
//    public static final String USERNAME_KEY = "username";
//    public static final String GENDER_KEY = "gender";
//    public static final String APPOINTMENTS_KEY = "appointment";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        FirebaseApp.initializeApp(getApplicationContext());
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (LoginCheckIfCurrUserNull() == 1){
//            // curr user != null
//            checkIfDocOrPat();
//        }
//
////        FirebaseFirestore db = FirebaseFirestore.getInstance();
////        DocumentReference df = db.document("/Patients/someid");
////        Map<Object, Object> p1 = new HashMap<Object, Object>();
////        p1.put(FULLNAME_KEY, "Pasa Aslan");
////        p1.put(USERNAME_KEY, "pssln");
////        p1.put(GENDER_KEY, "MALE");
////        p1.put(APPOINTMENTS_KEY, new ArrayList().toArray());
////        df.set(p1).addOnSuccessListener(new OnSuccessListener<Void>() {
////            @Override
////            public void onSuccess(Void unused) {
////                Log.d(TAG, "Document has been saved!");
////            }
////        }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                Log.w(TAG, "Document is not saved");
////            }
////        });
//
//        // Write a message to the database
////        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference ref = database.getReference();
////
////        Timestamp startDate = new Timestamp(123456789, 0);
////        Timestamp endDate = new Timestamp(987654321, 0);
////
////        UID doctorUID = UID.createUID(database, "doctors");
////        UID patientUID = UID.createUID(database, "patients");
////
////        Patient p = new Patient("asadasd", "lolol", "CuteRabbit38", "male", "2021-08-08", patientUID);
////        Doctor d = new Doctor("first doctor", "frstdctr", "GoofyDoggie96", "male", "immunology", startDate, endDate, doctorUID);
////
////        UID appointmentUID = UID.createUID(database, "appointment");
////
////        Appointment a1 = new Appointment(d, p,startDate, "asdfghhj", appointmentUID);
//    }
//
//
//    public void checkIfDocOrPat(){
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        DatabaseReference docRef = FirebaseDatabase.getInstance().getReference("Doctors/" + uid);
//        docRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    System.out.println("user is a doctor\n");
//                    updateUIDoc();
//                }
//                else{
//                    System.out.println("user is a patient\n");
//                    updateUIPatient();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//                System.out.println("user is not doc or patient\n");
//            }
//        });
//    }
//
//    public int LoginCheckIfCurrUserNull(){
//        FirebaseUser f = FirebaseAuth.getInstance().getCurrentUser();
//        if (f == null){
//            System.out.println("IN LOGIN CURRENT USER IS NULL\n");
//            return 0;
//        }
//        else{
//            System.out.println("IN LOGIN CURRENT USER NOT NULL ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
//            return 1;
//        }
//    }
//
//    /** Called when the user taps the Patient button */
//    public void UserLogin(View view) {
//
//        EditText editTextUsername = (EditText) findViewById(R.id.MainUsername);
//        String username = editTextUsername.getText().toString();
//        EditText editTextPassword = (EditText) findViewById(R.id.MainPassword);
//        String password = editTextPassword.getText().toString();
//        if (username.equals("") ) {
//            editTextUsername.setError("Must enter username");
//            return;
//        }
//        if (password.equals("")){
//            editTextPassword.setError("Must enter password");
//            return;
//        }
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    checkIfDocOrPat();
//                }
//                else{
//                    System.out.println("login not successful\n");
////                    editTextPassword.setError("Login was not sucessful");
//                    Toast.makeText(com.example.cscb07project_patientappointmentapp.MainActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        //@org.jetbrains.annotations.NotNull
//    }
//
//    public void updateUIDoc(){
//        Intent intent = new Intent(this, DoctorMain.class);
//        startActivity(intent);
//    }
//
//    public void updateUIPatient(){
//        Intent intent = new Intent(this, PatientHomePage.class);
//        startActivity(intent);
//    }
//
//
////    /** Called when the user taps the Patient button */
////    public void LoginAsPatient(View view) {
////        Intent intent = new Intent(this, PatientNextAppointments.class);
////        EditText editTextUsername = (EditText) findViewById(R.id.MainUsername);
////        String username = editTextUsername.getText().toString();
////        EditText editTexPassword = (EditText) findViewById(R.id.MainPassword);
////        String password = editTexPassword.getText().toString();
//////        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
////
//////        intent.putExtra(EXTRA_MESSAGE, message);
////        if (username.equals("") ) {
////            editTextUsername.setError("Must enter username");
////            return;
////        }
////        if (password.equals("")){
////            editTexPassword.setError("Must enter password");
////            return;
////        }
////        startActivity(intent);
////    }
//
////    /** Called when the user taps the Doctor button */
////    public void LoginAsDoctor(View view) {
////        Intent intent = new Intent(this, DoctorMain.class);
//////        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName2);
//////        String message = editText.getText().toString();
//////        intent.putExtra(EXTRA_MESSAGE, message);
////        EditText editText = (EditText) findViewById(R.id.MainUsername);
////        String message = editText.getText().toString();
////        EditText editText2 = (EditText) findViewById(R.id.MainPassword);
////        String message2 = editText2.getText().toString();
////
//////        intent.putExtra(EXTRA_MESSAGE, message);
////        if (!message.equals("") && !message2.equals("")) {
////            startActivity(intent);
////        }
////    }
//
//    /** Called when the user taps the SIGN UP as PATIENT button */
//    public void SignUpAsPatient(View view) {
//        Intent intent = new Intent(this, SignUpActivity.class);
//        startActivity(intent);
//    }
//
//    /** Called when the user taps the SIGN UP as DOCTOR button */
//    public void SignUpAsDoctor(View view) {
//        Intent intent = new Intent(this, SignUpDoctor.class);
//        startActivity(intent);
//    }
//}