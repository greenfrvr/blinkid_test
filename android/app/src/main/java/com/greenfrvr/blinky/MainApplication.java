package com.greenfrvr.blinky;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.greenfrvr.blinky.modules.BlinkIdPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.microblink.MicroblinkSDK;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private static final String LICENSE_KEY = "sRwAAAAUY29tLmdyZWVuZnJ2ci5ibGlua3nyxpNcNrdMjhIbNYaZCHFShj7GdfPUbOUYjxLIIigoFypnK14xdE0mVAHzC42SltPtJViouEu1Q7uJKjiQLKzem03SPnRVRSM9fMN/7uvwEJ8oXWu5flHAiPthky2/LyAqBYKvNhe/7kG2EviBZX4v3tE1utHs16Ve9twoWp3AZe2tcNqmqIaxMpJaumnL9tBEnraqRiYu7qRQ9jzA/elVAzqvSEDXJndXV8uOF1IeULUtgxp+LVlCKZamAHE=\n";

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.asList(
                    new MainReactPackage(),
                    new RNGestureHandlerPackage(),
                    new BlinkIdPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        MicroblinkSDK.setLicenseKey(LICENSE_KEY, this);
    }
}
