package com.example.ivo.fit_app;
/**
 * Created by Ivo on 13.2.2018 г..
 */

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserLoginService {

    String ENDPOINT = "http://192.168.1.102:8080";

    @POST("/login")
    Call<ResponseBody> getAuthorization(@Body Login login);
}
