package com.example.echanneling;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AppointmentDetailsFragment extends Fragment {
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointment_details, container, false);

        context = container.getContext();
        ImageButton backNav = (ImageButton) v.findViewById(R.id.btn_back);
        TextView doctorName = (TextView) v.findViewById(R.id.doctor_name);
        TextView doctorSpecialization = (TextView) v.findViewById(R.id.doctor_specialization);
        TextView appointmentTime = (TextView) v.findViewById(R.id.appointment_time);
        TextView appointmentDate = (TextView) v.findViewById(R.id.appointment_date);
        TextView hospital = (TextView) v.findViewById(R.id.hospital);
        TextView patientName = (TextView) v.findViewById(R.id.patient_name);
        TextView patientContactNumber = (TextView) v.findViewById(R.id.patient_contact_number);
        TextView totalFee = (TextView) v.findViewById(R.id.total_charge);
        Button continueBtn = (Button) v.findViewById(R.id.btn_continue);
        Button cancelBtn= (Button) v.findViewById(R.id.btn_cancel);


//        Log.i("doctorName", Appointment.getInstance().getDoctorName());
//        Log.i("doctorSpecialization", Appointment.getInstance().getDoctorSpecilization());
//        Log.i("doctorName", Appointment.getInstance().getDoctorName());
//        Log.i("appointmnetDate", Appointment.getInstance().getAppointmnetDate());
//        Log.i("appointmentTime", Appointment.getInstance().getAppointmentTime());
//        Log.i("hospital", Appointment.getInstance().getHospital());
//        Log.i("patientName", Appointment.getInstance().getPatientName());
//        Log.i("patientPhoneNumber", Appointment.getInstance().getPatientPhoneNumber());

        doctorName.setText(Appointment.getInstance().getDoctorName());
        doctorSpecialization.setText(Appointment.getInstance().getDoctorSpecilization());
        appointmentTime.setText(Appointment.getInstance().getAppointmentTime());
        appointmentDate.setText(Appointment.getInstance().getAppointmnetDate());
        hospital.setText(Appointment.getInstance().getHospital());
        patientName.setText(Appointment.getInstance().getPatientName());
        patientContactNumber.setText(Appointment.getInstance().getPatientPhoneNumber());

        final Appointment appointment = Appointment.getInstance();

        if(Appointment.getInstance().getDoctorSpecilization().equalsIgnoreCase("General")) {
            totalFee.setText("LKR 1750.00");
            appointment.setTotalCharge("LKR 1750.00");
        }
        else {
            totalFee.setText("LKR 3250.00");
            appointment.setTotalCharge("LKR 3250.00");
        }


        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("patinet_name", appointment.getPatientName());
                bundle.putString("patient_phone_number", appointment.getPatientPhoneNumber());
                bundle.putString("patinet_age", appointment.getPatientAge());

                PatientDetailsFragment patinetFrag = new PatientDetailsFragment();
                patinetFrag.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, patinetFrag).commit();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.layout_dialog_cancel_appointment);

                Button dialog_no_btn = dialog.findViewById(R.id.btn_okay);
                Button dialog_yes_btn = dialog.findViewById(R.id.btn_yes);
                dialog_yes_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        dialog.dismiss();
                    }
                });
                dialog_no_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("appointment_total_charge", appointment.getTotalCharge());

                PaymentProcessFragment paymentProcessFrag = new PaymentProcessFragment();
                paymentProcessFrag.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, paymentProcessFrag).commit();
            }
        });

        return v;
    }
}
