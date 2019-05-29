package com.example.currency.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.currency.R;
import com.example.currency.model.CurrencyTwoDate;

import java.util.List;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder> {
    private List<CurrencyTwoDate> currencyTwoDateList;

    public CurrenciesAdapter(List<CurrencyTwoDate> currencyTwoDateList) {
        this.currencyTwoDateList = currencyTwoDateList;
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
        currencyViewHolder.bind(currencyTwoDateList.get(i));

    }

    @Override
    public int getItemCount() {
        return currencyTwoDateList.size();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView nameCurrency;
        TextView charCode;
        TextView rateToday;
        TextView rateYesterday;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCurrency = itemView.findViewById(R.id.tv_nameCurrency);
            charCode = itemView.findViewById(R.id.tv_charCode);
            rateToday = itemView.findViewById(R.id.tv_rate);
            rateYesterday = itemView.findViewById(R.id.tv_rateYesterday);
        }

        void bind(CurrencyTwoDate currencyTwoDate){
            nameCurrency.setText(String.format("%s", currencyTwoDate.getName()));
            charCode.setText(String.format("%s",currencyTwoDate.getCharCode()));
            rateToday.setText(String.format("%f", currencyTwoDate.getRateToday()));
            rateYesterday.setText(String.format("%f",currencyTwoDate.getRateYesterday()));
        }
    }
}