package com.example.echanneling;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PatientDetailsFragment extends Fragment {

    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_details, container, false);

        context = container.getContext();
        Bundle bundle = this.getArguments();
        int indexSelected = bundle.getInt("INDEX_POSITION");

        //Resources res = v.getResources();

        String[] channelDates = getResources().getStringArray(R.array.channel_date);
        String[] channelTimes = getResources().getStringArray(R.array.channel_time);
        final Appointment appointment = Appointment.getInstance();
        appointment.setAppointmnetDate(channelDates[indexSelected]);
        appointment.setAppointmentTime(channelTimes[indexSelected]);

        final Spinner patientTitle = (Spinner) v.findViewById(R.id.spinner_title_patient);
        Button btn_continue = (Button) v.findViewById(R.id.btn_continue);
        Button btn_cancelHomeNav = (Button) v.findViewById(R.id.btn_cancel);
        final EditText inputPatientName = (EditText) v.findViewById(R.id.input_patient_name);
        final EditText inputPatientPhoneNumber = (EditText) v.findViewById(R.id.input_phone_number);
        final EditText inputPatientAge = (EditText) v.findViewById(R.id.input_patient_age);

        if (bundle.containsKey("patinet_name") && bundle.containsKey("patient_phone_number") && bundle.containsKey("patinet_age")){

            String patinetTitleName = bundle.getString("patinet_name");

            String title = patinetTitleName.substring(0, patinetTitleName.lastIndexOf("."));
            String name = patinetTitleName.substring(patinetTitleName.lastIndexOf(".") + 1);

            inputPatientName.setText(name);
            inputPatientPhoneNumber.setText(bundle.getString("patient_phone_number"));
            inputPatientAge.setText(bundle.getString("patinet_age"));
        }

        ArrayAdapter<String> titleSpinnerAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, v.getResources().getStringArray(R.array.patient_titles));
        titleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patientTitle.setAdapter(titleSpinnerAdapter);

        btn_cancelHomeNav.setOnClickListener(new View.OnClickListener() {
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

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputPatientName.getText().toString().isEmpty() || inputPatientPhoneNumber.getText().toString().isEmpty() || inputPatientAge.getText().toString().isEmpty()){
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
                } else if(inputPatientPhoneNumber.getText().toString().length() < 10 || inputPatientPhoneNumber.getText().toString().length() > 10 ||
                            Integer.parseInt(inputPatientAge.getText().toString()) <= 0 || Integer.parseInt(inputPatientAge.getText().toString()) > 150 ){
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
                    String title = patientTitle.getSelectedItem().toString();
                    appointment.setPatientName(title + " " + inputPatientName.getText().toString());
                    appointment.setPatientPhoneNumber(inputPatientPhoneNumber.getText().toString());
                    appointment.setPatientAge(inputPatientAge.getText().toString());

                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new AppointmentDetailsFragment()).commit();
                }
            }
        });

        //Get the View(s) in the fragment_patient_details

        return v;
    }
}
