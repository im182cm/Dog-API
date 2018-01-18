package philip.com.dogapi.mvp.main;

import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import philip.com.dogapi.di.ActivityScoped;
import philip.com.dogapi.mvp.model.api.ApiInterface;
import philip.com.dogapi.mvp.model.dto.BaseDTO;
import philip.com.dogapi.mvp.model.dto.Breeds;
import philip.com.dogapi.mvp.model.Repository;

/**
 * Created by Philip on 2018. 1. 7..
 */

@ActivityScoped
public class MainPresenter implements MainContract.Presenter {
    private static final String LOG_TAG = MainPresenter.class.getSimpleName();
    private MainContract.View mView;
    private final Repository mRepository = Repository.getInstance();
    private boolean mFristLoad = true;

    private final ApiInterface mApiInterface;

    @Inject
    public MainPresenter(ApiInterface apiInterface) {
        this.mApiInterface = apiInterface;
    }

    @Override
    public void start() {
        if (mFristLoad) {
            mView.setProgress(true);
            loadData();
            mFristLoad = false;
        }
    }

    @Override
    public void loadData() {
        Log.d(LOG_TAG, "loadData()");
        mRepository.start(mApiInterface.getAllBreeds().map(new Function<BaseDTO, Breeds>() {

            @Override
            public Breeds apply(BaseDTO baseDTO) throws Exception {
                LinkedTreeMap<String, ArrayList<String>> data = (LinkedTreeMap) baseDTO.getMessage();

                ArrayList<String> breeds = new ArrayList<>();
                ArrayList<String> subBreeds = new ArrayList<>();

                for (String breed : data.keySet()) {
                    breeds.add(breed);
                    ArrayList<String> subBreed = data.get(breed);
                    if (subBreed != null && subBreed.size() > 0) {
                        subBreeds.add(subBreed.toString().substring(1, subBreed.toString().length() - 1));
                    } else {
                        subBreeds.add("No SubBreeds");
                    }
                }
                return new Breeds(breeds, subBreeds);
            }
        }), new SingleObserver<Breeds>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(LOG_TAG, "onSubscribe()");
            }

            @Override
            public void onSuccess(Breeds breeds) {
                Log.d(LOG_TAG, "onSuccess()");
                mView.setProgress(false);
                mView.showData(breeds.getmBreedAL(), breeds.getmSubBreedAL());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(LOG_TAG, "onError()");
                Log.e(LOG_TAG, "exception", e);
            }
        });
    }

    @Override
    public void takeView(MainContract.View view) {
        this.mView = view;
    }
}
