package karthik.app.demo.di.app;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import karthik.app.demo.ui.categories.CategoryFragmentModule;
import karthik.app.demo.ui.main.MainActivity;
import karthik.app.demo.ui.main.MainActivityModule;
import karthik.app.demo.ui.sub_categories.SubCategoryFragmentModule;

/**
 * Created by NiCK on 12/30/2017.
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, CategoryFragmentModule.class, SubCategoryFragmentModule.class})
    abstract MainActivity bindMainActivity;

}
