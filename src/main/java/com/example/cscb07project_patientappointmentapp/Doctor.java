
package com.example.cscb07project_patientappointmentapp;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;
import com.google.protobuf.ApiProto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Doctor extends Person implements Serializable {

    enum Specialty {
        IMMUNOLOGY,
        ANESTHESIOLOGY,
        DERMATOLOGY,
        DIAGNOSTIC_RADIOLOGY,
        EMERGENCY_MEDICINE,
        FAMILY_MEDICINE,
        INTERNAL_MEDICINE,
        MEDICAL_GENETICS,
        NEUROLOGY,
        NUCLEAR_MEDICINE,
        OBSTETRICS_GYNECOLOGY,
        OPHTHALMOLOGY,
        PATHOLOGY,
        PEDIATRICS,
        PHYSICAL_MEDICINE_REHABILITATION,
        PREVENTIVE_MEDICINE,
        PSYCHIATRY,
        RADIATION_ONCOLOGY,
        SURGERY,
        UROLOGY
    }
    Specialty specialty;
    List<Patient> patients;


    public Doctor(){ }

    public Doctor(String fullName, String username, String password, String gender, String specialty, String my_uid) {
        super(fullName, username, password, gender, my_uid);
        this.specialty = Specialty.valueOf(specialty.toUpperCase());
        this.patients = new ArrayList<Patient>();
    }

    public HashMap<String, Date> giveNextFiveAvailableAppointments(){
        HashMap <String, Date> datesAvail = new HashMap<String, Date>();

//        if (this.upcomingAppts == null || this.upcomingAppts.size() == 0) { // dont think i need size == 0
//            this.upcomingAppts = new ArrayList<Appointment>();
//
//            Calendar cal1 = createCal(0);
//            Calendar cal2 = createCal(1);
//            Calendar cal3 = createCal(2);
//            Calendar cal4 = createCal(3);
//            Calendar cal5 = createCal(4);
//
//
//            datesAvail.put(cal1.getTime().toString(), cal1.getTime());
//            datesAvail.put(cal2.getTime().toString(), cal2.getTime());
//            datesAvail.put(cal3.getTime().toString(), cal3.getTime());
//            datesAvail.put(cal4.getTime().toString(), cal4.getTime());
//            datesAvail.put(cal5.getTime().toString(), cal5.getTime());
//
////            ArrayList <Date> avail = new ArrayList<Date>();
////            avail.add(cal1.getTime());
////            avail.add(cal2.getTime());
////            avail.add(cal3.getTime());
////            avail.add(cal4.getTime());
////            avail.add(cal5.getTime());
////            ArrayList <Calendar> avail = new ArrayList<Calendar>();
////            avail.add(cal1);
////            avail.add(cal2);
////            avail.add(cal3);
////            avail.add(cal4);
////            avail.add(cal5);
//            return datesAvail;
//        }
//        else{
//            datesAvail = getNextFiveAvailableAppointmentsHelpers();
//        }

        if (this.upcomingAppts == null ) { // dont think i need size == 0
            this.upcomingAppts = new ArrayList<Appointment>();
        }
        datesAvail = giveNextFiveAvailableAppointmentsHelpers();

        Iterator hashIterator = datesAvail.entrySet().iterator();
        System.out.println("IN DOC avali: " + username);
        while (hashIterator.hasNext()) {
            Map.Entry entry = (Map.Entry)hashIterator.next();
            Date d1 = (Date)entry.getValue();
            System.out.println("IN DOC Availabilities: " + d1.toString());
        }

        for (Appointment b: upcomingAppts){
            System.out.println(username + "curr (in doc) appts: " + b.dateAndTime.toString());
        }

        return datesAvail;
    }

    public HashMap<String, Date> giveNextFiveAvailableAppointmentsHelpers(){

        boolean taken = false;
        int count = 0;
        HashMap <String, Date> availableTimes = new HashMap<String, Date>();

        for (int i = 0; i < 5 ; i++){

            for (int j = 9; j < 17 ; j++){

                Calendar cal = createAndSetCal(j, i);
                Date calDate = cal.getTime();

                if (calDate.before(new Date())){
                    System.out.println("location 1\n");
                    taken = true;
                }
                else{
                    for (Appointment a: upcomingAppts){
                        System.out.println("upcoming: " + a.dateAndTime.toString());
                        System.out.println("calDate: " + calDate.toString());
                        if (calDate.toString().equals(a.dateAndTime.toString())){
                            System.out.println("location 2\n");
                            taken = true;
                        }
                    }
                }

                if (!taken){
                    availableTimes.put(cal.getTime().toString(), cal.getTime());
//                    count ++;
                }
//                if (count >= 5){
//                    return availableTimes;
//                }
                taken = false;
            }
        }

        Iterator hashIterator = availableTimes.entrySet().iterator();
        System.out.println("IN HELPER avali: " + username);
        while (hashIterator.hasNext()) {
            Map.Entry entry = (Map.Entry)hashIterator.next();
            Date d1 = (Date)entry.getValue();
            System.out.println("IN HELPER Availabilities: " + d1.toString());
        }
        return availableTimes;

//        Appointment sorter = upcomingAppts.get(0);
//        for (Appointment a:  upcomingAppts){
//            if (a.dateAndTime.before(sorter.dateAndTime)){
//                sorter = a;
//            }
//        }
//        // now sorter is the smallest in arraylist
//        ArrayList
    }


    public Calendar createCal(int setForward){
        Date a = new Date();
        a.setMinutes(0);
        a.setSeconds(0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.HOUR_OF_DAY, setForward);
        return cal;
    }

    public Calendar createAndSetCal(int hour, int day){
        Date a = new Date();
        a.setMinutes(0);
        a.setSeconds(0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.add(Calendar.DATE, day);
        return cal;
    }



//    public ArrayList<Appointment> seeNextAppointments(){
//        ArrayList<Appointment> nextAppointments = new ArrayList<>();
//
//        for (int i = 0; i < 5 || i < upcomingAppointments.size(); i++){
//            nextAppointments.add(upcomingAppointments.get(i));
//        }
//
//        return nextAppointments;
//    }

    public Specialty getSpecialty() { return specialty; }

    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }

    public List<Patient> getPatients() { return patients; }

    public void setPatients(List<Patient> patients) { this.patients = patients; }

    //    @Override
//    protected Object clone(){}

}
