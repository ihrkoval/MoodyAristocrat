package com.example.deam.aristocrat.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deam.aristocrat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareLookFragment extends Fragment {


    public ShareLookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_look, container, false);
    }

}
