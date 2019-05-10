package com.example.currency;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView currencyList;
    private CurrenciesAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currencyList = findViewById(R.id.rv_currency);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        currencyList.setLayoutManager(layoutManager);

        GlobalRetrofit globalRetrofit = (GlobalRetrofit) getApplicationContext();

        Single<Currencies> currencies1 = globalRetrofit.getApi().getCurrencies("04.26.2019");
        Single<Currencies> currencies2 = globalRetrofit.getApi().getCurrencies("04.25.2019");
        Single.zip(currencies1, currencies2, new BiFunction<Currencies, Currencies, List<CurrencyTwoDate>>() {
            @Override
            public List<CurrencyTwoDate> apply(Currencies currencies1, Currencies currencies2) throws Exception {
                Map<String,Double> hashMap = new HashMap<>();
                for(Currency currency: currencies2.currencies) {
                    hashMap.put(currency.getCharCode(),currency.getRateN());
                }

                List<CurrencyTwoDate> currencyTwoDateList = new LinkedList<>();
                for(Currency currency: currencies1.currencies) {
                    CurrencyTwoDate currencyTwoDate = new CurrencyTwoDate();
                    currencyTwoDate.setRateYesterday(hashMap.get(currency.getCharCode()));
                    currencyTwoDate.setId(currency.getId());
                    currencyTwoDate.setCharCode(currency.getCharCode());
                    currencyTwoDate.setName(currency.getName());
                    currencyTwoDate.setNumCode(currency.getNumCode());
                    currencyTwoDate.setRateToday(currency.getRateN());
                    currencyTwoDate.setScale(currency.getScale());
                    currencyTwoDateList.add(currencyTwoDate);
                }


                return currencyTwoDateList;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<CurrencyTwoDate>>() {


                    @Override
                    public void onSuccess(List<CurrencyTwoDate> currencyTwoDates) {
                        Log.d("TAG", currencyTwoDates.toString());
                        currencyAdapter = new CurrenciesAdapter(currencyTwoDates);
                      currencyList.setAdapter(currencyAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}

