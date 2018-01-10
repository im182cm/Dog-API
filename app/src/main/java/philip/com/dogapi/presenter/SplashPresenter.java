package philip.com.dogapi.presenter;

import android.os.Handler;

import philip.com.dogapi.contract.SplashContract;

/**
 * Created by Philip on 2018. 1. 8..
 */

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
