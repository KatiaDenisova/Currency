package com.example.currency.presenter;

import android.util.Log;

import com.example.currency.MainApp;
import com.example.currency.model.CurrencyDao;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.model.DataFilter;
import com.example.currency.model.DatabaseApp;
import com.example.currency.view.CurrenciesAdapter;
import com.example.currency.view.ViewCurrencyIntef;


import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PresenterCurrency implements PresentCurInterf, UpdateDataListener {
    CurrencyView view;
    MainApp mainApp;
    DatabaseApp db;
    ViewCurrencyIntef vci;
    private String TAG = "PresenterCurrency";

    public PresenterCurrency(CurrencyView view) {
        this.view = view;
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
                        loadData(currencyTwoDates);
                        view.showData(currencyTwoDates);

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
        if (currencyDao.getCurrenciesList().isEmpty()) {
            currencyDao.insertCurrencies(currencyTwoDateList);
        } else {
            currencyDao.updateCurrencies(currencyTwoDateList);
        }

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

    @Override
    public void updateDataCurrencies() {
        getCurrencyDao().getCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<List<CurrencyTwoDate>>() {
                    @Override
                    public boolean test(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {
                        return !currencyTwoDateList.contains(getCurrencyDao().getCurrenciesByShow(true));
                    }
                })
                .map(new Function<List<CurrencyTwoDate>, List<CurrencyTwoDate>>() {
                    @Override
                    public List<CurrencyTwoDate> apply(List<CurrencyTwoDate> currencyTwoDates) throws Exception {
                        Collections.sort(currencyTwoDates, CurrencyTwoDate.compareByPlace);
                        return currencyTwoDates;
                    }
                })
                .subscribe(new Consumer<List<CurrencyTwoDate>>() {
                    @Override
                    public void accept(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {
                       Log.d("tagListener", currencyTwoDateList.toString());
                        view.showData(currencyTwoDateList);
                    }
                });
    }

    @Override
    public CurrencyDao getCurrencyDao() {
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        return currencyDao;
    }
}



