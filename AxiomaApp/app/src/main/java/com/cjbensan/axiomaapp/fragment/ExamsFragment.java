package com.cjbensan.axiomaapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjbensan.axiomaapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamsFragment extends Fragment {


    public ExamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exams, container, false);
    }

}