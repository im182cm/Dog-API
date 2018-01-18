package philip.com.dogapi.mvp.splash;

import philip.com.dogapi.mvp.BasePresenter;

public interface SplashContract {
    interface View {
        void goNextActivity();
    }

    interface Presenter extends BasePresenter {
        void start();
    }
}
