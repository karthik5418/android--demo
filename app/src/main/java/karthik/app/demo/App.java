package karthik.app.demo;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import karthik.app.demo.di.app.DaggerAppComponent;

/**
 * Created by NiCK on 1/2/2018.
 */

// DispatchingAndroidInjector<Activity> : Last thing we have to do is injecting into Injector. What is the reason of This injector.
// Application has activities. That is why we implement HasActivityInjector interface

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
