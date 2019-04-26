package com.example.currency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
//    private String URL_RASES = "http://www.nbrb.by/Services/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL_RASES)
//                .client(new OkHttpClient())
//                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();

//        RatesAPI api = retrofit.create(RatesAPI.class);

GlobalRetrofit globalRetrofit = (GlobalRetrofit) getApplicationContext();
        globalRetrofit.getApi().currencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver <Currencies>() {
                    @Override
                    public void onSuccess(Currencies currencies) {
                        Log.d("tag", "onSuccess: "+ currencies);
                        Toast.makeText(getApplicationContext(), "msg: " + currencies, Toast.LENGTH_LONG).show();
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
