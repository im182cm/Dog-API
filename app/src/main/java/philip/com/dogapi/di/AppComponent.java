package philip.com.dogapi.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import philip.com.dogapi.DogApiApp;
import philip.com.dogapi.mvp.model.RepositoryModule;

@Singleton
@Component(modules = {ApplicationModule.class,
        RepositoryModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DogApiApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}