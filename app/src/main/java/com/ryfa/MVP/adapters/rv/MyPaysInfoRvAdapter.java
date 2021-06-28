package com.ryfa.MVP.adapters.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryfa.MVP.databinding.LayoutRvMyPaysInfoItemBinding;
import com.ryfa.MVP.general.PersianCalendar;
import com.ryfa.MVP.general.SetLocale;
import com.ryfa.MVP.models.PayModel;

import java.util.ArrayList;

public class MyPaysInfoRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    ArrayList<PayModel.Pay> list;

    public MyPaysInfoRvAdapter(Context context, ArrayList<PayModel.Pay> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutRvMyPaysInfoItemBinding binding = LayoutRvMyPaysInfoItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        PayModel.Pay pay = list.get(position);

        viewHolder.itemBinding.tvDay.setText(PersianCalendar.getInstance(pay.getDate()).getPersianWeekDayStr());
        viewHolder.itemBinding.tvCreateAt.setText(PersianCalendar.getInstance(pay.getDate()).getIranianDate());
        viewHolder.itemBinding.tvPrice.setText(SetLocale.set(pay.getPrice(),",") + " تومان ");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutRvMyPaysInfoItemBinding itemBinding;

        public ViewHolder(LayoutRvMyPaysInfoItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
        }
    }

}
