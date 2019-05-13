package com.example.currency.network;

import android.app.Application;


import androidx.room.Room;

import com.example.currency.model.CurrencyDao;
import com.example.currency.model.DatabaseApp;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class GlobalRetrofit extends Application {
GlobalRetrofit instance;
    private String URL_RASES = "http://www.nbrb.by/Services/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_RASES)
            .client(new OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    RatesAPI api = retrofit.create(RatesAPI.class);

    private DatabaseApp databaseApp;

    public GlobalRetrofit getInstance() {
        return instance;
    }

    public DatabaseApp getDatabaseApp() {
        return databaseApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        databaseApp = Room.databaseBuilder(this, DatabaseApp.class,"database").build();

    }

    public RatesAPI getApi() {
        return api;
    }
}
