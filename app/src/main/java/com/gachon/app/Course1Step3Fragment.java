package com.gachon.app;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class Course1Step3Fragment extends Fragment {


    View root;
    Spinner[] spinners;


    public Course1Step3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course1_step3, container, false);


        return root;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinners = new Spinner[3];

        spinners[0] = (Spinner) root.findViewById(R.id.spinner1);
        spinners[1] = (Spinner) root.findViewById(R.id.spinner2);
        spinners[2] = (Spinner) root.findViewById(R.id.spinner3);

        String[] items = {"int", "float", "char"};

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.data_types, android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        int spinner_size = spinners.length;
        for (int i = 0 ; i < spinner_size; i++)
            spinners[i].setAdapter(adapter);
    }
}
