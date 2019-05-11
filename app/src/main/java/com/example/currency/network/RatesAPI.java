package com.example.currency.network;

import com.example.currency.model.Currencies;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RatesAPI  {


    @GET("XmlExRates.aspx?ondate=")
    Single<Currencies> getCurrencies(@Query("date") String date);
}
