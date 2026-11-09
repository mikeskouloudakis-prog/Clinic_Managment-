package com.example.clinicmanagment;

public class Appointment {

    private Patient patient;
    private Doctor doctor;
    private String time;
    private String date;

    public Appointment(Patient patient,
                       Doctor doctor,
                       String date,
                       String time) {

        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

}
