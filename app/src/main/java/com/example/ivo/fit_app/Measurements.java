package com.example.ivo.fit_app;

/**
 * Created by Ivo on 13.2.2018 г..
 */

public class Measurements {

    private float weight;
    private float biceps;
    private float calf;
    private float chest;
    private float hip;
    private float thigh;
    private float waist;


    public Measurements(float weight, float biceps, float calf, float chest, float hip, float thigh, float waist) {
        this.weight = weight;
        this.biceps = biceps;
        this.calf = calf;
        this.chest = chest;
        this.hip = hip;
        this.thigh = thigh;
        this.waist = waist;
    }

    public Measurements(float weight) {
        this.weight = weight;
    }

    public float getBiceps() {
        return biceps;
    }

    public void setBiceps(float biceps) {
        this.biceps = biceps;
    }

    public float getCalf() {
        return calf;
    }

    public void setCalf(float calf) {
        this.calf = calf;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public float getThigh() {
        return thigh;
    }

    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
