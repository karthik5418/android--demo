package karthik.app.demo;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by NiCK on 12/29/2017.
 */

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
