package com.example.echanneling;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class DoctorSpecializationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_doctor_specilization, container, false);

        ImageButton backNav = v.findViewById(R.id.btn_back);
        Button btnGeneralDoctor = v.findViewById(R.id.btn_general_doctor);
        MaterialButton btnDermatologist = v.findViewById(R.id.btn_dermatologist);
        MaterialButton btnCardiologist = v.findViewById(R.id.btn_cardiologist_doctor);
        MaterialButton btnENT = v.findViewById(R.id.btn_ent_doctor);
        MaterialButton btnNeurologist = v.findViewById(R.id.btn_neurologist_doctor);
        MaterialButton btnPathalogist = v.findViewById(R.id.btn_pathalogy_doctor);
        MaterialButton btnDentist = v.findViewById(R.id.btn_dentist_doctor);

        final Appointment appointment = Appointment.getInstance();

        btnGeneralDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setDoctorSpecilization("General");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        btnDermatologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "You clicked Dermatologist", Toast.LENGTH_SHORT).show();
                appointment.setDoctorSpecilization("Dermatologist");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        btnCardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setDoctorSpecilization("Cardiologist");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        btnENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setDoctorSpecilization("ENT -(Otorhinolaryngologist)");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        btnNeurologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setDoctorSpecilization("Neurologist");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        btnPathalogist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setDoctorSpecilization("Pathologist");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        btnDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setDoctorSpecilization("Dentist");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChannelingCenterSelectFragment()).commit();
            }
        });

        Log.i("appointment_value", appointment.getHospital().toString());

        return v;
    }
}
