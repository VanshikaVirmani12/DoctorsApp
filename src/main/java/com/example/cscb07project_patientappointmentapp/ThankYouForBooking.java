package com.example.cscb07project_patientappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ThankYouForBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you_for_booking);

        Intent intent = getIntent();
        Doctor docBooked = (Doctor)intent.getSerializableExtra("docBookedWith");
        Appointment appBooked = (Appointment)intent.getSerializableExtra("apptBookedComplete");
        displayBookedAppointment(appBooked, docBooked);

//        System.out.println("FUCKKKKK\n");
    }

    public void displayBookedAppointment(Appointment appBooked, Doctor docBooked){

        TextView AppBookedInfo = (TextView) findViewById(R.id.AppBookedInfoTV);
        AppBookedInfo.setText(appBooked.dateAndTime.toString() + "\n" + docBooked.fullName);
    }





    public void backToPatientHomeScreen(View view){
        Intent intent = new Intent(this, PatientHomePage.class);
        startActivity(intent);
    }


    public void signOutFromThankYouForBooking(View view){
        FirebaseAuth.getInstance().signOut();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            System.out.println("PAT: After sign out: User == null\n");
        }else{
            System.out.println("PAT: After sign out: User != null ...... email adress: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}