package com.example.tetetest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("/bucheon-api/api/pub/v1/bt-devices")
    Call <List<Map<String,Object>>> getData();

}
