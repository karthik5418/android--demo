package karthik.app.demo.di.app;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import karthik.app.demo.ui.snapdeal.SnapdealSearchActivity;
import karthik.app.demo.ui.snapdeal.SnapdealSearchComponent;

/**
 * Created by NiCK on 1/2/2018.
 */

@Module
public abstract class ActivityBuilderModule {
    @Binds
    @IntoMap
    @ActivityKey(SnapdealSearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSnapdealSearchActivity(SnapdealSearchComponent.Builder builder);

}
