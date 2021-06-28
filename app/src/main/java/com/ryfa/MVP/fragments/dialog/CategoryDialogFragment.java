package com.ryfa.MVP.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ryfa.MVP.adapters.rv.CategoryRVAdapter;
import com.ryfa.MVP.databinding.FragmentDialogCategoryBinding;
import com.ryfa.MVP.interfaces.OnListResultCallBak;
import com.ryfa.MVP.models.CategoryModel;

import java.util.ArrayList;

public class CategoryDialogFragment extends DialogFragment {

    public static final String TAG = "CategoryDialogFra";
    FragmentDialogCategoryBinding binding;
    OnItemClickListener onItemClickListener;

    public static CategoryDialogFragment newInstance() {
        Bundle args = new Bundle();
        CategoryDialogFragment fragment = new CategoryDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentDialogCategoryBinding.inflate(LayoutInflater.from(getContext()), null, false);

        CategoryModel.getCategories(getContext(), new OnListResultCallBak<CategoryModel>() {
            @Override
            public void onStart(LoadingDialogFragment dialog) {

            }

            @Override
            public void onResponse(ArrayList<CategoryModel> list, LoadingDialogFragment loadingDialog) {
                binding.rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvCategory.setAdapter(new CategoryRVAdapter(getContext(),list).setOnItemClickListener(new CategoryRVAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, CategoryModel model) {
                        onItemClickListener.onItemClick(position,model,CategoryDialogFragment.this);
                    }
                }));

            }

            @Override
            public void onFailure(int statusCode, LoadingDialogFragment loadingDialog) {

            }
        });

        builder.setView(binding.getRoot());
        return builder.create();
    }


    public interface OnItemClickListener {
        void onItemClick(int position, CategoryModel category, CategoryDialogFragment dialogFragment);
    }

    public CategoryDialogFragment setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }
}
