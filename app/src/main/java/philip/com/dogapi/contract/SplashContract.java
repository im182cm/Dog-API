package philip.com.dogapi.contract;

import philip.com.dogapi.presenter.BasePresenter;
import philip.com.dogapi.view.BaseView;

/**
 * Created by Philip on 2018. 1. 8..
 */

public interface SplashContract {
    interface View extends BaseView<Presenter> {
        void goNextActivity();
    }

    interface Presenter extends BasePresenter {
        void start();
    }
}
