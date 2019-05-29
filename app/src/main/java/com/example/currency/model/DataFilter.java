package com.example.currency.model;

import com.example.currency.MainApp;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
public class DataFilter implements DataFilterInref {
    MainApp mainApp;
    public DataFilter(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public Single<List<CurrencyTwoDate>> getListCurTwoDay() {
        Single<Currencies> currencies1 = mainApp.getApi().getCurrencies("04.26.2019");
        Single<Map<String, Double>> currencies2 = mainApp.getApi().getCurrencies("04.25.2019")
                .map(new Function<Currencies, Map<String, Double>>() {
                    @Override
                    public Map<String, Double> apply(Currencies currencies) throws Exception {
                        Map<String, Double> hashMap = new HashMap<>();
                        for (Currency currency : currencies.currencies) {
                            hashMap.put(currency.getCharCode(), currency.getRateN());
                        }
                        return hashMap;
                    }
                });

        return Single.zip(currencies1, currencies2, new BiFunction<Currencies, Map<String, Double>, List<CurrencyTwoDate>>() {
            @Override
            public List<CurrencyTwoDate> apply(Currencies currencies1, Map<String, Double> oldRates) throws Exception {

                List<CurrencyTwoDate> currencyTwoDateList = new LinkedList<>();
                int place = 0;
                for (Currency currencyE : currencies1.currencies) {
//                    CurrencyTwoDate currencyTwoDate = new CurrencyTwoDate(
//                            currencyE.getId(),currencyE.getCharCode(),
//                            currencyE.getName(),currencyE.getRateN(),
//                            oldRates.get(currencyE.getCharCode()),
//                            currencyE.getNumCode(),
//                            currencyE.getScale());
//                    currencyTwoDate.setRateYesterday(oldRates.get(currencyE.getCharCode()));
                    place++;
                    CurrencyTwoDate currencyTwoDate = new CurrencyTwoDate();
                    currencyTwoDate.setId(currencyE.getId());
                    currencyTwoDate.setCharCode(currencyE.getCharCode());
                    currencyTwoDate.setName(currencyE.getName());
                    currencyTwoDate.setRateToday(currencyE.getRateN());
                    currencyTwoDate.setRateYesterday(oldRates.get(currencyE.getCharCode()));
                    currencyTwoDate.setNumCode(currencyE.getNumCode());
                    currencyTwoDate.setScale(currencyE.getScale());
                    currencyTwoDate.setShow(true);
                    currencyTwoDate.setPlace(place);


                    currencyTwoDateList.add(currencyTwoDate);

                }
                return currencyTwoDateList;
            }
        }).map(new Function<List<CurrencyTwoDate>, List<CurrencyTwoDate>>() {
            @Override
            public List<CurrencyTwoDate> apply(List<CurrencyTwoDate> currencyTwoDates) throws Exception {
                Collections.sort(currencyTwoDates, CurrencyTwoDate.compareByChareChode);
                return currencyTwoDates;
            }
        });
    }
}
