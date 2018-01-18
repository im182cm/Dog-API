package philip.com.dogapi.mvp.main;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import philip.com.dogapi.di.ActivityScoped;
import philip.com.dogapi.di.FragmentScoped;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link MainPresenter}.
 */
@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);
}
