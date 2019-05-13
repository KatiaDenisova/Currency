package com.example.currency.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "CurrencyTwoDate")
public class CurrencyTwoDate {

    private int id;
    @PrimaryKey
    private String charCode;
    private String name;
    private double rateToday;
    private double rateYesterday;
    private int numCode;
    private int scale;

    @Ignore
    public CurrencyTwoDate() {
    }

    @Ignore
    public CurrencyTwoDate(int id, String charCode, String name, double rateToday, double rateYesterday, int numCode, int scale) {
        this.id = id;
        this.charCode = charCode;
        this.name = name;
        this.rateToday = rateToday;
        this.rateYesterday = rateYesterday;
        this.numCode = numCode;
        this.scale = scale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public double getRateToday() {
        return rateToday;
    }

    public void setRateToday(double rateToday) {
        this.rateToday = rateToday;
    }

    public double getRateYesterday() {
        return rateYesterday;
    }

    public void setRateYesterday(double rateYesterday) {
        this.rateYesterday = rateYesterday;
    }

    @Override
    public String toString() {
        return "CurrencyTwoDate{" +
                "id=" + id +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                ", rateToday=" + rateToday +
                ", rateYesterday=" + rateYesterday +
                ", numCode=" + numCode +
                ", scale=" + scale +
                '}';
    }

    public static Comparator<CurrencyTwoDate> compareByChareChode = new Comparator<CurrencyTwoDate>() {
        @Override
        public int compare(CurrencyTwoDate o1, CurrencyTwoDate o2) {
            return o1.charCode.compareTo(o2.charCode);
        }
    };


}
