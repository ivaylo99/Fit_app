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

    String ENDPOINT = "http://192.168.1.100:8080/";

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("users/sign-up")
    Call<User> createUser(@Body User user);

    @GET("getUserIdFromToken")
    Call<ResponseBody> getUserId(@Header("authorization") String token);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("users/1/measurements")
    Call<Measurments> addMeasurment(@Body Measurments weight,@Header("authorization") String token);
}
