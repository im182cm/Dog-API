package philip.com.dogapi.contract;

import java.util.ArrayList;

import philip.com.dogapi.presenter.BasePresenter;
import philip.com.dogapi.view.BaseView;

/**
 * Created by Philip on 2018. 1. 7..
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showData(ArrayList<String> breeds, ArrayList<String> subBreeds);
        void setProgress(boolean show);
    }

    interface Presenter extends BasePresenter {
        void start();
        void loadData();
    }
}
