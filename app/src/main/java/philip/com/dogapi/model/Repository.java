package philip.com.dogapi.model;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Philip on 2018. 1. 8..
 */

public class Repository {
    private static final String LOG_TAG = Repository.class.getSimpleName();
    private static Repository INSTANCE = null;

    public Repository() {
    }

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }

        return INSTANCE;
    }

    public  <T> void start(Single<T> single, SingleObserver singleObserver){
        single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(singleObserver);
    }
}
