package karthik.app.demo.di.app;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import karthik.app.demo.di.scope.ApplicationContext;
import karthik.app.demo.ui.snapdeal.SnapdealSearchComponent;

/**
 * Created by NiCK on 1/2/2018.
 */

@Module(subcomponents = {SnapdealSearchComponent.class})
public class AppModule {

    @Provides
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

}
