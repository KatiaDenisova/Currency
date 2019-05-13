package com.example.currency.presenter;

import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.network.GlobalRetrofit;

import java.util.List;

public interface PresentCurInterf {

    void getCurrencies(GlobalRetrofit globalRetrofit);
    void loadData(List<CurrencyTwoDate> currencyTwoDateList);
    void onClear();
}
