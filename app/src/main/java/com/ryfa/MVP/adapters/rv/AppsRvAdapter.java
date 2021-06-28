package com.ryfa.MVP.adapters.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryfa.MVP.databinding.LayoutRvAppItemBinding;
import com.ryfa.MVP.fragments.dialog.LoadingDialogFragment;
import com.ryfa.MVP.general.SetLocale;
import com.ryfa.MVP.interfaces.OnListResultCallBak;
import com.ryfa.MVP.models.AppModel;
import com.ryfa.MVP.models.CategoryModel;

import java.util.ArrayList;

public class AppsRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<AppModel.App> list;
    OnItemClickListener onItemClickListener;

    public AppsRvAdapter(Context context, ArrayList<AppModel.App> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutRvAppItemBinding itemsBinding = LayoutRvAppItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(itemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AppModel.App app = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.itemBinding.tvAppName.setText(app.getName());
        viewHolder.itemBinding.tvPrice.setText(SetLocale.set(app.getPrice(), ",") + " تومان");

        viewHolder.itemBinding.btnPay.setVisibility(app.getPayed() == 1 ? View.GONE : View.VISIBLE);

        CategoryModel.getCategories(context, new OnListResultCallBak<CategoryModel>() {
            @Override
            public void onStart(LoadingDialogFragment dialog) {

            }

            @Override
            public void onResponse(ArrayList<CategoryModel> list, LoadingDialogFragment loadingDialog) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == app.getCategory())
                        viewHolder.itemBinding.tvCategory.setText(list.get(i).getName());
                }
            }

            @Override
            public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutRvAppItemBinding itemBinding;

        public ViewHolder(@NonNull LayoutRvAppItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;

            itemBinding.btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onPayClick(getAdapterPosition(), list.get(getAdapterPosition()));
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onPayClick(int position, AppModel.App app);
    }

    public AppsRvAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public void addItem(AppModel.App app){
        list.add(app);
        notifyDataSetChanged();
    }
}
