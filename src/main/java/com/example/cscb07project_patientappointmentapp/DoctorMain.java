package com.example.cscb07project_patientappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class DoctorMain extends AppCompatActivity {

    private ArrayList<Appointment> upcome;

    private ListView docNextAppLV;
    private Appointment AppClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);


        IntializePatientNextApptListView();
        getAppClicked();

    }


//    public void getApptClicked(){
//        docNextAppLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String dateString = (String) parent.getItemAtPosition(position);
//                System.out.println("i want to see info on app: " + dateString);
//                getApptObjectClickedOn(dateString);
//            }
//        });
//    }
//
//    public void getApptObjectClickedOn(String dateString){
//        for (Appointment a: upcome){
//            if (a.dateAndTime.toString().equals(dateString)){
//                AppClicked = a;
//            }
//        }
//        detailsOnAppView();
//    }
//
//    public void detailsOnAppView(){
//        Intent intent = new Intent(this, DisplayOneAppInfo.class);
//        intent.putExtra("appClicked", AppClicked);
//        startActivity(intent);
//    }

    public void IntializePatientNextApptListView() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ArrayList<String> nextApptStrings = new ArrayList<String>();

        docNextAppLV = (ListView) findViewById(R.id.DocMainNextAppointmentsLV);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.doc_display_, R.id.DocInfo, nextApptStrings);
        ref = FirebaseDatabase.getInstance().getReference("Doctors/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Doctor doc = snapshot.getValue(Doctor.class);
                upcome = (ArrayList<Appointment>) doc.upcomingAppts;
                if (upcome == null) {
                    upcome = new ArrayList<Appointment>();
                }
                for (Appointment ap : upcome) {
                    nextApptStrings.add(ap.dateAndTime.toString());
                }
                docNextAppLV.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void getAppClicked(){
        docNextAppLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stringOfAppclicked = (String) parent.getItemAtPosition(position);
                System.out.println("i i i : " + stringOfAppclicked);
                getAppObjectClickedOn(stringOfAppclicked);
            }
        });
    }

    public void getAppObjectClickedOn(String stringOfAppclicked){
        System.out.println("why i an : " + stringOfAppclicked);
        Appointment app_clickedon = new Appointment(); // this is very bad, should not initialize
        if (upcome == null){
            System.out.println("its NULER\n");
        }
        for (Appointment appt: upcome){
            System.out.println("whys this nukll: " + appt.dateAndTime.toString());
            if (appt.dateAndTime.toString().equals(stringOfAppclicked)){
                app_clickedon = appt;
            }
        }

        Intent intent = new Intent(this, OneOfDocsApptInfo.class);
        intent.putExtra("appClickedOn", app_clickedon);
        startActivity(intent);
    }


    public void signOutFromDoctorMain(View view){
        FirebaseAuth.getInstance().signOut();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            System.out.println("Doc: After sign out: User == null\n");
        }else{
            System.out.println("Doc: After sign out: User != null ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView textview = (TextView) findViewById(R.id.doctorLoggedInDoctorMain);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            textview.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
    }
}