package com.example.echanneling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeSlotListAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] channel_date;
    String[] channel_time;
    String[] activePatients;
    Context context;
    public TimeSlotListAdapter(Context cont, String[] channel_date, String[] channel_time, String[] activePatients) {

        this.channel_date = channel_date;
        this.channel_time = channel_time;
        this.activePatients = activePatients;
        this.context = cont;

        this.mInflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return channel_time.length;
    }

    @Override
    public Object getItem(int position) {
        return channel_date[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.timeslot_custom_layout, null);

        TextView channelDate = (TextView) v.findViewById(R.id.date_textview);
        TextView channelTime = (TextView) v.findViewById(R.id.time_textview);
        TextView activePatient = (TextView) v.findViewById(R.id.active_patient_textView);

        String curDate = channel_date[i];
        String curTime = channel_time[i];
        String curActPatient = activePatients[i];

        channelDate.setText(curDate);
        channelTime.setText(curTime);
        activePatient.setText(curActPatient);

        return v;
    }
}
