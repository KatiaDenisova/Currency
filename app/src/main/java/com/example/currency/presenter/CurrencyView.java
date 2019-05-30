package com.example.currency.presenter;

import com.example.currency.model.CurrencyTwoDate;

import java.util.List;

public interface CurrencyView {
    void showData(List<CurrencyTwoDate> list);
}
