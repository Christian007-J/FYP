package com.example.clotclassifier;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DataFragment extends Fragment {

    Spinner possible_cause, clot_type, gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_data, container, false);

        possible_cause = root.findViewById(R.id.possible_cause);
        String [] cause = getResources().getStringArray(R.array.causes);
        ArrayAdapter adapter = new ArrayAdapter(root.getContext(), android.R.layout.simple_spinner_item,cause);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        possible_cause.setAdapter(adapter);

        clot_type = root.findViewById(R.id.clot_type);
        String [] types = getResources().getStringArray(R.array.clot_types);
        ArrayAdapter adapter1 = new ArrayAdapter(root.getContext(), android.R.layout.simple_spinner_item,types);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clot_type.setAdapter(adapter1);

        gender = root.findViewById(R.id.gender);
        String [] genders = getResources().getStringArray(R.array.gender);
        ArrayAdapter adapter2 = new ArrayAdapter(root.getContext(), android.R.layout.simple_spinner_item,genders);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter2);

        return root;
    }
}