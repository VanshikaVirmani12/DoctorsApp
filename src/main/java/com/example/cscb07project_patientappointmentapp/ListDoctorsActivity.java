package com.example.cscb07project_patientappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ListDoctorsActivity extends AppCompatActivity {

    private ListView DocsLV;
    ArrayList<String> allDoctors;
    DatabaseReference reference;
    HashMap<String, Doctor> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctors);

        DocsLV = (ListView)findViewById(R.id.idLVAllDocs);
        allDoctors = new ArrayList<String>();
        map = new HashMap<>();
        initializeListView();
        System.out.println("DID WE GET HERE--\n");
        getDoctorClicked();
    }

    private void initializeListView() {
        System.out.println("REACHED\n");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.doc_display_,R.id.DocInfo, allDoctors);
        reference = FirebaseDatabase.getInstance().getReference("Doctors/");
        // in below line we are calling method for add child event listener to get the child of our database.
        //________________________________________________________________________________________________________________________
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    Doctor value = child.getValue(Doctor.class);
                    allDoctors.add(value.getFullName() + ", " + value.getUsername() + ", " + value.getGender().toString() + ", " + value.getSpecialty().toString());
                    map.put(value.getUsername(), value);
                }
                DocsLV.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void getDoctorClicked(){
//        System.out.println("got to school\n");
        DocsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                System.out.println("my string: " + s);
                getDoctorObjectClickedOn(s);
            }
        });
    }

    public void getDoctorObjectClickedOn(String s){
        String[] arrOfStr = s.split(", ");
        String u = arrOfStr[1];
        Doctor d_clickedOn = map.get(u);

        Intent intent = new Intent(this, OneDocsAvailableTimes.class);
        intent.putExtra("docClickedOn", d_clickedOn);
        startActivity(intent);
    }

    public void filterThroughDocs(View view){
        System.out.println("WE CLICKED THE FILTER\n");
        Intent intent = new Intent(this, FilterDocs.class);
        intent.putExtra("hashmap", (Serializable) map);
        startActivity(intent);

//        ArrayList<Object> object = new ArrayList<Object>();
////        Intent intent = new Intent(Current.class, Transfer.class);
//        Bundle args = new Bundle();
//        args.putSerializable("ARRAYLIST",(Serializable)object);
//        intent.putExtra("BUNDLE",args);
////        startActivity(intent);

//        intent.putExtra("mapper", (Serializable) map);

//        ArrayList<String> dv = new ArrayList<String>();
//        dv.add(new Patient());
//        Bundle extras = new Bundle();
//        extras.putSerializable("HashMap",dv);
//        intent.putExtras(extras);
    }



//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                ImageView selectedImage = (ImageView) view.findViewById(R.id.checkBox);
//
//                selectedImage.setImageResource(R.drawable.simplecheck);
//                String str =  parent.getAdapter().getItem(position).toString();
//
//                Toast.makeText(getApplicationContext(), "  " + str , Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    }

    //// ________________________________________________________________________________________________________________________






//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // this method is called when new child is added to
//                // our data base and after adding new child
//                // we are adding that item inside our array list and
//                // notifying our adapter that the data in adapter is changed.
//                allDoctors.add(snapshot.getValue(Doctor.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // this method is called when the new child is added.
//                // when the new child is added to our list we will be
//                // notifying our adapter that data has changed.
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                // below method is called when we remove a child from our database.
//                // inside this method we are removing the child from our array list
//                // by comparing with it's value.
//                // after removing the data we are notifying our adapter that the
//                // data has been changed.
//                allDoctors.remove(snapshot.getValue(Doctor.class));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                // this method is called when we move our
//                // child in our database.
//                // in our code we are note moving any child.
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // this method is called when we get any
//                // error from Firebase with error.
//            }
//        });
    // below line is used for setting
    // an adapter to our list view.
//        if (DocsLV== null){
//            System.out.println("DocsLV is null\n");
//        }
//        if (adapter == null){
//            System.out.println("adapter is null\n");
//        }
//        DocsLV.setAdapter(adapter);
//    }


    public void signOutFromListDoctors(View view){
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


































//
//
////    ArrayList<String[]> DocDisplayList;
////    TextView[] docTextViews;
////    int j =0;
//    ArrayList<Doctor> allDoctorsatClinic;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_doctors);
//
////        listDoctors();
////        System.out.println("SIZERRRRR: " + DocDisplayList.size() + "\n");
////        onResume();
//        allDoctorsatClinic = new ArrayList<Doctor>();
//        gdl();
//
//        System.out.println("IN MAINER: SIZE: " + allDoctorsatClinic.size());
//        for (Doctor d : allDoctorsatClinic) {
//            System.out.println("IN MAINER: " + d.getUsername() + d.getSpecialty());
//        }
//    }
//
//    public void gdl() {
//
//        System.out.println("ALINA MADE IT\n");
//        // we use final cause we are using an anonymous class and anonymous classes only take
//        // final variables, we can still call methods on those variables tho. No problems there!
////        final List<Doctor> theDoctors = new ArrayList<Doctor>();
//        final ArrayList<Doctor> theDoctors = new ArrayList<Doctor>();
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference();
//        databaseReference.child("Doctors").addValueEventListener(new ValueEventListener() {
//
//            // This method will be invoked any time the data on the database changes.
//            // Additionally, it will be invoked as soon as we connect the listener, so that we
//            // can get an initial snapshot of the data on the database.
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // this is gonna give me collection of all the references under "Doctor"
//                // I am going to get all the children at the doctor level
//                Iterable<DataSnapshot> children = snapshot.getChildren();
//                // Now we have to "Shake hands" with all of them
//                // Each time we shake hands with the object we put it in child
//                for (DataSnapshot child : children) {
//                    Doctor value = child.getValue(Doctor.class);
//                    theDoctors.add(value);
//                }
//
////                for (Doctor d : theDoctors) {
////                    System.out.println("ONE DOC: " + d.getUsername() + d.getSpecialty());
////
////                }
//                updateFields(theDoctors);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
//
//
//
//
//    public void updateFields(ArrayList<Doctor> allDocs){
//        System.out.println("READY TO ADD AMOUNT OF DOCS " + allDocs.size());
//
//        TextView[] docTextViews = new TextView[7]; //DocDisplayList.size()
//
//        docTextViews[0] = (TextView) findViewById(R.id.Doc1DocList);
//        docTextViews[1] = (TextView) findViewById(R.id.Doc2DocList);
//        docTextViews[2] = (TextView) findViewById(R.id.Doc3DocList);
//        docTextViews[3] = (TextView) findViewById(R.id.Doc4DocList);
//        docTextViews[4] = (TextView) findViewById(R.id.Doc5DocList);
//        docTextViews[5] = (TextView) findViewById(R.id.Doc6DocList);
//        docTextViews[6] = (TextView) findViewById(R.id.Doc7DocList);
//        int i = 0;
//        for (Doctor d : allDocs) {
//            if (i < 7){
//                docTextViews[i].setText(d.getFullName() + ", " + d.getSpecialty());
//            }
//            i++;
//            System.out.println("IN BEACH: ONE DOC: " + d.getUsername() + d.getSpecialty());
//        }
//
//
//        for (Doctor doc: allDocs) {
//            allDoctorsatClinic.add((Doctor)doc.clone());
//        }
//
//    }
//
////
////    public void listAllDocsFirst(ArrayList<Doctor> allDocs) {
//////        int i = 0;
//////        for (String[] b: DocDisplayList){
//////            if (i < 7){
//////                docTextViews[i].setText(b[0] + ", " + b[1] +", " + b[2]);
//////            }
//////        }
////
////
////    }
//
////    @Override
////    public void onResume() {
////
////        super.onResume();
//////        int i = 0;
//////        for (String[] b: DocDisplayList){
//////            System.out.println("yoyoyo\n");
//////            if (i < 7){
//////                docTextViews[i].setText(b[0] + ", " + b[1] +", " + b[2]);
//////                System.out.println("doc name: " + b[0] + "speciality: " + b[2]);
//////            }
//////            i++;
//////        }
////////        System.out.println("First Doc: name: " + DocDisplayList[0][0] + "speciality: " + DocDisplayList[0][2]);
//////        TextView t = (TextView) findViewById(R.id.Doc1DocList);
//////       t.setText("ALINAAAAAA");
////    }
//
//
//
//
//
////   public void listDoctors() {
////
////       String[] a = new String[3];
////       DocDisplayList = new ArrayList<String[]>();
////       docTextViews = new TextView[7]; //DocDisplayList.size()
////        j = 7;
////
////       docTextViews[0] = (TextView) findViewById(R.id.Doc1DocList);
////       docTextViews[1] = (TextView) findViewById(R.id.Doc2DocList);
////       docTextViews[2] = (TextView) findViewById(R.id.Doc3DocList);
////       docTextViews[3] = (TextView) findViewById(R.id.Doc4DocList);
////       docTextViews[4] = (TextView) findViewById(R.id.Doc5DocList);
////       docTextViews[5] = (TextView) findViewById(R.id.Doc6DocList);
////       docTextViews[6] = (TextView) findViewById(R.id.Doc7DocList);
////
////
////       FirebaseDatabase database = FirebaseDatabase.getInstance();
////       DatabaseReference dinosaursRef = database.getReference("Doctors");
////       dinosaursRef.orderByChild("fullname").addChildEventListener(new ChildEventListener() {
////           @Override
////           public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
////               Doctor doc = snapshot.getValue(Doctor.class);
////               a[0] = doc.fullName;
////               a[1]= String.valueOf(doc.gender);
////               a[2] = String.valueOf(doc.specialty);
////               DocDisplayList.add(a);
////               System.out.println(snapshot.getKey() + " was " + doc.fullName);
////               System.out.println("size in loop: " + DocDisplayList.size() + "\n" );
////               j =5;
////               System.out.println("j is : " + j);
////               displayDocs();
////
////               for (String[] b: DocDisplayList){
////                   System.out.println("the list:" +  b[0] + ", " + b[1] +", " + b[2]);
////               }
////           }
////           public void displayDocs(){
////    //               System.out.println("dWOOO: oc name: " + a[0] + "speciality: " + a[2] + "\n");
////               System.out.println("sizeeee: " + DocDisplayList.size() + "\n" );
////               if (DocDisplayList.size() < 7) {
////                   int i = 0;
////                   for (String[] b: DocDisplayList){
////                       System.out.println("yoyoyo\n");
////                       if (i < 7 && i < DocDisplayList.size()){
////                           docTextViews[i].setText(b[0] + ", " + b[1] +", " + b[2]);
////                           System.out.println("doc name: " + b[0] + "speciality: " + b[2]);
////                       }
////                       i++;
////                   }
////               }
////           }
////
////           @Override
////           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////           }
////
////           @Override
////           public void onChildRemoved(@NonNull DataSnapshot snapshot) {
////
////           }
////
////           @Override
////           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////           }
////
////           @Override
////           public void onCancelled(@NonNull DatabaseError error) {
////
////           }
////       });
////
////
////       System.out.println("sizeeee: " + DocDisplayList.size() + "\n" );
////       for (String[] b: DocDisplayList){
////           System.out.println("yoyoyo\n");
////
////           System.out.println("HIIIII doc name: " + b[0] + "speciality: " + b[2] + "\n");
////
////       }
//////       System.out.println("j is nower: " + j);
//////       int i = 0;
//////       for (String[] b: DocDisplayList){
//////           if (i < 7){
//////               docTextViews[i].setText(b[0] + ", " + b[1] +", " + b[2]);
//////           }
//////       }
//////       TextView t = (TextView) findViewById(R.id.Doc1Availabilities);
//////       t.setText("ALINAAAAAA");
//////       dothis();
////
////
////   }
//
//}