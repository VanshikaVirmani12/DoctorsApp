package com.example.cscb07project_patientappointmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BookAppointmentActivity extends AppCompatActivity //implements AdapterView.OnItemSelectedListener {
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        FirebaseUser f = FirebaseAuth.getInstance().getCurrentUser();
        if (f == null){
            System.out.println("IN BOOK APT CURRENT USER IS NULL\n");
        }
        else{
            System.out.println("IN BOOK APT CURRENT USER NOT NULL ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }

//        Intent intent = new Intent(this, ListDoctorsActivity.class);
//        startActivity(intent);

//        //Getting the instance of Spinner and applying OnItemSelectedListener on it
//        Spinner spin = (Spinner) findViewById(R.id.DoctorSpecialization);
//        //spin.setOnItemSelectedListener(this);
//
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.SpecializationFilter, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin.setAdapter(adapter1);
//        spin.setOnItemSelectedListener(this);
//
//        Spinner spin2 = (Spinner) findViewById(R.id.DoctorList);
//        //spin.setOnItemSelectedListener(this);
//
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.DoctorsList, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin2.setAdapter(adapter2);
//        spin2.setOnItemSelectedListener(this);
//
//        Spinner spin3 = (Spinner) findViewById(R.id.Gender);
//        //spin.setOnItemSelectedListener(this);
//
//        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.SpecializationFilter, android.R.layout.simple_spinner_item);
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin3.setAdapter(adapter3);
//        spin3.setOnItemSelectedListener(this);

    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

    public void signOutFromBookAppts(View view){
        FirebaseAuth.getInstance().signOut();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            System.out.println("Patient: After sign out: User == null\n");
        }else{
            System.out.println("Patient: After sign out: User != null ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        TextView textview = (TextView) findViewById(R.id.PatientLoggedInNextAppts);
//        textview.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//    }
}