package com.example.echanneling;

import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private static Appointment appointment_instance = null;

    private String doctorName, doctorSpecilization, appointmnetDate, appointmentTime, hospital, patientName,
            patientPhoneNumber, patientAge;
    private String totalCharge;
    private List<Appointment> appointment_arr = new ArrayList<Appointment>();

    private Appointment(){
    }

    public Appointment(String doctorName, String doctorSpecilization, String appointmnetDate, String appointmentTime, String hospital, String patientName, String patientPhoneNumber, String patientAge, String totalCharge) {
        this.doctorName = doctorName;
        this.doctorSpecilization = doctorSpecilization;
        this.appointmnetDate = appointmnetDate;
        this.appointmentTime = appointmentTime;
        this.hospital = hospital;
        this.patientName = patientName;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientAge = patientAge;
        this.totalCharge = totalCharge;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecilization() {
        return doctorSpecilization;
    }

    public void setDoctorSpecilization(String doctorSpecilization) {
        this.doctorSpecilization = doctorSpecilization;
    }

    public String getAppointmnetDate() {
        return appointmnetDate;
    }

    public void setAppointmnetDate(String appointmnetDate) {
        this.appointmnetDate = appointmnetDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(String totalCharge) {
        this.totalCharge = totalCharge;
    }

    public List<Appointment> getAppointment_arr() {
        return appointment_arr;
    }

    public static Appointment getInstance(){
        if(appointment_instance == null)
            appointment_instance = new Appointment();

        return appointment_instance;
    }
}
