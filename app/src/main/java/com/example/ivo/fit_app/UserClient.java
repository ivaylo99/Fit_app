package com.example.ivo.fit_app;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Ivo on 2.2.2018 г..
 */

public interface UserClient {

    String ENDPOINT = "http://192.168.43.206:8080/";

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("users/sign-up")
    Call<User> createUser(@Body User user);

    @GET("getUserIdFromToken")
    Call<ResponseBody> getUserId(@Header("authorization") String token);

    @POST("users/{id}/measurements")
    Call<Measurments> addMeasurment(@Body float weight,@Path("id")String id,@Header("authorization") String token);
}
