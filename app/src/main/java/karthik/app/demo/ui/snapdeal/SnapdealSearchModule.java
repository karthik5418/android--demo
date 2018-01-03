package karthik.app.demo.ui.snapdeal;

import dagger.Module;
import dagger.Provides;
import karthik.app.demo.di.scope.PerActivity;

/**
 * Created by NiCK on 1/2/2018.
 */

@Module
public class SnapdealSearchModule {

    @Provides
    @PerActivity
    SnapdealSearchActivity provideSnapdealSearchActvity(SnapdealSearchActivity snapdealSearchActivity) {

        return snapdealSearchActivity;
    }
}
