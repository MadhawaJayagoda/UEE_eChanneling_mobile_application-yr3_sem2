package com.example.echanneling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AppointmentListAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    Context context;
    ArrayList<String> appointment_dates_arr;
    ArrayList<String> appointment_times_arr;
    ArrayList<String> doctor_names_arr;
    ArrayList<String> channeling_ceneters_arr;

    public AppointmentListAdapter(Context context, ArrayList<String> appointment_dates, ArrayList<String> appointment_times, ArrayList<String> doctor_names, ArrayList<String> channeling_ceneters) {
        this.context = context;
        this.appointment_dates_arr = appointment_dates;
        this.appointment_times_arr = appointment_times;
        this.doctor_names_arr = doctor_names;
        this.channeling_ceneters_arr = channeling_ceneters;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return appointment_dates_arr.size();
    }

    @Override
    public Object getItem(int position) {
        return doctor_names_arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.appointment_list_custom_view, null);

        TextView appointmentDate = v.findViewById(R.id.textView_appointment_date);
        TextView appointmentTime = v.findViewById(R.id.textView_appointment_time);
        TextView appointmentDoctorName = v.findViewById(R.id.textView_doctor_name);
        TextView appointmentChannelingCenter = v.findViewById(R.id.textView_channelling_center);

        String curAppointmentDate = appointment_dates_arr.get(position);
        String curAppointmentTime = appointment_times_arr.get(position);
        String curAppointmentDoctorName = doctor_names_arr.get(position);
        String curAppointmentChannellingCenter = channeling_ceneters_arr.get(position);

        appointmentDate.setText(curAppointmentDate);
        appointmentTime.setText(curAppointmentTime);
        appointmentDoctorName.setText(curAppointmentDoctorName);
        appointmentChannelingCenter.setText(curAppointmentChannellingCenter);

        return v;
    }
}
