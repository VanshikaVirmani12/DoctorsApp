package com.example.cscb07project_patientappointmentapp;


import androidx.annotation.Keep;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

public class Appointment implements Serializable{

    //    Doctor doctor;
//    Patient patient;
    String doc_uid;
    String pat_uid;
    Date dateAndTime;
    String docFullName;
//    String description;

    // the only thing changed to start implementing view appointments is the consdtructor
    // of appointment now includes doc name

    public Appointment(){}

//    public Appointment(Doctor doctor, String description, Date date){
//        this.doctor = doctor;
////        this.patient = patient;
//        this.dateAndTime = date;
//        this.description = description;
//    }

    public Appointment(String doctor, String pat,  Date date, String docname){
        this.doc_uid = doctor;
        this.pat_uid = pat;
        this.dateAndTime = date;
        this.docFullName = docname;
//        this.description = description;
    }

//    public String getDate(){
//       String hold = this.startTime.toDate().toString();
//       List<String> splits = Arrays.asList(hold.split("\\s+"));
//       return(splits.get(0) + ", " + splits.get(1) + " " + splits.get(2) + ", " +splits.get(5));
//    }
//
//    public String getTime(){
//        String hold = this.startTime.toDate().toString();
//        List<String> splits = Arrays.asList(hold.split("\\s+"));
//        return(splits.get(3));
//    }

//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }
//
//    public Patient getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }
//
//    public Calendar getCalendar() {
//        return calendar;
//    }
//
//    public void setCalendar(Calendar calendar) {
//        this.calendar = calendar;
//    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getDoc_uid() {
        return doc_uid;
    }

    public void setDoc_uid(String doc_uid) {
        this.doc_uid = doc_uid;
    }

    public String getPat_uid() {
        return pat_uid;
    }

    public void setPat_uid(String pat_uid) {
        this.pat_uid = pat_uid;
    }

    public String getDocFullName() {
        return docFullName;
    }

    public void setDocFullName(String docFullName) {
        this.docFullName = docFullName;
    }

}

