package com.example.currency.view;

import com.example.currency.model.CurrencyTwoDate;

import java.util.List;

public interface ViewCurrencyIntef {
    void showToast(String str);
    void displayCurrencies(List<CurrencyTwoDate> listCur);
    void displayError(String str);
}
