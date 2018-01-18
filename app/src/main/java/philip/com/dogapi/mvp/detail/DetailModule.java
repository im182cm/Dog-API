package philip.com.dogapi.mvp.detail;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import philip.com.dogapi.di.ActivityScoped;
import philip.com.dogapi.di.FragmentScoped;
import philip.com.dogapi.util.Constant;

@Module
public abstract class DetailModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract DetailImageFragment detailContentFragmen();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DetailListFragment detailListFragment();

    @ActivityScoped
    @Binds
    abstract DetailContract.Presenter detailPresenter(DetailPresenter presenter);

    @Provides
    @ActivityScoped
    static String provideBreed(DetailActivity detailActivity) {
        return detailActivity.getIntent().getStringExtra(Constant.EXTRA_BREED);
    }
}
