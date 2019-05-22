package com.example.currency.presenter;

import com.example.currency.MainApp;
import com.example.currency.model.CurrencyTwoDate;

import java.util.List;

public interface PresentCurInterf {

    void getCurrencies(MainApp mainApp);
    void loadData(List<CurrencyTwoDate> currencyTwoDateList);
    void onClear();
}
