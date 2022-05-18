
package com.example.cscb07project_patientappointmentapp;

import java.util.Comparator;

public class DateComparator implements Comparator<Appointment> {

    public int compare(Appointment a1, Appointment a2) {
        return a1.getDateAndTime().compareTo(a2.getDateAndTime());
    }

}