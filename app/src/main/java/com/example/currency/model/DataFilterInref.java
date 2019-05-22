package com.example.currency.model;

import java.util.List;

import io.reactivex.Single;

public interface DataFilterInref {
    Single<List<CurrencyTwoDate>> getListCurTwoDay();

}
