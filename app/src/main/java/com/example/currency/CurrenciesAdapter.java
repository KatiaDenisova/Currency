package com.example.currency;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder> {
    private List<Currency> currencyList;

    public CurrenciesAdapter(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForRateList = R.layout.currency_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForRateList, viewGroup, false);

        CurrencyViewHolder currencyViewHolder = new CurrencyViewHolder(view);
        return currencyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder currencyViewHolder, int i) {
        currencyViewHolder.bind(currencyList.get(i));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView nameCurrency;
        TextView charCode;
        TextView rate;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCurrency = itemView.findViewById(R.id.tv_nameCurrency);
            charCode = itemView.findViewById(R.id.tv_charCode);
            rate = itemView.findViewById(R.id.tv_rate);
        }

        void bind(Currency currency){
            nameCurrency.setText(String.format("%s", currency.getName()));
            charCode.setText(String.format("%s",currency.getCharCode()));
            rate.setText(String.format("%f", currency.getRateN()));
        }
    }
}