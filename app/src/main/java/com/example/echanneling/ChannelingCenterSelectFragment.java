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
import android.widget.ImageButton;
import android.widget.Spinner;

public class ChannelingCenterSelectFragment extends Fragment {
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_select_channeling_center, container, false);

        context = container.getContext();
        ImageButton backNav = view.findViewById(R.id.btn_back);
        Spinner pickChannelingType = view.findViewById(R.id.spinner_channeling_type);
        final Spinner pickChannelingCenter = view.findViewById(R.id.spinner_channeling_center);
        Button btn_next = view.findViewById(R.id.btn_next_pick_channeling_center);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickChannelingCenter.getSelectedItemPosition() == 0) {
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
                } else {
                    Appointment appointment = Appointment.getInstance();
                    appointment.setHospital(pickChannelingCenter.getSelectedItem().toString());
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorSpecializationFragment()).commit();
                }
            }
        });

        ArrayAdapter<String> channelTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.channelingType));
        channelTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pickChannelingType.setAdapter(channelTypeAdapter);

        ArrayAdapter<String> channelCenterAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.channelingCenter));
        channelCenterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pickChannelingCenter.setAdapter(channelCenterAdapter);

        // Set the default values
        pickChannelingType.setSelection(0);
        pickChannelingCenter.setSelection(0);

        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });

        return view;
    }
}
