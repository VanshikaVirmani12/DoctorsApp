package com.example.cscb07project_patientappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PatientNextAppointments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_next_appointments);

        System.out.println("in patient next appt\n");
        IntializePatientNextApptListView();
//        onResume(); // i think this is automatically called, no need to call it
    }


    public void IntializePatientNextApptListView(){



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ArrayList<String> nextApptStrings = new ArrayList<String>();

        ListView patientNextAppLV = (ListView)findViewById(R.id.PatientViewNextAppLV);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.doc_display_,R.id.DocInfo, nextApptStrings);
        ref = FirebaseDatabase.getInstance().getReference("Patients/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("data changed\n");
                Patient pat = snapshot.getValue(Patient.class);
                ArrayList<Appointment> upcome = (ArrayList<Appointment>) pat.upcomingAppts;
                if (upcome == null){
                    upcome = new ArrayList<Appointment>();
                }
                for (Appointment ap: upcome){
                    nextApptStrings.add(ap.dateAndTime.toString() + ", " + ap.docFullName);
                }
//                for (String s: nextApptStrings){
//                    System.out.println("next appt: " + s);
//                }
                patientNextAppLV.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




//        ref.child("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //@org.jetbrains.annotations.NotNull
//                        Patient p = snapshot.getValue(Patient.class);
//                        ArrayList<Appointment> upcome = (ArrayList<Appointment>) p.upcomingAppts;
//                        textviewPatientName.setText(name);
//                        textviewPatientDOB.setText(dob);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        //@org.jetbrains.annotations.NotNull
//                    }
//                });
//        textviewPatientEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());


//        dateStrings = new ArrayList<String>();
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.doc_display_,R.id.DocInfo, dateStrings);
//        Iterator hashIterator = nextAvail.entrySet().iterator();
//
//        System.out.println("avali: " + docClicked.getUsername());
//        while (hashIterator.hasNext()) {
//            Map.Entry entry = (Map.Entry)hashIterator.next();
//            Date d1 = (Date)entry.getValue();
//            System.out.println("Availabilities: " + d1.toString());
//            dateStrings.add(d1.toString());
//        }
//        oneDocAvailabilitiesLV.setAdapter(adapter);
    }




    public void signOutFromPatientNextAppointments(View view){
        FirebaseAuth.getInstance().signOut();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            System.out.println("Patient Next appts: After sign out: User == null\n");
        }else{
            System.out.println("Patient Next appts: After sign out: User != null ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        TextView textview = (TextView) findViewById(R.id.PatientLoggedInNextAppts);
        textview.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }
}