package com.example.currency;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.network.GlobalRetrofit;
import com.example.currency.presenter.PresenterCurrency;
import com.example.currency.view.CurrenciesAdapter;
import com.example.currency.view.ViewCurrencyIntef;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewCurrencyIntef {

    private Toolbar toolbar;
    private String TAG ="Main Activity";
    private RecyclerView currencyList;
    private CurrenciesAdapter currencyAdapter;
    private PresenterCurrency presenterCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currencyList = findViewById(R.id.rv_currency);


        GlobalRetrofit globalRetrofit = (GlobalRetrofit) getApplicationContext();

        setMVP();
        setUpViews();
        getCurrenciesList(globalRetrofit);

//        Single<Currencies> currencies1 = globalRetrofit.getApi().getCurrencies("04.26.2019");
//        Single<Map<String, Double>> currencies2 = globalRetrofit.getApi().getCurrencies("04.25.2019")
//                .map(new Function<Currencies, Map<String, Double>>() {
//                    @Override
//                    public Map<String, Double> apply(Currencies currencies) throws Exception {
//                        Map<String, Double> hashMap = new HashMap<>();
//                        for (Currency currency : currencies.currencies) {
//                            hashMap.put(currency.getCharCode(), currency.getRateN());
//                        }
//                        return hashMap;
//                    }
//                });
//        Single.zip(currencies1, currencies2, new BiFunction<Currencies, Map<String, Double>, List<CurrencyTwoDate>>() {
//            @Override
//            public List<CurrencyTwoDate> apply(Currencies currencies1, Map<String, Double> oldRates) throws Exception {
//
//                List<CurrencyTwoDate> currencyTwoDateList = new LinkedList<>();
//                for (Currency currencyE : currencies1.currencies) {
//                    CurrencyTwoDate currencyTwoDate = new CurrencyTwoDate();
//                    currencyTwoDate.setRateYesterday(oldRates.get(currencyE.getCharCode()));
//                    currencyTwoDate.setId(currencyE.getId());
//                    currencyTwoDate.setCharCode(currencyE.getCharCode());
//                    currencyTwoDate.setName(currencyE.getName());
//                    currencyTwoDate.setNumCode(currencyE.getNumCode());
//                    currencyTwoDate.setRateToday(currencyE.getRateN());
//                    currencyTwoDate.setScale(currencyE.getScale());
//                    currencyTwoDateList.add(currencyTwoDate);
//                }
//                return currencyTwoDateList;
//            }
//        }).map(new Function<List<CurrencyTwoDate>, List<CurrencyTwoDate>>() {
//            @Override
//            public List<CurrencyTwoDate> apply(List<CurrencyTwoDate> currencyTwoDates) throws Exception {
//                Collections.sort(currencyTwoDates, CurrencyTwoDate.compareByChareChode);
//                return currencyTwoDates;
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<List<CurrencyTwoDate>>() {
//
//
//                    @Override
//                    public void onSuccess(List<CurrencyTwoDate> currencyTwoDates) {
//                        Log.d("TAG", currencyTwoDates.toString());
//                        currencyAdapter = new CurrenciesAdapter(currencyTwoDates);
//                        currencyList.setAdapter(currencyAdapter);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//                });
    }

    private void setMVP() {
        presenterCurrency = new PresenterCurrency(this);
    }

    private void setUpViews() {
        currencyList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getCurrenciesList(GlobalRetrofit globalRetrofit) {
        presenterCurrency.getCurrencies(globalRetrofit);
    }

    @Override
    public void showToast(String str) {

    }

    @Override
    public void displayCurrencies(List<CurrencyTwoDate> listCur) {
        if(listCur!=null) {
            currencyAdapter = new CurrenciesAdapter(listCur);
            currencyList.setAdapter(currencyAdapter);
        }
        else {
            Log.d(TAG,"Currencies response null");
        }
    }

    @Override
    public void displayError(String str) {
        showToast(str);
    }
}

