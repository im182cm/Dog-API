package philip.com.dogapi.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import philip.com.dogapi.mvp.detail.DetailActivity;
import philip.com.dogapi.mvp.detail.DetailModule;
import philip.com.dogapi.mvp.main.MainActivity;
import philip.com.dogapi.mvp.main.MainModule;

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = DetailModule.class)
    abstract DetailActivity detailActivity();
}