package philip.com.dogapi.model.api;

import io.reactivex.Single;
import philip.com.dogapi.model.dto.BaseDTO;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Philip on 2018. 1. 8..
 */

public interface ApiInterface {
    @GET("/api/breeds/list/all")
    Single<BaseDTO> getAllBreeds();

    @GET("/api/breed/{breed}/list")
    Single<BaseDTO> getAllSubBreeds(@Path("breed") String breed);

    @GET("/api/breed/{breed}/{subbreed}/images")
    Single<BaseDTO> getAllImages(@Path("breed") String breed, @Path("subbreed") String subbreed);
}
