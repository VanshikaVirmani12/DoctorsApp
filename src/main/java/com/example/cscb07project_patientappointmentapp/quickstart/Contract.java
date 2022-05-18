package com.example.cscb07project_patientappointmentapp.quickstart;

import android.app.Activity;

public interface Contract {

    interface View{
        void onLoginSuccessDoctor(String message);
        void onLoginSuccessPatient(String message);
        void onLoginFailure(String message);
    }

    interface Presenter{
        void login(Activity activity, String email, String password);
    }

    interface Model{
        void performFirebaseLogin(Activity activity, String email, String password);
    }

    interface onLoginListener{
        void onSuccessDoctor(String message);
        void onSuccessPatient(String message);
        void onFailure(String message);
    }

}
