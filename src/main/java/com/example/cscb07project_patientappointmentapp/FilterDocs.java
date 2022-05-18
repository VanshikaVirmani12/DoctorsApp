
package com.example.cscb07project_patientappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FilterDocs extends AppCompatActivity {

    HashMap<String, Doctor> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_docs);

//        Bundle bundle = this.getIntent().getExtras();
//        if(bundle != null) {
//            map = (HashMap<String, Doctor>)bundle.getSerializable("HashMap");
//            Doctor d1 = map.get("alinamaria.buzila@gmail.com");
//            if (d1 != null){
//                System.out.println(" GOT ITT: " + d1.getUsername() + ", " + d1.getFullName() + ", " + d1.getSpecialty());
//            }
//            else{
//                System.out.println("not today\n");
//            }
//        }

        Intent intent = getIntent();
        map = (HashMap<String, Doctor>)intent.getSerializableExtra("hashmap");
//        if (map == null){
//            System.out.println("MAP IS NULL\n");
//        }
//        else {
//            Doctor gotten = map.get("alinamaria.buzila@gmail.com");
//            if (gotten != null) {
//                System.out.println(" GOT ITT: " + gotten.getUsername() + ", " + gotten.getFullName() + ", " + gotten.getSpecialty());
//            } else {
//                System.out.println("COULD NOT GET\n");
//            }
//        }
    }

    public void startFilteringThroughDocs(View view){

        Spinner genSpinner = (Spinner) findViewById(R.id.genderSpinnerFilterDoc);
        String gen = String.valueOf(genSpinner.getSelectedItem());
        Spinner specSpinner = (Spinner) findViewById(R.id.doctorSpecializationFilterDoc);
        String spec = String.valueOf(specSpinner.getSelectedItem());
        spec = spec.toUpperCase();
        gen = gen.toUpperCase();


        Intent intent =  new Intent(this, ListFilteredDocs.class);
        intent.putExtra("allDocsMap", (Serializable) map);
        intent.putExtra("gender_filter", gen);
        intent.putExtra("spec_filter", spec);

        startActivity(intent);

    }


    public void signOutFromFilterDocs(View view){
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