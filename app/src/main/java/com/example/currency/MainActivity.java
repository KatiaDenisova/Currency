package com.example.currency;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.model.CurrencyDao;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.model.DatabaseApp;
import com.example.currency.presenter.PresenterCurrency;
import com.example.currency.view.TestActivity;
import com.example.currency.view.CurrenciesAdapter;
import com.example.currency.view.ViewCurrencyIntef;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String TAG = "Main Activity";
    private RecyclerView currencyList;
    private CurrenciesAdapter currencyAdapter;
    private PresenterCurrency presenterCurrency;
    private ImageView tun;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }


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
        updateData();

    }

    private void setMVP() {
        presenterCurrency = new PresenterCurrency();
    }

    private void setUpViews() {
        currencyList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getCurrenciesList(MainApp mainApp) {
        presenterCurrency.getCurrencies(mainApp);
    }



    private List<CurrencyTwoDate> getCurrencies() {
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        List<CurrencyTwoDate> list = currencyDao.getCurrenciesByShow(true);
        return list;
    }

    private void updateData(){

        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        currencyDao.getCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<List<CurrencyTwoDate>>() {
                    @Override
                    public boolean test(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {
                        return !currencyTwoDateList.contains(currencyDao.getCurrenciesByShow(true));
                    }
                })
                .map(new Function<List<CurrencyTwoDate>, List<CurrencyTwoDate>>() {
                    @Override
                    public List<CurrencyTwoDate> apply(List<CurrencyTwoDate> currencyTwoDates) throws Exception {
                        Collections.sort(currencyTwoDates, CurrencyTwoDate.compareByPlace);
                        return currencyTwoDates;
                    }
                })
                .subscribe(new Consumer<List<CurrencyTwoDate>>() {
                    @Override
                    public void accept(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {
                        currencyAdapter = new CurrenciesAdapter(currencyTwoDateList);
                        currencyList.setAdapter(currencyAdapter);
                    }
                });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_tuning) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        }
        return true;
    }
}

