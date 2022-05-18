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

public class OneOfDocsApptInfo extends AppCompatActivity {

    private Appointment app_clickedon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_of_docs_appt_info);


        Intent intent = getIntent();
        app_clickedon = (Appointment) intent.getSerializableExtra("appClickedOn");

        IntializeApptInfoTV();
    }


    public void IntializeApptInfoTV() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        TextView textv = (TextView) findViewById(R.id.OneOfDocsAppInfoTV);
        System.out.println("ggggg + " +  app_clickedon.pat_uid);

        ref.child("Patients").child(app_clickedon.pat_uid).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Patient docsPat = snapshot.getValue(Patient.class);
                        String allDocsVis = "";
                        if (docsPat.visitedDocFullName == null){
                            System.out.println("visited null\n");
                        }
                        for (String s: docsPat.visitedDocFullName){
                            allDocsVis += ", ";
                            allDocsVis += s;
                        }
                        textv.setText(app_clickedon.dateAndTime.toString() + "\n" + "Patient name: " + docsPat.fullName + "\n" + "Patient email: " + docsPat.username + "\n" + docsPat.gender.toString() + "\n" + "Patient Date Of Birth: " + docsPat.DOB + "\n" + "Doctors "  + docsPat.fullName + " has seen: " + allDocsVis);
                    }
                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {
                        //@org.jetbrains.annotations.NotNull
                    }
                });


//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.doc_display_, R.id.DocInfo, nextApptStrings);
//        ref = FirebaseDatabase.getInstance().getReference("Doctors/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Doctor doc = snapshot.getValue(Doctor.class);
//                upcome = (ArrayList<Appointment>) doc.upcomingAppts;
//                if (upcome == null) {
//                    upcome = new ArrayList<Appointment>();
//                }
//                for (Appointment ap : upcome) {
//                    nextApptStrings.add(ap.dateAndTime.toString());
//                }
//                docNextAppLV.setAdapter(adapter);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
    }

    public void signOutFromOneOfDocsApptInfo(View view){
        FirebaseAuth.getInstance().signOut();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            System.out.println("Patient: After sign out: User == null\n");
        }else{
            System.out.println("Patient: After sign out: User != null ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}