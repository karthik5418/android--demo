package karthik.app.demo;

import android.app.Application;

/**
 * Created by NiCK on 1/2/2018.
 */

// DispatchingAndroidInjector<Activity> : Last thing we have to do is injecting into Injector. What is the reason of This injector.
// Application has activities. That is why we implement HasActivityInjector interface

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //initializeComponent();
    }
}
