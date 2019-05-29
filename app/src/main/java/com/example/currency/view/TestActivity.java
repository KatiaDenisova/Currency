package com.example.currency.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.SwitchCompat;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.MainApp;
import com.example.currency.R;
import com.example.currency.model.CurrencyDao;
import com.example.currency.model.CurrencyTwoDate;
import com.example.currency.model.DatabaseApp;

import java.util.List;


public class TestActivity extends AppCompatActivity {
    private TuningCurAdapter tuningCurAdapter;
    private RecyclerView currencyList;
    private SwitchCompat switchCompat;
    private Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.savetuning, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);



        currencyList = findViewById(R.id.rv_currencyTuning);
        currencyList.setLayoutManager(new LinearLayoutManager(this));


        switchCompat = findViewById(R.id.switchTuning);
        showCurrencies();
//        getDaoCurrency().getCurrenciesByShowFlowable(true)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<CurrencyTwoDate>>() {
//                    @Override
//                    public void accept(List<CurrencyTwoDate> currencyTwoDateList) throws Exception {
//
//
//                    }
//                });



    }


    private CurrencyDao getDaoCurrency(){
        DatabaseApp databaseApp = MainApp.getInstance().getDatabaseApp();
        CurrencyDao currencyDao = databaseApp.currencyDao();
        return currencyDao;
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
//        , new TuningCurAdapter.OnItemTouchedListener() {
//            @Override
//            public void onItemTouchedChangePlace(int fromHolderPos) {
//                Log.d("TAG", "POS: " + fromHolderPos);
////
////                CurrencyTwoDate item =  getDaoCurrency().getCurrencyByPlace(tuningCurAdapter.getItemPlace(fromHolderPos));
////                item.setPlace(fromHolderPos);
////                getDaoCurrency().updateCurrency(item);
//            }
//        });
        ItemTouchHelper.Callback callback = new ItemMoveCallback(tuningCurAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(currencyList);
        currencyList.setAdapter(tuningCurAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            List<CurrencyTwoDate> curList = tuningCurAdapter.getItems();
            for (int i = 0; i < curList.size(); i++) {
                curList.get(i).setPlace(i);
            }
            getDaoCurrency().updateCurrencies(curList);
        }
        return true;
    }

}
