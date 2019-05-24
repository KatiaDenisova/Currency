package com.example.currency.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.R;
import com.example.currency.model.CurrencyTwoDate;

import java.util.List;

public class TuningCurAdapter extends RecyclerView.Adapter<TuningCurAdapter.CurrencyViewHolder> {

    private List<CurrencyTwoDate> currencyTwoDateList;
    private OnItemCheckedChangeListener onItemCheckedChangeListener;


    public TuningCurAdapter(List<CurrencyTwoDate> currencyTwoDateList) {
        this.currencyTwoDateList = currencyTwoDateList;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForTuning = R.layout.tuning_list_cur;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForTuning,parent,false);

        CurrencyViewHolder currencyViewHplder = new CurrencyViewHolder(view);
        return currencyViewHplder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {

        holder.bind(currencyTwoDateList.get(position));
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int adapterPositin = holder.getAdapterPosition();
                CurrencyTwoDate currencyTwoDate = currencyTwoDateList.get(adapterPositin);
                currencyTwoDate.setShow(isChecked);
                currencyTwoDateList.set(adapterPositin, currencyTwoDate);
            }
        });
    }

    @Override
    public int getItemCount() {
        return currencyTwoDateList.size();

    }

    @Override
    public void onViewRecycled(@NonNull CurrencyViewHolder holder) {
        super.onViewRecycled(holder);
        holder.aSwitch.setOnCheckedChangeListener(null);
    }

    public void setOnItemCheckedChangeListener(OnItemCheckedChangeListener onItemCheckedChangeListener) {
        this.onItemCheckedChangeListener = onItemCheckedChangeListener;
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView nameCurrency;
        TextView charCode;
        SwitchCompat aSwitch;
        ImageButton imageButton;
        Boolean isTouched = true;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCurrency = itemView.findViewById(R.id.tv_nameTuning);
            charCode = itemView.findViewById(R.id.tv_tuningCur);
            aSwitch = itemView.findViewById(R.id.switchTuning);
            imageButton = itemView.findViewById(R.id.imgBut);

            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isTouched) {
                        isTouched = true;
                        if(isChecked!=true){
//                            click.posClicked((short)getAdapterPosition());
                        }
                    }
                }
            });
        }

        void bind(CurrencyTwoDate currencyTwoDate) {
            nameCurrency.setText(String.format("%s", currencyTwoDate.getName()));
            charCode.setText(String.format("%s",currencyTwoDate.getCharCode()));
        }
    }

    interface OnItemCheckedChangeListener {
        void onItemCheckedChanged(int position, boolean isChecked);
    }

}
