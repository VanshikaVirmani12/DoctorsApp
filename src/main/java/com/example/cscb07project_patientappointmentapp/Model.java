package com.example.cscb07project_patientappointmentapp;

import android.app.Activity;
import androidx.annotation.NonNull;

import com.example.cscb07project_patientappointmentapp.quickstart.Contract;
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

public class Model implements Contract.Model {

    private Contract.onLoginListener mOnLoginListener;

    public Model(Contract.onLoginListener onLoginListener){
        this.mOnLoginListener = onLoginListener;
    }

    @Override
    public void performFirebaseLogin(Activity activity, String email, String password) {

        FirebaseUser f = FirebaseAuth.getInstance().getCurrentUser();
        if (f == null) {
            //System.out.println("IN LOGIN CURRENT USER IS NULL\n");
            mOnLoginListener.onFailure("IN LOGIN CURRENT USER IS NULL\n");
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task == null) {
                    mOnLoginListener.onFailure(task.getException().toString());
                }
                if (task.isSuccessful()) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference docRef = FirebaseDatabase.getInstance().getReference("Doctors/" + uid);
                    docRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                System.out.println("user is a doctor\n");
                                mOnLoginListener.onSuccessDoctor(task.getResult().toString());
                            } else {
                                System.out.println("user is a patient\n");
                                mOnLoginListener.onSuccessPatient(task.getResult().toString());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            System.out.println("user is not doc or patient\n");
                        }
                    });

                } else {
                    mOnLoginListener.onFailure(task.getException().toString());
                }
            }


        });
    }

//    public void getcurrentUID() {
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        if (uid == null) {
//            mOnLoginListener.onFailure("USER DOES NOT EXIST\n");
//        }
//    }
}

