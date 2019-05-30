package com.example.currency.presenter;

import com.example.currency.model.CurrencyDao;

public interface UpdateDataListener {
    void updateDataCurrencies();
    CurrencyDao getCurrencyDao();
}
