package com.example.clinicmanagment;

public class Patient {

    private String name;
    private int age;
    private String phone;

    public Patient(String name, int age, String phone) {

        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {

        return name;
    }
}
