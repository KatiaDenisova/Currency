package com.example.currency.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name ="Currency")
public class Currency {
    @Attribute(name="Id")
    private int id;

    @Element(name = "CharCode")
    private String charCode;

    @Element(name = "Name")
    private String name;

    @Element(name = "Rate")
    private double rateN;

    @Element(name = "NumCode")
    private int numCode;

    @Element(name = "Scale")
    private int scale;

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

    public int getId() {
        return id;
    }

    public String getCharCode() {
        return charCode;
    }

    public String getName() {
        return name;
    }

    public double getRateN() {
        return rateN;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRateN(double rateN) {
        this.rateN = rateN;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                ", rateN=" + rateN +
                ", numCode=" + numCode +
                ", scale=" + scale +
                '}';
    }
}