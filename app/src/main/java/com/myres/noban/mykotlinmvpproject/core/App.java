package com.myres.noban.mykotlinmvpproject.core;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDexApplication;

import com.myres.noban.mykotlinmvpproject.R;
import com.myres.noban.mykotlinmvpproject.di.AppComponent;
import com.myres.noban.mykotlinmvpproject.di.AppModule;
import com.myres.noban.mykotlinmvpproject.di.DaggerAppComponent;
import com.myres.noban.mykotlinmvpproject.utility.ConnectivityReceiver;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.PrettyFormatStrategy;
import org.jetbrains.annotations.Contract;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class App extends MultiDexApplication {

    public static App instance;

    public AppComponent appComponent;

    @Contract(pure = true)
    public static synchronized App getApp() {
        return instance;
    }

    public static App getApplication(@NonNull Activity activity) {
        return (App) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Hawk.init(this).build();
        //InitDagger
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build()
        ;

        PrettyFormatStrategy prettyFormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(3)
                .methodOffset(1)
                .tag(getString(R.string.app_name))
                .build();
//        Logger.addLogAdapter(new AndroidLogAdapter(prettyFormatStrategy) {
//            @Override
//            public boolean isLoggable(int priority, @Nullable String tag) {
//                return BuildConfig.DEBUG;
//            }
//        });

        if (BuildConfig.DEBUG) {
            try {
                PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                    messageDigest.update(signature.toByteArray());
                  //  Logger.i("KeyHash: " + Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener connectivityListener) {
        ConnectivityReceiver.connectivityReceiverListener = connectivityListener;
    }
}
