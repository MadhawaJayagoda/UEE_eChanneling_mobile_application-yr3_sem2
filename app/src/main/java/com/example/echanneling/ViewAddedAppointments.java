package com.example.echanneling;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewAddedAppointments extends Fragment {

    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_added_appointments, container, false);

        context = container.getContext();
        ListView appointment_listView = (ListView) v.findViewById(R.id.list_apppointments);
        ImageButton back_btn = (ImageButton) v.findViewById(R.id.btn_back);

        ArrayList<Appointment> appointments_arraList = (ArrayList<Appointment>) Appointment.getInstance().getAppointment_arr();
        ArrayList<String> appointment_dates_arr = new ArrayList<String>();
        ArrayList<String> appointment_times_arr = new ArrayList<String>();
        ArrayList<String> doctor_names_arr = new ArrayList<String>();
        ArrayList<String> channeling_ceneters_arr = new ArrayList<String>();

        if( appointments_arraList.isEmpty() ) {
            appointments_arraList.add(new Appointment("Dr. Saman Gunawardena", "Cardiologist", "2020-12-02",
                    "3.30 P.M.", "MediCare Channeling Center - Piliyandala", "Kumara Sarathchandra", "0773412654", "34", "LKR 3500.00"));

            appointments_arraList.add(new Appointment("Dr. Sujatha Aththanayake", "Neurologist", "2020-12-05",
                "11.30 P.M.", "Asiri Medical Center - Colombo 07", "Kumara Sarathchandra", "0727422654", "58", "LKR 3500.00" ));

        }

        Log.i("View_added", "Appointments");
        for(Appointment appointment : appointments_arraList){
            Log.i("Arralist_appointments", appointment.getDoctorName().toString());
        }

        if (appointments_arraList != null && !appointments_arraList.isEmpty()) {
            for (int i = 0; i < appointments_arraList.size(); i++) {

                // Add data to the String arrays declared above
                appointment_dates_arr.add(appointments_arraList.get(i).getAppointmnetDate());
                appointment_times_arr.add(appointments_arraList.get(i).getAppointmentTime());
                doctor_names_arr.add(appointments_arraList.get(i).getDoctorName());
                channeling_ceneters_arr.add(appointments_arraList.get(i).getHospital());
            }

            AppointmentListAdapter appointmentListAdapter = new AppointmentListAdapter(context, appointment_dates_arr, appointment_times_arr, doctor_names_arr, channeling_ceneters_arr);
            appointment_listView.setAdapter(appointmentListAdapter);
        }else {
            Log.i("Appointment_arrayLists", "Appointment_arralist is empty");
        }


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });

        return v;
    }
}
