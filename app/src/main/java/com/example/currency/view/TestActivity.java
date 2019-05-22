package com.example.currency.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.MainApp;
import com.example.currency.R;
import com.example.currency.model.CurrencyDao;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.model.DatabaseApp;

import java.util.List;

public class TestActivity extends Activity {

    private String TAG = "Test Activity";
    private TuningCurAdapter tuningCurAdapter;
    private RecyclerView currencyList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        currencyList = findViewById(R.id.rv_currencyTuning);
        currencyList.setLayoutManager(new LinearLayoutManager(this));

        showCurrencies();

    }


    private void showCurrencies() {
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        List<CurrencyTwoDate> list = currencyDao.getCurrencies();
            tuningCurAdapter = new TuningCurAdapter(list);
            currencyList.setAdapter(tuningCurAdapter);

    }
}
