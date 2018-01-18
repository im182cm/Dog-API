package philip.com.dogapi.mvp.detail;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import philip.com.dogapi.mvp.BasePresenter;

public interface DetailContract {
    interface View {
        void showData(ArrayList<String> subBreeds);
        void setProgress(boolean show);
    }

    interface Presenter extends BasePresenter {
        void loadData();
        void loadImages(@NonNull String breed);
        void setSubBreed(@NonNull String subBreed);
        void takeView(DetailContract.View view);
    }
}
