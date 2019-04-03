package com.greenfrvr.blinky.modules;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.greenfrvr.blinky.FragmentWrapper;

import javax.annotation.Nonnull;


public class ScanCardViewManager extends SimpleViewManager<View> {

    public static final String TAG = ScanCardViewManager.class.getSimpleName();

    @Nonnull
    @Override
    public String getName() {
        return "ScanCardView";
    }

    @Nonnull
    @Override
    protected View createViewInstance(@Nonnull ThemedReactContext reactContext) {
        AppCompatActivity activity = (AppCompatActivity) reactContext.getCurrentActivity();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = FragmentWrapper.newInstance();

        fragmentTransaction.add(android.R.id.content, fragment);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();

        View fv = fragment.getView();

        ViewGroup parent = ((ViewGroup) fv.getParent());
        parent.removeViewInLayout(fv);

        Log.i(TAG, "createViewInstance: ");
        return fv;

    }


}
