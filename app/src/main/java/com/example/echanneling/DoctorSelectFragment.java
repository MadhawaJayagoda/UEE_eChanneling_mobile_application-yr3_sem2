package com.example.echanneling;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class DoctorSelectFragment extends Fragment {

    Context currContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_doctor, container, false);
        ListView doctorList = v.findViewById(R.id.doctor_listview);

        ImageButton backNav = v.findViewById(R.id.btn_back);
        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSpecializationFragment()).commit();
            }
        });

        currContext = container.getContext();
        String[] doctorNames = getResources().getStringArray(R.array.doctor_names);
        String[] doctorTitles = getResources().getStringArray(R.array.doctor_titles);
        String[] doctorAvatar = getResources().getStringArray(R.array.doctor_avatar_url);

        //doctorList.setAdapter(new ArrayAdapter<String>( currContext, R.layout.doctor_list_custom_view, doctor_names));

        DoctorListAdapter doctorAdapter = new DoctorListAdapter(currContext, doctorNames, doctorTitles, doctorAvatar);
        doctorList.setAdapter(doctorAdapter);

        doctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putInt("INDEX_POSITION",position);

                DoctorTimeslotFragment docTimeslotFrag = new DoctorTimeslotFragment();
                docTimeslotFrag.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, docTimeslotFrag).commit();

            }
        });

        return v;
    }
}
