package karthik.app.demo.ui.snapdeal;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by NiCK on 1/2/2018.
 */

@Subcomponent(modules = SnapdealSearchModule.class)
public interface SnapdealSearchComponent extends AndroidInjector<SnapdealSearchActivity>{

    @Subcomponent.Builder
     abstract class Builder extends AndroidInjector.Builder<SnapdealSearchActivity> {
    }
}
