package com.example.currency.model;

import com.example.currency.network.GlobalRetrofit;

import java.util.List;

import io.reactivex.Single;

public interface DataFilterInref {
    Single<List<CurrencyTwoDate>> getListCurTwoDay();

}
