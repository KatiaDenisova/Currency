package com.example.currency.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "DailyExRates")
public class Currencies {

    @Attribute(name="Date")
    public String date;

    @ElementList(inline = true)
    public List<Currency> currencies;
    public List<Currency> getCurrencies() {
        return currencies;
    }

    @Override
    public String toString() {
        return "Currencies{" +
                "date='" + date + '\'' +
                ", currencies=" + currencies +
                '}';
    }
}