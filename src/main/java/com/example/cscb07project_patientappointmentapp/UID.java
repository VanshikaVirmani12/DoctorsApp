package com.example.cscb07project_patientappointmentapp;

import com.google.firebase.database.FirebaseDatabase;

public class UID {

    String uid;

    public UID (String uid){
        this.uid = uid;
    }

    public static UID createUID(FirebaseDatabase database, String databaseTag){
        databaseTag.toUpperCase();

        if (databaseTag != "PATIENTS" || databaseTag != "DOCTORS" || databaseTag != "APPOINTMENTS"){
            throw new IllegalArgumentException();
        }

        return new UID(database.getReference(databaseTag).push().getKey());
    }
}