package com.example.ivo.fit_app;

/**
 * Created by Ivo on 23.2.2018 г..
 */

import java.util.HashMap;
import java.util.Map;



public class Food {


    private Integer id;

    private String name;

    private Integer proteins;

    private Integer fats;

    private Integer carbs;

    private Double calsPerUnit;

    private Integer unit;

    private Integer amount;

    private String predominatMacros;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Food(Integer id, String name, Integer proteins, Integer fats, Integer carbs, Double calsPerUnit, Integer unit, Integer amount, String predominatMacros) {
        this.id = id;
        this.name = name;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
        this.calsPerUnit = calsPerUnit;
        this.unit = unit;
        this.amount = amount;
        this.predominatMacros = predominatMacros;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getProteins() {
        return proteins;
    }


    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }


    public Integer getFats() {
        return fats;
    }


    public void setFats(Integer fats) {
        this.fats = fats;
    }


    public Integer getCarbs() {
        return carbs;
    }


    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }


    public Double getCalsPerUnit() {
        return calsPerUnit;
    }


    public void setCalsPerUnit(Double calsPerUnit) {
        this.calsPerUnit = calsPerUnit;
    }


    public Integer getUnit() {
        return unit;
    }


    public void setUnit(Integer unit) {
        this.unit = unit;
    }


    public Integer getAmount() {
        return amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getPredominatMacros() {
        return predominatMacros;
    }


    public void setPredominatMacros(String predominatMacros) {
        this.predominatMacros = predominatMacros;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}