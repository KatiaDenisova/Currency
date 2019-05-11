package com.example.currency.presenter;

import android.util.Log;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.model.DataFilter;
import com.example.currency.network.GlobalRetrofit;
import com.example.currency.view.ViewCurrencyIntef;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PresenterCurrency implements PresentCurInterf {

    ViewCurrencyIntef vci;
    private String TAG = "PresenterCurrency";

    public PresenterCurrency(ViewCurrencyIntef vci) {
        this.vci = vci;
    }

    @Override
    public void getCurrencies(GlobalRetrofit globalRetrofit) {
        DataFilter dataFilter = new DataFilter();
        dataFilter.getListCurTwoDay(globalRetrofit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<CurrencyTwoDate>>() {


                    @Override
                    public void onSuccess(List<CurrencyTwoDate> currencyTwoDates) {
                        Log.d(TAG, currencyTwoDates.toString());
                        vci.displayCurrencies(currencyTwoDates);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        vci.displayError("Error fetching Currency Data ");

                    }
                });
    }

}



