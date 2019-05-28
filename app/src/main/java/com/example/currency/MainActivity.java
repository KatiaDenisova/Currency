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

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

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
//        getCurrenciesList(mainApp);
//        getCurrenciesByDb(getDaoCurrency());
//        showCur();
//        displayCurrencies(getCurrencies());
        updateData();



//        tuning = findViewById(R.id.btInTuning);
//
//        tuning.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, TestActivity.class));
//            }
//        });

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

    private void getCurrenciesByDb(CurrencyDao dao) {
        presenterCurrency.getCurrenciesByDb(getDaoCurrency());
    }


    private List<CurrencyTwoDate> getCurrencies() {
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
//        List<CurrencyTwoDate> list = currencyDao.getCurrenciesByShow(true);
        List<CurrencyTwoDate> list = currencyDao.getCurrenciesByShow(true);
        return list;
    }

    private void updateData(){

        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        currencyDao.getCurrenciesByShowFlowable(true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CurrencyTwoDate>>() {
                    @Override
                    public void accept(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {
                        currencyAdapter = new CurrenciesAdapter(currencyTwoDateList);
                        currencyList.setAdapter(currencyAdapter);
                    }
                });
    }

    private void showCur() {
        currencyAdapter = new CurrenciesAdapter(getDaoCurrency().getCurrenciesByShow(true));
        currencyList.setAdapter(currencyAdapter);
    }

    private void displayCurrencies(List<CurrencyTwoDate> listCur) {
        if (listCur != null) {
            currencyAdapter = new CurrenciesAdapter(listCur);
            currencyList.setAdapter(currencyAdapter);
        } else {
            Log.d(TAG, "Currencies response null");
        }
    }

    private CurrencyDao getDaoCurrency(){
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        return currencyDao;
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

