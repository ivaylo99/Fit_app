package com.example.ivo.fit_app;

/**
 * Created by Ivo on 13.2.2018 г..
 */

public class Measurments {

    private float weight;
    private String id;
    private String token;

    public Measurments(float weight, String id, String token) {
        this.weight = weight;
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
