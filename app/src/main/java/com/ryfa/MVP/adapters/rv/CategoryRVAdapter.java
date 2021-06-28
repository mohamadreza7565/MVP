package com.ryfa.MVP.adapters.rv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryfa.MVP.databinding.LayoutRvCategoryItemBinding;
import com.ryfa.MVP.models.CategoryModel;

import java.util.ArrayList;


public class CategoryRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private OnItemClickListener onItemClickListener;
    private ArrayList<CategoryModel> list;
    private int typeView;

    public CategoryRVAdapter(Context context, ArrayList<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutRvCategoryItemBinding itemsBinding = LayoutRvCategoryItemBinding.inflate(LayoutInflater.from(context), viewGroup, false);
        return new ViewHolder(itemsBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        CategoryModel category = list.get(position);

        viewHolder.itemBinding.tvCategory.setText(category.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutRvCategoryItemBinding itemBinding;

        public ViewHolder(@NonNull LayoutRvCategoryItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;

            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(),list.get(getAdapterPosition()));
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, CategoryModel model);
    }

    public CategoryRVAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

}
