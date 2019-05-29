package com.example.currency.view;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.R;
import com.example.currency.model.CurrencyTwoDate;

import java.util.Collections;
import java.util.List;

public class TuningCurAdapter extends RecyclerView.Adapter<TuningCurAdapter.CurrencyViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {

    private List<CurrencyTwoDate> currencyTwoDateList;
    private OnItemCheckedChangeListener onItemCheckedChangeListener;

    public TuningCurAdapter(List<CurrencyTwoDate> currencyTwoDateList, OnItemCheckedChangeListener onItemCheckedChangeListener) {
        this.currencyTwoDateList = currencyTwoDateList;
        this.onItemCheckedChangeListener = onItemCheckedChangeListener;
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
                int adapterPosition = holder.getAdapterPosition();
                if (onItemCheckedChangeListener != null) {
                    onItemCheckedChangeListener.onItemCheckedChanged(adapterPosition, isChecked);
                }
            }
        });
    }

    public CurrencyTwoDate getItem(int position) {
        return currencyTwoDateList.get(position);
    }

    public void updateCurAdapter() {

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

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(currencyTwoDateList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(currencyTwoDateList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(CurrencyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.YELLOW);
    }

    @Override
    public void onRowClear(CurrencyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
    }

    public class  CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView nameCurrency;
        TextView charCode;
        SwitchCompat aSwitch;
        View rowView;
        ImageView dragImg;


        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            rowView = itemView;
            nameCurrency = itemView.findViewById(R.id.tv_nameTuning);
            charCode = itemView.findViewById(R.id.tv_tuningCur);
            aSwitch = itemView.findViewById(R.id.switchTuning);
            dragImg = itemView.findViewById(R.id.imageView);


        }

        void bind(CurrencyTwoDate currencyTwoDate) {
            nameCurrency.setText(String.format("%s", currencyTwoDate.getName()));
            charCode.setText(String.format("%s",currencyTwoDate.getCharCode()));
            aSwitch.setChecked(currencyTwoDate.isShow());
        }
    }

    interface OnItemCheckedChangeListener {
        void onItemCheckedChanged(int position, boolean isChecked);
    }

}
