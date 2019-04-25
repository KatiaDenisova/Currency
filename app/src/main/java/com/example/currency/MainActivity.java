package com.example.currency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String URL_RASES = "http://www.nbrb.by/Services/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_RASES)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        RatesAPI api = retrofit.create(RatesAPI.class);

        Call<Currencies> call = api.getCurrencies();
        call.enqueue(new Callback<Currencies>() {
            @Override
            public void onResponse(Call<Currencies> call, Response<Currencies> response) {
                Log.d("tag", "onResponse: "+ response.body());
            }

            @Override
            public void onFailure(Call<Currencies> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
