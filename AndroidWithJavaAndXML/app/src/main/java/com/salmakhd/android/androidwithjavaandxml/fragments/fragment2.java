package com.salmakhd.android.androidwithjavaandxml.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.salmakhd.android.androidwithjavaandxml.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment2 extends ListFragment {
    String[] AndroidApps = new String[] {
            "Master",
            "Nirvana",
            "Arsa"
    };

    String[] Descriptions = new String[] {
            "Sample",
            "Fitness app",
            "booking app"
    };

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment2 newInstance(String param1, String param2) {
        fragment2 fragment = new fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
            // gets data from a data source and passes to complex views
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    AndroidApps
            );
            // set the adapter of this ListFragment
            setListAdapter(adapter);
            // Inflate the layout for this fragment
            return view;
        } catch (Exception e) {
            Log.i("ff", e.toString());
            return null;
        }
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
//        fragment1 txt = (fragment1)
//                getFragmentManager().findFragmentById(R.id.fragment2);
//        txt.change(AndroidApps[position], "Version: " + Descriptions[position]);
//        getListView().setSelector(android.R.color.holo_blue_bright);

    }
}