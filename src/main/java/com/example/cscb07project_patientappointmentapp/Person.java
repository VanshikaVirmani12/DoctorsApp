package com.example.cscb07project_patientappointmentapp;

import com.example.cscb07project_patientappointmentapp.UID;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

public abstract class Person implements Serializable {
    protected enum Gender{
        MALE,
        FEMALE
    }

    String fullName;
    String username;
    String password;
    Gender gender;
    String my_uid;
    List<Appointment> upcomingAppts;
    List<String> visited;

    public Person(){
//        if (upcomingAppts == null){
//            upcomingAppts = new ArrayList<Appointment>();
//        }
    }

    public Person(String fullName, String username, String password, String gender, String myuid){
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.my_uid = myuid;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.upcomingAppts = new ArrayList<Appointment>();
        this.visited = new ArrayList<String>();
    }


    public void addAppointmentToAppointments (Appointment appointment) {
        if (upcomingAppts == null){
            upcomingAppts = new ArrayList<Appointment>();
        }
        upcomingAppts.add(appointment);
    }

    public void addToVisited(String uid){
        if (visited == null){
            visited = new ArrayList<String>();
        }
        if (!visited.contains(uid)){
            visited.add(uid);
        }
    }


//    public void

    // function for get next five appts
//
//    public LinkedHashSet<Appointment> getUpcomingAppointments() { return upcomingAppointments; }
//
//    public void setUpcomingAppointments(LinkedHashSet<Appointment> upcomingAppointments) { this.upcomingAppointments = upcomingAppointments; }


//    public ArrayList<Appointment> getUpcomingAppointments() {
//        return upcomingAppointments;
//    }
//
//    public void setUpcomingAppointments(ArrayList<Appointment> upcomingAppointments) {
//        this.upcomingAppointments = upcomingAppointments;
//    }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Gender getGender() { return gender; }

    public List<Appointment> getUpcomingAppts() { return upcomingAppts; }

    public void setUpcomingAppts(List<Appointment> upcomingAppts) { this.upcomingAppts = upcomingAppts; }

    public void setGender(Gender gender) { this.gender = gender; }

    public List<String> getVisited() { return visited; }

    public void setVisited(List<String> visited) { this.visited = visited; }
//    public void addAppointmentToAppointments (Appointment appointment, Person p) {
//        int n = p.upcomingAppointments.size();
//
//        // Base Case
//        if (p.upcomingAppointments == null || p.upcomingAppointments.size() == 0 || (appointment.startTime.compareTo(p.upcomingAppointments.get(n-1).startTime) < 0)){
//            p.upcomingAppointments.add(appointment);
//        }
//
//        // Induction Step
//        else {
//            for (int i = 0; i < p.upcomingAppointments.size(); i++) {
//                if (p.upcomingAppointments.get(i).startTime.compareTo(appointment.startTime) > 0){
//                    p.upcomingAppointments.add(i, appointment);
//                    break;
//                }
//            }
//        }
//    }

    public String getMy_uid() { return my_uid; }

    public void setMy_uid(String my_uid) { this.my_uid = my_uid; }
}
