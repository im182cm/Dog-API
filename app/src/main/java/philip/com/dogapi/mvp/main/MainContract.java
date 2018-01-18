package philip.com.dogapi.mvp.main;

import java.util.ArrayList;

import philip.com.dogapi.mvp.BasePresenter;

public interface MainContract {
    interface View {
        void showData(ArrayList<String> breeds, ArrayList<String> subBreeds);
        void setProgress(boolean show);
    }

    interface Presenter extends BasePresenter {
        void start();
        void loadData();
        void takeView(MainContract.View view);
    }
}
