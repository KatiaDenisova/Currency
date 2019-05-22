package com.example.currency;

import android.app.Application;


import androidx.room.Room;

import com.example.currency.model.DatabaseApp;
import com.example.currency.network.RatesAPI;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainApp extends Application {

public static MainApp instance;

    private String URL_RASES = "http://www.nbrb.by/Services/";

    RatesAPI api;

    private DatabaseApp databaseApp;

    public static MainApp getInstance() {
        return instance;
    }

    public DatabaseApp getDatabaseApp() {
        return databaseApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        api  = initRetrofit();

        databaseApp = Room.databaseBuilder(this, DatabaseApp.class,"database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }

    private RatesAPI initRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_RASES)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(RatesAPI.class);
    }

    public RatesAPI getApi() {
        return api;
    }
}
