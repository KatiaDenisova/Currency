package com.example.currency.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.R;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.network.GlobalRetrofit;

import java.util.List;

public class TestActivity extends Activity implements ViewCurrencyIntef {

    private String TAG = "Test Activity";
    private TuningCurAdapter tuningCurAdapter;
    private RecyclerView currencyList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        currencyList = findViewById(R.id.rv_currencyTuning);
        currencyList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void showToast(String str) {

    }

    @Override
    public void displayCurrencies(List<CurrencyTwoDate> listCur) {
        if (listCur != null) {
            tuningCurAdapter = new TuningCurAdapter(listCur);
            currencyList.setAdapter(tuningCurAdapter);
        } else {
            Log.d(TAG, "Currencies response null");
        }
    }

    @Override
    public void displayError(String str) {

    }
}
