package com.example.currency.presenter;

import android.util.Log;

import com.example.currency.MainApp;
import com.example.currency.model.CurrencyDao;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.model.DataFilter;
import com.example.currency.model.DatabaseApp;
import com.example.currency.view.ViewCurrencyIntef;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PresenterCurrency implements PresentCurInterf {
    MainApp mainApp;
    DatabaseApp db;
    ViewCurrencyIntef vci;
    private String TAG = "PresenterCurrency";

    public PresenterCurrency() {

    }

    @Override
    public void getCurrencies(MainApp mainApp) {
//        db = mainApp.getInstance().getDatabaseApp();
        DataFilter dataFilter = new DataFilter(mainApp);
        dataFilter.getListCurTwoDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<CurrencyTwoDate>>() {
                    @Override
                    public void onSuccess(List<CurrencyTwoDate> currencyTwoDates) {

//                        Log.d(TAG, currencyTwoDates.toString());
//                        vci.displayCurrencies(currencyTwoDates);
                        loadData(currencyTwoDates);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        vci.displayError("Error fetching Currency Data ");

                    }
                });
    }

    @Override
    public void loadData(List<CurrencyTwoDate> currencyTwoDateList) {
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        if (currencyDao.getCurrencies() == null) {
            currencyDao.insertCurrencies(currencyTwoDateList);
        } else {
            currencyDao.updateCurrencies(currencyTwoDateList);
        }
//        currencyDao.insertCurrencies(currencyTwoDateList);

    }

    @Override
    public void getCurrenciesByDb(CurrencyDao dao) {
        dao.getCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CurrencyTwoDate>>() {
                    @Override
                    public void accept(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {

                    }
                });
    }


    @Override
    public void onClear() {

    }

}



