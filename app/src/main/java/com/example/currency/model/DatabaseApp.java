package com.example.currency.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CurrencyTwoDate.class}, version = 1)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract CurrencyDao currencyDao();

}
