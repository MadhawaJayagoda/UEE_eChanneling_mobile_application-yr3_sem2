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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DoctorTimeslotFragment extends Fragment {

    Context currContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_specific_doctor, container, false);

        currContext = container.getContext();
        Bundle bundle = this.getArguments();
        int index_pos = bundle.getInt("INDEX_POSITION");

        ListView timeslot_list = (ListView) v.findViewById(R.id.listview_timeslots);
        ImageView doctorPic = (ImageView) v.findViewById(R.id.doctor_imageview);
        ImageButton backNav = (ImageButton) v.findViewById(R.id.btn_back);
        TextView doctorName = (TextView) v.findViewById(R.id.doctor_name_textview);
        TextView doctorTitle = (TextView) v.findViewById(R.id.doctor_title_textView);
        TextView doctorHospital = (TextView) v.findViewById(R.id.doctor_registered_hospital_textview);

        String[] doctorNames = getResources().getStringArray(R.array.doctor_names);
        String[] doctorTitles = getResources().getStringArray(R.array.doctor_titles);
        String[] doctorHospitals = getResources().getStringArray(R.array.doctor_hospital);
        String[] doctorAvatars = getResources().getStringArray(R.array.doctor_avatar_url);

        String[] channelDates = getResources().getStringArray(R.array.channel_date);
        String[] channelTimes = getResources().getStringArray(R.array.channel_time);
        String[] activePatients = getResources().getStringArray(R.array.patients_number);

        final Appointment appointment = Appointment.getInstance();

        switch( index_pos) {
            case 0 :
                int imgID_0 = v.getResources().getIdentifier(doctorAvatars[0], "drawable", container.getContext().getPackageName());

                doctorName.setText(doctorNames[0]);
                doctorTitle.setText(doctorTitles[0]);
                doctorHospital.setText(doctorHospitals[0]);
                doctorPic.setImageResource(imgID_0);
                appointment.setDoctorName(doctorNames[0]);
                break;

            case 1 :
                int imgID_1 = v.getResources().getIdentifier(doctorAvatars[1], "drawable", container.getContext().getPackageName());

                doctorName.setText(doctorNames[1]);
                doctorTitle.setText(doctorTitles[1]);
                doctorHospital.setText(doctorHospitals[1]);
                doctorPic.setImageResource(imgID_1);
                appointment.setDoctorName(doctorNames[1]);
                break;

            case 2 :
                int imgID_2 = v.getResources().getIdentifier(doctorAvatars[2], "drawable", container.getContext().getPackageName());

                doctorName.setText(doctorNames[2]);
                doctorTitle.setText(doctorTitles[2]);
                doctorHospital.setText(doctorHospitals[2]);
                doctorPic.setImageResource(imgID_2);
                appointment.setDoctorName(doctorNames[2]);
                break;
        }

        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSelectFragment()).commit();
            }
        });

        TimeSlotListAdapter timeSlotAdapter = new TimeSlotListAdapter(currContext, channelDates, channelTimes, activePatients);
        timeslot_list.setAdapter(timeSlotAdapter);

        timeslot_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putInt("INDEX_POSITION", position);

                PatientDetailsFragment patientFrag = new PatientDetailsFragment();
                patientFrag.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, patientFrag).commit();
            }
        });

        Log.i("Doctor Specilization", appointment.getDoctorSpecilization());

        return v;
    }
}
