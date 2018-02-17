package com.example.ivo.fit_app;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Ivo on 17.2.2018 г..
 */

public interface GetIdFromTokenService {

    String ENDPOINT = "http://192.168.1.102:8080/";

    @GET("getUserIdFromToken")
    Call<ResponseBody> getUserId(@Header("authorization") String token);
}
