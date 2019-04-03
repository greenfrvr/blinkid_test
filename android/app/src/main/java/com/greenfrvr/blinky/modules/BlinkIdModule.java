package com.greenfrvr.blinky.modules;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.greenfrvr.blinky.FragmentWrapper;

public class BlinkIdModule extends ReactContextBaseJavaModule {

    public static final String FRAGMENT_TAG = "ScanFragment";

    public BlinkIdModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNBlinkId";
    }

    @ReactMethod
    public void startScan() {
        AppCompatActivity currentActivity = (AppCompatActivity) getCurrentActivity();

        FragmentManager fm = currentActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(android.R.id.content, FragmentWrapper.newInstance(), "ScanFragment");
        ft.addToBackStack(null);
        ft.commit();
    }
}