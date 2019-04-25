package com.example.currency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RatesAPI api = retrofit.create(RatesAPI.class);
        api.currencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver <Currencies>() {
                    @Override
                    public void onSuccess(Currencies currencies) {
                        Log.d("tag", "onSuccess: "+ currencies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
//        Call<Currencies> call = api.getCurrencies();
//        call.enqueue(new Callback<Currencies>() {
//            @Override
//            public void onResponse(Call<Currencies> call, Response<Currencies> response) {
//                Log.d("tag", "onResponse: "+ response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Currencies> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }
}
