package com.example.clinicmanagment;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "patients.txt";

    // SAVE PATIENT

    public static void savePatient(Patient patient) {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME, true);

            writer.write(
                    patient.getName() + "," +
                            patient.getAge() + "," +
                            patient.getPhone() + "\n"
            );

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // LOAD PATIENTS

    public static ArrayList<Patient> loadPatients() {

        ArrayList<Patient> patients =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME));

            String line;

            while((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String phone = data[2];

                Patient patient =
                        new Patient(name, age, phone);

                patients.add(patient);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("No file found yet.");
        }

        return patients;
    }

// SAVE DOCTOR

    public static void saveDoctor(Doctor doctor) {

        try {

            FileWriter writer =
                    new FileWriter("doctors.txt", true);

            writer.write(
                    doctor.getName() + "," +
                            doctor.getSpecialty() + "\n"
            );

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

// LOAD DOCTORS

    public static ArrayList<Doctor> loadDoctors() {

        ArrayList<Doctor> doctors =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("doctors.txt"));

            String line;

            while((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Doctor doctor =
                        new Doctor(data[0], data[1]);

                doctors.add(doctor);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("No doctors file found.");
        }

        return doctors;
    }


// SAVE APPOINTMENT

    public static void saveAppointment(Appointment appointment) {

        try {

            FileWriter writer =
                    new FileWriter("appointments.txt", true);

            writer.write(
                    appointment.getPatient().getName() + "," +
                            appointment.getDoctor().getName() + "," +
                            appointment.getDoctor().getSpecialty() + "," +
                            appointment.getDate() + "," +
                            appointment.getTime() + "\n"
            );

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

// LOAD APPOINTMENTS

    public static ArrayList<Appointment> loadAppointments() {

        ArrayList<Appointment> appointments =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("appointments.txt"));

            String line;

            while((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Patient patient =
                        new Patient(data[0], 0, "");

                Doctor doctor =
                        new Doctor(data[1], data[2]);

                String date = data[3];

                String time = data[3];

                Appointment appointment =
                        new Appointment(patient, doctor, date, time);

                appointments.add(appointment);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("No appointments file found.");
        }

        return appointments;
    }

}
