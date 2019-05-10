package com.example.currency;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RatesAPI  {


    @GET("XmlExRates.aspx?ondate=")
    Single<Currencies> getCurrencies(@Query("date") String date);
}
