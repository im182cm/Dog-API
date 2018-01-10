package philip.com.dogapi.presenter;

import android.util.Log;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import philip.com.dogapi.model.api.RetrofitManager;
import philip.com.dogapi.contract.DetailContract;
import philip.com.dogapi.model.dto.BaseDTO;
import philip.com.dogapi.model.Repository;

/**
 * Created by Philip on 2018. 1. 7..
 */

public class DetailPresenter implements DetailContract.Presenter {
    private static final String LOG_TAG = DetailPresenter.class.getSimpleName();
    private final DetailContract.View mListView, mImgView;
    private final Repository mRepository;
    private String mBreed;
    private boolean mFirstLoad = true;

    public DetailPresenter(DetailContract.View listView, DetailContract.View imgView, Repository repository, String breed) {
        this.mListView = listView;
        this.mImgView = imgView;
        this.mRepository = repository;
        this.mBreed = breed;

        mListView.setPresenter(this);
        mImgView.setPresenter(this);
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
        mRepository.start(RetrofitManager.getInstance().getAllSubBreeds(mBreed).map(new Function<BaseDTO, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(BaseDTO baseDTO) throws Exception {
                return (ArrayList)baseDTO.getMessage();
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
    public void loadImages(String subBreed) {
        mImgView.setProgress(true);
        Log.d(LOG_TAG, "loadData()");
        mRepository.start(RetrofitManager.getInstance().getAllImages(mBreed, subBreed).map(new Function<BaseDTO, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(BaseDTO baseDTO) throws Exception {
                return (ArrayList)baseDTO.getMessage();
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
    public void setBreed(String breed) {
        loadImages(breed);
    }
}
