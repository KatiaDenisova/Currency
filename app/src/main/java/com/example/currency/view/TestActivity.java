package com.example.currency.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.MainApp;
import com.example.currency.R;
import com.example.currency.model.CurrencyDao;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.model.DatabaseApp;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class TestActivity extends Activity {
    private Toolbar toolbar;
    private String TAG = "Test Activity";
    private TuningCurAdapter tuningCurAdapter;
    private RecyclerView currencyList;
    private SwitchCompat switchCompat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = findViewById(R.id.toolbar2);

        setContentView(R.layout.activity_test);
        currencyList = findViewById(R.id.rv_currencyTuning);
        currencyList.setLayoutManager(new LinearLayoutManager(this));

//        showCurrencies(getCurrencies(getDaoCurrency()));

        switchCompat = findViewById(R.id.switchTuning);
        showCurrencies();
        getDaoCurrency().getCurrencies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CurrencyTwoDate>>() {
                    @Override
                    public void accept(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {

                    }
                });



    }


    private CurrencyDao getDaoCurrency(){
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        return currencyDao;
    }
//    private List<CurrencyTwoDate> getCurrencies(CurrencyDao currencyDao) {
//        List<CurrencyTwoDate> list = currencyDao.getCurrencies();
//        return list;
//    }

    private CurrencyTwoDate getCurrencyByCharCode(CurrencyDao currencyDao, String charCode) {
       CurrencyTwoDate cur = currencyDao.getCurrencyByCharCode(charCode);
        return cur;
    }

    private void showCurrencies() {
        tuningCurAdapter = new TuningCurAdapter(getDaoCurrency().getCurrenciesList(), new TuningCurAdapter.OnItemCheckedChangeListener() {
            @Override
            public void onItemCheckedChanged(int position, boolean isChecked) {
                CurrencyTwoDate item = tuningCurAdapter.getItem(position);
                item.setShow(isChecked);
                getDaoCurrency().updateCurrency(item);
            }
        });
        currencyList.setAdapter(tuningCurAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
