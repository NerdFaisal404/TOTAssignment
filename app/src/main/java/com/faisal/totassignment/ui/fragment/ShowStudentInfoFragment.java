package com.faisal.totassignment.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faisal.totassignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowStudentInfoFragment extends Fragment {


    public ShowStudentInfoFragment() {
        // Required empty public constructor
    }

    public static ShowStudentInfoFragment newInstance() {
        ShowStudentInfoFragment fragment = new ShowStudentInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_student_info, container, false);
    }

}
