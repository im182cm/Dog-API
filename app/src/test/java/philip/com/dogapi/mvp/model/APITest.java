package philip.com.dogapi.mvp.model;


import com.google.gson.internal.LinkedTreeMap;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import philip.com.dogapi.mvp.model.api.ApiInterface;
import philip.com.dogapi.mvp.model.dto.BaseDTO;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by 1000140 on 2018. 1. 11..
 */
public class APITest {
    private MockWebServer mockWebServer;
    private ApiInterface service;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    @After
    public void setDown() {
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllBreeds() {
        mockWebServer.enqueue(new MockResponse().setBody("{\"status\":\"success\",\"message\":{\"wolfhound\":[\"irish\"]}}"));

        LinkedTreeMap<String, ArrayList<String>> mockData = new LinkedTreeMap<>();
        mockData.put("wolfhound", new ArrayList<String>(Arrays.asList("irish")));
        BaseDTO<LinkedTreeMap<String, ArrayList<String>>> mockDTO = new BaseDTO<>("success", mockData);

        TestObserver<BaseDTO> observer = TestObserver.create();
        service.getAllBreeds().subscribe(observer);
        observer.assertNoErrors();
        observer.assertComplete();

        BaseDTO response = observer.values().get(0);
        assertEquals(mockDTO.getStatus(), response.getStatus());
        System.out.println(mockDTO.getStatus() + " / " + response.getStatus());
        assertEquals(mockDTO.getMessage().toString(), response.getMessage().toString());
        System.out.println(mockDTO.getMessage().toString() + " / " + response.getMessage().toString());
    }
}