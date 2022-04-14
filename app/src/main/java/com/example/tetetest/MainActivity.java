package com.example.tetetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    String baseUrl = "https://senior.bucheon.go.kr/";
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        retrofitInterface.getData().enqueue(new Callback<List<Map<String,Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String,Object>>> call, Response<List<Map<String,Object>>> response) {
                Map<String,Object>> data = response.body().get(Integer.parseInt("devices"));
                ArrayList<Map<String,Object>> jsonList = (ArrayList) data.get("devices");
                mAdapter = new MyAdapter(jsonList);
                Log.d("TEST", " 성공성공");
                Log.d("TEST", String.valueOf(jsonList));
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Map<String,Object>>> call, Throwable t) {
                Log.d("TEST", "실패실패");
                t.printStackTrace();
            }
        });
    }
}