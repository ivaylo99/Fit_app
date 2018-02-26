package com.example.ivo.fit_app;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Ivo on 22.2.2018 г..
 */

public interface GetMealsService {

    String ENDPOINT = "http://192.168.1.100:8080/";

    @Headers({"Content-Type: application/json"})
    @GET("users/{id}/diets")
    Call<List<Food>> getMeals(@Path("id") String id,@Header("authorization") String token);
}
