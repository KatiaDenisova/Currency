package com.example.currency.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CurrencyDao {
    @Query("Select * from CurrencyTwoDate")
    Flowable<List<CurrencyTwoDate>> getCurrencies();

    @Query("Select * from CurrencyTwoDate")
    List<CurrencyTwoDate> getCurrenciesList();

    @Query("Select * from CurrencyTwoDate where place = :place")
    CurrencyTwoDate getCurrencyByPlace(int place);

//    @Query("Select * from CurrencyTwoDate where charCode =:charCode")
//    CurrencyTwoDate getCurrency(String charCode);
    @Query("Select * from CurrencyTwoDate where show = :show")
    List<CurrencyTwoDate> getCurrenciesByShow(Boolean show);

    @Query("Select * from CurrencyTwoDate where show = :show")
    Flowable<List<CurrencyTwoDate>> getCurrenciesByShowFlowable(Boolean show);

    @Query("Select * from CurrencyTwoDate where charCode = :charCode")
    CurrencyTwoDate getCurrencyByCharCode(String charCode);

    @Insert
    void insertCurrency(CurrencyTwoDate currencyTwoDate);

    @Insert
    void insertCurrencies(List<CurrencyTwoDate> currencyTwoDateList);

    @Update
    void updateCurrency(CurrencyTwoDate currencyTwoDate);

    @Update
    void updateCurrencies(List<CurrencyTwoDate> currencyTwoDateList);

    @Delete
    void deleteCurrency(CurrencyTwoDate currencyTwoDate);

    @Delete
    void deleteCurrencies(List<CurrencyTwoDate> currencyTwoDateList);
}
