package com.example.currency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView currencyList;
    private CurrenciesAdapter currencyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyList = findViewById(R.id.rv_currency);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        currencyList.setLayoutManager(layoutManager);

GlobalRetrofit globalRetrofit = (GlobalRetrofit) getApplicationContext();
        globalRetrofit.getApi().currencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver <Currencies>() {
                    @Override
                    public void onSuccess(Currencies currencies) {
                        Log.d("tag", "onSuccess: "+ currencies);
                        Toast.makeText(getApplicationContext(), "msg: " + currencies, Toast.LENGTH_LONG).show();
                        currencyAdapter = new CurrenciesAdapter(currencies.getCurrencies());
                        currencyList.setAdapter(currencyAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                });






    }
}
