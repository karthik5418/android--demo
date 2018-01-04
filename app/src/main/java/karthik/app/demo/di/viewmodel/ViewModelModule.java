package karthik.app.demo.di.viewmodel;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import karthik.app.demo.ui.snapdeal.SnapdealViewModel;

/**
 * Created by NiCK on 1/3/2018.
 */

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SnapdealViewModel.class)
    abstract ViewModel bindSnapdealViewModel(SnapdealViewModel snapdealViewModel);
}
