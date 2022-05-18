package com.example.cscb07project_patientappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);

    }


    public void goToBookAppt(View view) {
        Intent intent = new Intent(this, ListDoctorsActivity.class); //BookAppointmentActivity.class
        startActivity(intent);
    }

    public void goToViewAppts(View view) {
        Intent intent = new Intent(this, PatientNextAppointments.class);
        startActivity(intent);
    }

    public void signOutPatientHomePage(View view){
        FirebaseAuth.getInstance().signOut();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            System.out.println("Patient: After sign out: User == null\n");
        }else{
            System.out.println("Patient: After sign out: User != null ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView textviewPatientEmail = (TextView) findViewById(R.id.PatientHomePageLoggedInPatientEmail);
        TextView textviewPatientName = (TextView) findViewById(R.id.PatientHomePageLoggedInPatientName);
        TextView textviewPatientDOB = (TextView) findViewById(R.id.PatientHomePageLoggedInPatientBirthday);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

            ref.child("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(
            new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    //@org.jetbrains.annotations.NotNull
                    Patient p = snapshot.getValue(Patient.class);
                    assert p != null;
                    String name = p.fullName;
                    String dob = p.DOB;
                    textviewPatientName.setText(name);
                    textviewPatientDOB.setText(dob);
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {
                    //@org.jetbrains.annotations.NotNull
                }
            });
            textviewPatientEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Contact contact = dataSnapshot.getValue(Contact.class);
//                    String name = contact.name; // "John Doe"
//                    String city = contact.city; // "Texas"
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });

//            ref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    if (task.isSuccessful()){
//                        Log.e("firebase", "Error getting data", task.getException());
//                    }
//                    else{
//                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    }
//                }
//            });
        }
    }

}