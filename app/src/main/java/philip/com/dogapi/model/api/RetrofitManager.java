package philip.com.dogapi.model.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Philip on 2018. 1. 9..
 */

public class RetrofitManager {
    private final ApiInterface apiInterface;

    private RetrofitManager() {
        this.apiInterface = new Retrofit.Builder()
                .baseUrl("https://dog.ceo")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build()
                .create(ApiInterface.class);
    }

    private static class Singleton {
        private static final RetrofitManager instance = new RetrofitManager();
    }

    public static ApiInterface getInstance() {
        return Singleton.instance.apiInterface;
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }
}
