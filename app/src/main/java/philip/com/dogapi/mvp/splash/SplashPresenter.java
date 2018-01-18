package philip.com.dogapi.mvp.splash;

import android.os.Handler;

import philip.com.dogapi.mvp.splash.SplashContract;

public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mView;

    public SplashPresenter(SplashContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.goNextActivity();
            }
        }, 1000);
    }
}
