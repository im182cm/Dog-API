package philip.com.dogapi.mvp.detail;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import philip.com.dogapi.mvp.model.api.ApiInterface;
import philip.com.dogapi.mvp.model.dto.BaseDTO;
import philip.com.dogapi.mvp.model.Repository;

public class DetailPresenter implements DetailContract.Presenter {
    private static final String LOG_TAG = DetailPresenter.class.getSimpleName();
    private DetailContract.View mListView, mImgView;
    private final Repository mRepository = Repository.getInstance();
    private String mBreed, mSubBreed;
    private boolean mFirstLoad = true;

    private final ApiInterface mApiInterface;

    @Inject
    public DetailPresenter(ApiInterface apiInterface, String breed) {
        this.mApiInterface = apiInterface;
        this.mBreed = breed;
    }

    @Override
    public void start() {
        if (mFirstLoad) {
            loadData();
            mFirstLoad = false;
        }
    }

    @Override
    public void loadData() {
        mListView.setProgress(true);
        Log.d(LOG_TAG, "loadData()");
        mRepository.start(mApiInterface.getAllSubBreeds(mBreed).map(new Function<BaseDTO, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(BaseDTO baseDTO) throws Exception {
                return (ArrayList) baseDTO.getMessage();
            }
        }), new SingleObserver<ArrayList<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(LOG_TAG, "onSubscribe()");
                //mListView.setProgress(false);
            }

            @Override
            public void onSuccess(ArrayList<String> subBreeds) {
                Log.d(LOG_TAG, "onSuccess()");
                mListView.setProgress(false);
                mListView.showData(subBreeds);
                loadImages(subBreeds.get(0));
            }

            @Override
            public void onError(Throwable e) {
                Log.d(LOG_TAG, "onError()");
                Log.d(LOG_TAG, "onError()", e);
                mListView.setProgress(false);
            }
        });
    }

    @Override
    public void loadImages(@NonNull String subBreed) {
        if (!TextUtils.isEmpty(mSubBreed) && mSubBreed.equals(subBreed))
            return;

        mSubBreed = subBreed;
        mImgView.setProgress(true);
        Log.d(LOG_TAG, "loadData()");
        mRepository.start(mApiInterface.getAllImages(mBreed, subBreed).map(new Function<BaseDTO, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(BaseDTO baseDTO) throws Exception {
                return (ArrayList) baseDTO.getMessage();
            }
        }), new SingleObserver<ArrayList<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(LOG_TAG, "onSubscribe()");
                //mImgView.setProgress(false);
            }

            @Override
            public void onSuccess(ArrayList<String> images) {
                Log.d(LOG_TAG, "onSuccess()");
                Log.d(LOG_TAG, images.toString());
                mImgView.setProgress(false);
                mImgView.showData(images);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(LOG_TAG, "onError()");
                Log.d(LOG_TAG, "onError()", e);
                mImgView.setProgress(false);
            }
        });
    }

    @Override
    public void setSubBreed(@NonNull String breed) {
        loadImages(breed);
    }

    @Override
    public void takeView(DetailContract.View view) {
        if (view instanceof DetailListFragment) {
            this.mListView = view;
        } else if (view instanceof DetailImageFragment) {
            this.mImgView = view;
        }
    }
}
