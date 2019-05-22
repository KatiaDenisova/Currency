package com.example.currency;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.presenter.PresenterCurrency;
import com.example.currency.view.TestActivity;
import com.example.currency.view.CurrenciesAdapter;
import com.example.currency.view.ViewCurrencyIntef;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewCurrencyIntef {
    private ImageButton tuning;
    private Toolbar toolbar;
    private String TAG = "Main Activity";
    private RecyclerView currencyList;
    private CurrenciesAdapter currencyAdapter;
    private PresenterCurrency presenterCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currencyList = findViewById(R.id.rv_currency);


        MainApp mainApp = (MainApp) getApplicationContext();



        setMVP();
        setUpViews();
        getCurrenciesList(mainApp);
        tuning = findViewById(R.id.btInTuning);

        tuning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });

    }

    private void setMVP() {
        presenterCurrency = new PresenterCurrency(this);
    }

    private void setUpViews() {
        currencyList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getCurrenciesList(MainApp mainApp) {
        presenterCurrency.getCurrencies(mainApp);
    }

    @Override
    public void showToast(String str) {

    }

    @Override
    public void displayCurrencies(List<CurrencyTwoDate> listCur) {

        if (listCur != null) {
            currencyAdapter = new CurrenciesAdapter(listCur);
            currencyList.setAdapter(currencyAdapter);
        } else {
            Log.d(TAG, "Currencies response null");
        }
    }

    @Override
    public void displayError(String str) {
        showToast(str);
    }
}

