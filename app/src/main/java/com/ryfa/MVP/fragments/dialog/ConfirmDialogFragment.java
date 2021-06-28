package com.ryfa.MVP.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ryfa.MVP.databinding.FragmentDialogConfirmBinding;


public class ConfirmDialogFragment extends DialogFragment {

    public static final String TAG = "ConfirmDialogFragment";
    FragmentDialogConfirmBinding binding;
    OnItemClickListener onItemClickListener;
    String title, confirm, cancel,discription;

    public static ConfirmDialogFragment newInstance(String title,String discription, String confirm, String cancel) {
        Bundle args = new Bundle();
        ConfirmDialogFragment fragment = new ConfirmDialogFragment(title,discription, confirm, cancel);
        fragment.setArguments(args);
        return fragment;
    }

    public ConfirmDialogFragment(String title, String discription, String confirm, String cancel) {
        this.title = title;
        this.confirm = confirm;
        this.cancel = cancel;
        this.discription = discription;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentDialogConfirmBinding.inflate(LayoutInflater.from(getContext()), null, false);

        binding.tvTitle.setText(title);
        binding.btnConfirm.setText(confirm);
        binding.btnCansel.setText(cancel);
        binding.tvDiscription.setText(discription);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onConfirmClick(ConfirmDialogFragment.this);
            }
        });

        binding.btnCansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onCancelClick(ConfirmDialogFragment.this);
            }
        });

        builder.setView(binding.getRoot());
        return builder.create();
    }


    public interface OnItemClickListener {
        void onConfirmClick(ConfirmDialogFragment dialogFragment);

        void onCancelClick(ConfirmDialogFragment dialogFragment);
    }

    public ConfirmDialogFragment setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }
}
