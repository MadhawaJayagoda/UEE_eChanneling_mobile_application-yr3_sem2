package com.example.echanneling;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class DoctorListAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] doctorname;
    String[] doctorTitles;
    String[] doctorAvatarURL;
    Resources res;
    Context context;

    public DoctorListAdapter(Context cont, String[] doctorname, String[] doctorTitles, String[] doctorAvatar) {
        this.doctorname = doctorname;
        this.doctorTitles = doctorTitles;
        this.doctorAvatarURL = doctorAvatar;
        this.context = cont;
        mInflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return doctorname.length;
    }

    @Override
    public Object getItem(int position) {
        return doctorname[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.doctor_list_custom_view, null);

        TextView doctorNameTextView = (TextView) v.findViewById(R.id.doctor_name_doctor_list_custom);
        TextView doctorTitlesTextView = (TextView) v.findViewById(R.id.doctor_titile_doctor_list_custom);
        ImageView imageDoctor = (ImageView) v.findViewById(R.id.image_view_doctor_list_custom);

        String curDoctorName = doctorname[i];
        String curDoctorTitle = doctorTitles[i];
        String curDoctorAvatar = doctorAvatarURL[i];

        int imgID = v.getResources().getIdentifier(curDoctorAvatar, "drawable", context.getPackageName());

        doctorNameTextView.setText(curDoctorName);
        doctorTitlesTextView.setText(curDoctorTitle);
        imageDoctor.setImageResource(imgID);

        return v;
    }
}
