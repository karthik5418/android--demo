package karthik.app.demo.di.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import karthik.app.demo.App;
import karthik.app.demo.di.network.NetworkModule;
import karthik.app.demo.di.viewmodel.ViewModelModule;

/**
 * Created by NiCK on 1/2/2018.
 */


//  ActivityBuilderModule : It is an internal class in Dagger 2.10. Provides our activities and fragments with given module

@Component(modules = {AndroidInjectionModule.class, ActivityBuilderModule.class, AppModule.class, NetworkModule.class, ViewModelModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(App app);
}
