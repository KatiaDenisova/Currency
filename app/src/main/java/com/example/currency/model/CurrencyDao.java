package com.example.currency.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CurrencyDao {
    @Query("Select * from CurrencyTwoDate")
    Single<List<CurrencyTwoDate>> getCurrencies();

//    @Query("Select * from CurrencyTwoDate where charCode =:charCode")
//    CurrencyTwoDate getCurrency(String charCode);

    @Insert
    void insertCurrency(CurrencyTwoDate currencyTwoDate);

    @Insert
    CurrencyDao insertCurrencies(List<CurrencyTwoDate> currencyTwoDateList);

    @Update
    void updateCurrency(CurrencyTwoDate currencyTwoDate);

    @Update
    void updateCurrencies(List<CurrencyTwoDate> currencyTwoDateList);

    @Delete
    void deleteCurrency(CurrencyTwoDate currencyTwoDate);

    @Delete
    void deleteCurrencies(List<CurrencyTwoDate> currencyTwoDateList);
}