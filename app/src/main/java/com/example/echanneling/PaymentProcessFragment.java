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
import android.widget.EditText;
import android.widget.TextView;

public class PaymentProcessFragment extends Fragment {

    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment_process, container, false);

        context = container.getContext();
        Bundle bundle = this.getArguments();
        String tot_charge = bundle.getString("appointment_total_charge");

        TextView total_cost_txtView= (TextView) v.findViewById(R.id.textView_total_cost);
        Button cancel_pay_btn = (Button) v.findViewById(R.id.btn_pay_cancel);
        Button payment_btn = (Button) v.findViewById(R.id.btn_pay);
        final EditText inputCardNumber = (EditText) v.findViewById(R.id.input_card_number);
        final EditText inputCardPin = (EditText) v.findViewById(R.id.input_card_pin);


        total_cost_txtView.setText(tot_charge);

        final Appointment appointment = Appointment.getInstance();
        final Appointment obj_appointment = new Appointment(
                appointment.getDoctorName(),
                appointment.getDoctorSpecilization(),
                appointment.getAppointmnetDate(),
                appointment.getAppointmentTime(),
                appointment.getHospital(),
                appointment.getPatientName(),
                appointment.getPatientPhoneNumber(),
                appointment.getPatientAge(),
                appointment.getTotalCharge()
        );

        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCardNumber.getText().toString().isEmpty() || inputCardPin.getText().toString().isEmpty()){
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setContentView(R.layout.layout_dialog_warning);
                    Button dialog_okay_btn = dialog.findViewById(R.id.btn_okay);
                    dialog_okay_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (inputCardNumber.getText().toString().length() < 16 || inputCardNumber.getText().toString().length() > 16 ||
                        inputCardPin.getText().toString().length() < 3 || inputCardPin.getText().toString().length() > 3){
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setContentView(R.layout.layout_dialog_invalid_input);
                    Button dialog_okay_btn = dialog.findViewById(R.id.btn_okay);
                    dialog_okay_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    appointment.getAppointment_arr().add(obj_appointment);

                    Log.i("Doctor Name", obj_appointment.getDoctorName());
                    Log.i("Doctor Specialization", obj_appointment.getDoctorSpecilization());
                    Log.i("Appointment Date", obj_appointment.getAppointmnetDate());
                    Log.i("Appointment Time", obj_appointment.getAppointmentTime());
                    Log.i("Hospital", obj_appointment.getHospital());
                    Log.i("Tot_Charge", obj_appointment.getTotalCharge());

                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setContentView(R.layout.layout_dialog_successfully_added_appointment);

                    Button dialog_btn_okay = dialog.findViewById(R.id.btn_okay);
                    dialog_btn_okay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });

        cancel_pay_btn.setOnClickListener(new View.OnClickListener() {
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
        return v;
    }
}
