package com.example.ivo.fit_app;

/**
 * Created by Ivo on 2.2.2018 г..
 */

public class User {

    private String name;
    private String email;
    private String password;
    private String gender;
    private Integer height;
    private Integer weight;
    private Integer age;
    private Integer activity;
    private String goal;

    public User(){

    }

    public User(String name, String email, String password, String gender, Integer height, Integer weight, Integer age, Integer activity, String goal) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.activity = activity;
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}


