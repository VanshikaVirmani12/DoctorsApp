package com.example.cscb07project_patientappointmentapp;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class DoctorIDtoDoctorAdapter {

    public DoctorIDtoDoctorAdapter(){}

    public static Doctor getDoctor(String doctorName, String doctorSpecialty, String doctorGender) {

        System.out.println("ALINA MADE IT\n");
        //This will hold our collection of D-d-d-doctors
        // we use final cause we are using an anonymous class and anonymous classes only take
        // final variables, we can still call methods on those variables tho. No problems there!
        final List<Doctor> theDoctors = new ArrayList<Doctor>();


        // Connecting to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Doctors").addValueEventListener(new ValueEventListener() {

            // This method will be invoked any time the data on the database changes.
            // Additionally, it will be invoked as soon as we connect the listener, so that we
            // can get an initial snapshot of the data on the database.
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this is gonna give me collection of all the references under "Doctor"
                // I am going to get all the children at the doctor level
                Iterable<DataSnapshot> children = snapshot.getChildren();
                // Now we have to "Shake hands" with all of them
                // Each time we shake hands with the object we put it in child
                for (DataSnapshot child : children) {
                    Doctor value = child.getValue(Doctor.class);
                    theDoctors.add(value);
                }
                System.out.println("the doc size: " + theDoctors.size());
                for (Doctor d : theDoctors) {
                    System.out.println("ONE DOC: " + d.getUsername() + d.getSpecialty());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return new Doctor();
    }



    public Doctor getAllDoctorsList() {

        System.out.println("ALINA MADE IT\n");
        // we use final cause we are using an anonymous class and anonymous classes only take
        // final variables, we can still call methods on those variables tho. No problems there!
//        final List<Doctor> theDoctors = new ArrayList<Doctor>();
        final ArrayList<Doctor> theDoctors = new ArrayList<Doctor>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Doctors").addValueEventListener(new ValueEventListener() {

            // This method will be invoked any time the data on the database changes.
            // Additionally, it will be invoked as soon as we connect the listener, so that we
            // can get an initial snapshot of the data on the database.
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this is gonna give me collection of all the references under "Doctor"
                // I am going to get all the children at the doctor level
                Iterable<DataSnapshot> children = snapshot.getChildren();
                // Now we have to "Shake hands" with all of them
                // Each time we shake hands with the object we put it in child
                for (DataSnapshot child : children) {
                    Doctor value = child.getValue(Doctor.class);
                    theDoctors.add(value);
                }

//                for (Doctor d : theDoctors) {
//                    System.out.println("ONE DOC: " + d.getUsername() + d.getSpecialty());
//
//                }
                updateFields(theDoctors);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return new Doctor();
    }

    public void updateFields(ArrayList<Doctor> allDocs){
        System.out.println("IN UPDATE: the doc size: " + allDocs.size());

//        Intent intent = new Intent(this, ListDoctorsActivity.class);
//        Intent intent = new Intent(this, MainActivity.class);

    }

}
// The code below is a list of doctors
// ArrayAdapter<Doctor> doctorArrayAdapter = new ArrayAdapter<Doctor>(getActivity(), android.R.layout.simple_expandable_list_item_1, doctors);
//setListAdapter(doctorArrayAdapter)


// Traversing through list so I can find the Doctor with the target credentials
// let the default doctor be the first doctor in the list for now!
//        System.out.println("the Docs size: " + theDoctors.size());
//        Doctor hold = theDoctors.get(0);
//        for(Doctor doctor : theDoctors){
//            // checks if there is a doctor with the same name, same specialty, same gender
//            if (doctor.getFullName().equals(doctorName) && doctor.getSpecialty().equals(doctorSpecialty.toUpperCase()) && doctor.getGender().equals(doctorGender.toUpperCase())){
//                hold = doctor;
//            }
//        }
//        return hold;
