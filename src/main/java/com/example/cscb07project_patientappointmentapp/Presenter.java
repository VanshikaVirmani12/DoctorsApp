package com.example.cscb07project_patientappointmentapp;

import android.app.Activity;

import com.example.cscb07project_patientappointmentapp.Model;
import com.example.cscb07project_patientappointmentapp.quickstart.Contract;

public class Presenter implements Contract.Presenter, Contract.onLoginListener {

    private Contract.View mLoginView;
    private Model mLoginInteractor;

    public Presenter(Contract.View mLoginView) {
        this.mLoginView = mLoginView;
        mLoginInteractor = new Model(this);
    }

    @Override
    public void login(Activity activity, String email, String password) {

        mLoginInteractor.performFirebaseLogin(activity, email, password);
        //mLoginInteractor.getcurrentUID();
        //mLoginInteractor.getCurrentUser()

    }

    @Override
    public void onSuccessDoctor(String message) {
        mLoginView.onLoginSuccessDoctor(message);

    }

    @Override
    public void onSuccessPatient(String message) {
        mLoginView.onLoginSuccessPatient(message);

    }

    @Override
    public void onFailure(String message) {
        mLoginView.onLoginFailure(message);

    }

}