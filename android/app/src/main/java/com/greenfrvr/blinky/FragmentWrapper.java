package com.greenfrvr.blinky;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentWrapper extends Fragment {

    public static final String TAG = FragmentWrapper.class.getSimpleName();

    public static FragmentWrapper newInstance() {
        Bundle args = new Bundle();
        FragmentWrapper fragment = new FragmentWrapper();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wrapper, container, false);
    }

    //
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = getChildFragmentManager();
        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = new MyScanFragment();
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else {
            Log.i(TAG, "onResume: FRAGMENT HAS BEEN ADDED");
        }
    }
}
