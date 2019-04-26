package com.example.currency;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RatesAPI  {
//    @GET("XmlExRates.aspx?ondate=04.25.2019")
//    Call<Currencies> getCurrencies();

    @GET("XmlExRates.aspx?ondate=04.25.2019")
    Call<String> getRates();

    @GET("XmlExRates.aspx?ondate=04.26.2019")
    Single<Currencies> currencies();
}
