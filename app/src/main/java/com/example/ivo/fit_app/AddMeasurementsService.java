package com.example.ivo.fit_app;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Ivo on 17.2.2018 г..
 */

public interface AddMeasurementsService {

    String ENDPOINT = "http://192.168.1.102:8080/";

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("users/{id}/measurements")
    Call<Measurments> addMeasurment(@Body Measurments weight, @Path("id") String id, @Header("authorization") String token);
}
