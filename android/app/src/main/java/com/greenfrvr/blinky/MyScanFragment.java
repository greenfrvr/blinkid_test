package com.greenfrvr.blinky;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microblink.entities.recognizers.Recognizer;
import com.microblink.entities.recognizers.RecognizerBundle;
import com.microblink.entities.recognizers.blinkid.mrtd.MrtdCombinedRecognizer;
import com.microblink.recognition.RecognitionSuccessType;
import com.microblink.view.CameraAspectMode;
import com.microblink.view.CameraEventsListener;
import com.microblink.view.recognition.RecognizerRunnerView;
import com.microblink.view.recognition.ScanResultListener;

public class MyScanFragment extends Fragment implements CameraEventsListener {
    public static final String TAG = MyScanFragment.class.getSimpleName();

    private static final int PERMISSION_CAMERA_REQUEST_CODE = 69;
    private RecognizerRunnerView mRecognizerView;
    private MrtdCombinedRecognizer mRecognizer;
    private RecognizerBundle mRecognizerBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // create RecognizerView
        super.onCreate(savedInstanceState);
        mRecognizer = new MrtdCombinedRecognizer();
        mRecognizerBundle = new RecognizerBundle(mRecognizer);
        mRecognizerView = new RecognizerRunnerView(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecognizerView.setRecognizerBundle(mRecognizerBundle);
        // scan result listener will be notified when scan result gets available
        mRecognizerView.setScanResultListener(mScanResultListener);
        // camera events listener will be notified about camera lifecycle and errors
        mRecognizerView.setCameraEventsListener(this);

        // set camera aspect mode
        // ASPECT_FIT will fit the camera preview inside the view
        // ASPECT_FILL will zoom and crop the camera preview, but will use the
        // entire view surface
        mRecognizerView.setAspectMode(CameraAspectMode.ASPECT_FILL);
        mRecognizerView.create();
        return mRecognizerView;
    }


    @Override
    public void onStart() {
        super.onStart();
        // you need to pass all activity's lifecycle methods to RecognizerView
        mRecognizerView.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        // you need to pass all activity's lifecycle methods to RecognizerView
        mRecognizerView.resume();

        Log.i(TAG, "onResume: " + mRecognizerView.getCurrentOrientation());

    }

    @Override
    public void onPause() {
        super.onPause();
        // you need to pass all activity's lifecycle methods to RecognizerView
        mRecognizerView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        // you need to pass all activity's lifecycle methods to RecognizerView
        mRecognizerView.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // you need to pass all activity's lifecycle methods to RecognizerView
        mRecognizerView.destroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // you need to pass all activity's lifecycle methods to RecognizerView
        mRecognizerView.changeConfiguration(newConfig);
    }

//    @Override
//    public void onScanningDone(RecognitionResults results) {
//        // this method is from ScanResultListener and will be called when scanning completes
//        // RecognitionResults may contain multiple results in array returned
//        // by method getRecognitionResults().
//        // This depends on settings in RecognitionSettings object that was
//        // given to RecognizerView.
//        // For more information, see chapter "Recognition settings and results")
//
//        // After this method ends, scanning will be resumed and recognition
//        // state will be retained. If you want to prevent that, then
//        // you should call:
//        // mRecognizerView.resetRecognitionState();
//
//        // If you want to pause scanning to prevent receiving recognition
//        // results, you should call:
//        // mRecognizerView.pauseScanning();
//        // After scanning is paused, you will have to resume it with:
//        // mRecognizerView.resumeScanning(true);
//        // boolean in resumeScanning method indicates whether recognition
//        // state should be automatically reset when resuming scanning
//    }

    @Override
    public void onCameraPreviewStarted() {
        // this method is from CameraEventsListener and will be called when camera preview starts
    }

    @Override
    public void onCameraPreviewStopped() {
        // this method is from CameraEventsListener and will be called when camera preview stops
    }

    @Override
    public void onError(Throwable exc) {
        /**
         * This method is from CameraEventsListener and will be called when
         * opening of camera resulted in exception or recognition process
         * encountered an error. The error details will be given in exc
         * parameter.
         */
    }

    @Override
    @TargetApi(23)
    public void onCameraPermissionDenied() {
        /**
         * Called on Android 6.0 and newer if camera permission is not given
         * by user. You should request permission from user to access camera.
         */
        requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA_REQUEST_CODE);
        /**
         * Please note that user might have not given permission to use
         * camera. In that case, you have to explain to user that without
         * camera permissions scanning will not work.
         * For more information about requesting permissions at runtime, check
         * this article:
         * https://developer.android.com/training/permissions/requesting.html
         */
    }

    @Override
    public void onAutofocusFailed() {
        /**
         * This method is from CameraEventsListener will be called when camera focusing has failed.
         * Camera manager usually tries different focusing strategies and this method is called when all
         * those strategies fail to indicate that either object on which camera is being focused is too
         * close or ambient light conditions are poor.
         */
    }

    @Override
    public void onAutofocusStarted(Rect[] areas) {
        /**
         * This method is from CameraEventsListener and will be called when camera focusing has started.
         * You can utilize this method to draw focusing animation on UI.
         * Areas parameter is array of rectangles where focus is being measured.
         * It can be null on devices that do not support fine-grained camera control.
         */
    }

    @Override
    public void onAutofocusStopped(Rect[] areas) {
        /**
         * This method is from CameraEventsListener and will be called when camera focusing has stopped.
         * You can utilize this method to remove focusing animation on UI.
         * Areas parameter is array of rectangles where focus is being measured.
         * It can be null on devices that do not support fine-grained camera control.
         */
    }

    private final ScanResultListener mScanResultListener = new ScanResultListener() {
        @Override
        public void onScanningDone(@NonNull RecognitionSuccessType recognitionSuccessType) {
            // this method is from ScanResultListener and will be called when scanning completes
            // you can obtain scanning result by calling getResult on each
            // recognizer that you bundled into RecognizerBundle.
            // for example:

            MrtdCombinedRecognizer.Result result = mRecognizer.getResult();
            if (result.getResultState() == Recognizer.Result.State.Valid) {
                // result is valid, you can use it however you wish
            }

            // Note that mRecognizer is stateful object and that as soon as
            // scanning either resumes or its state is reset
            // the result object within mRecognizer will be changed. If you
            // need to create a immutable copy of the result, you can do that
            // by calling clone() on it, for example:

            MrtdCombinedRecognizer.Result immutableCopy = result.clone();

            // After this method ends, scanning will be resumed and recognition
            // state will be retained. If you want to prevent that, then
            // you should call:
            mRecognizerView.resetRecognitionState();
            // Note that reseting recognition state will clear internal result
            // objects of all recognizers that are bundled in RecognizerBundle
            // associated with RecognizerRunnerView.

            // If you want to pause scanning to prevent receiving recognition
            // results or mutating result, you should call:
            mRecognizerView.pauseScanning();
            // if scanning is paused at the end of this method, it is guaranteed
            // that result within mRecognizer will not be mutated, therefore you
            // can avoid creating a copy as described above

            // After scanning is paused, you will have to resume it with:
            mRecognizerView.resumeScanning(true);
            // boolean in resumeScanning method indicates whether recognition
            // state should be automatically reset when resuming scanning - this
            // includes clearing result of mRecognizer
        }
    };
}