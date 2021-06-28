package com.ryfa.MVP.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.ryfa.MVP.R;


public class SnackBar {

    Context context;
    Snackbar snackbar;
    Snackbar.SnackbarLayout layout;
    View view;
    String messageText;
    String actionConfigText = "";
    String actionCancelText = "";
    boolean cancelAction = false;
    boolean canBeClose = false;
    private float x1, x2;
    static final int MIN_DISTANCE = 150;

    public static SnackBar make(Context context, View view, String messageText) {
        return new SnackBar(context, view, messageText);
    }

    public static SnackBar make(Context context, View view, String messageText, String actionConfigText) {
        return new SnackBar(context, view, messageText, actionConfigText, false);
    }

    public static SnackBar make(Context context, View view, String messageText, String actionConfigText, boolean canBeClose) {
        return new SnackBar(context, view, canBeClose, messageText, actionConfigText, false);
    }

    public static SnackBar make(Context context, View view, String messageText, String actionConfigText, String actionCancelText) {
        return new SnackBar(context, view, messageText, actionConfigText, actionCancelText, true);
    }

    public static SnackBar make(Context context, View view, String messageText, String actionConfigText, String actionCancelText, boolean canBeClose) {
        return new SnackBar(context, view, canBeClose, messageText, actionConfigText, actionCancelText);
    }

    private SnackBar(Context context, View view, String messageText, String actionConfigText, boolean cancelAction) {
        this.context = context;
        this.view = view;
        this.messageText = messageText;
        this.actionConfigText = actionConfigText;
        this.cancelAction = cancelAction;
    }

    private SnackBar(Context context, View view, boolean canBeClose, String messageText, String actionConfigText, boolean cancelAction) {
        this.context = context;
        this.view = view;
        this.messageText = messageText;
        this.actionConfigText = actionConfigText;
        this.cancelAction = cancelAction;
        this.canBeClose = canBeClose;
    }

    private SnackBar(Context context, View view, String messageText, String actionConfigText, String actionCancelText, boolean cancelAction) {
        this.context = context;
        this.view = view;
        this.messageText = messageText;
        this.cancelAction = cancelAction;
        this.actionConfigText = actionConfigText;
        this.actionCancelText = actionCancelText;
    }

    private SnackBar(Context context, View view, boolean canBeClose, String messageText, String actionConfigText, String actionCancelText) {
        this.context = context;
        this.view = view;
        this.messageText = messageText;
        this.canBeClose = canBeClose;
        this.actionConfigText = actionConfigText;
        this.actionCancelText = actionCancelText;
    }

    private SnackBar(Context context, View view, String messageText) {
        this.context = context;
        this.view = view;
        this.messageText = messageText;
    }

    public void showShort() {

        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);

        layout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackView = LayoutInflater.from(context).inflate(R.layout.layout_snackbar, null);

        ImageView imv_close = snackView.findViewById(R.id.imv_close);
        imv_close.setVisibility(canBeClose ? View.VISIBLE : View.GONE);
        imv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        TextView tv_message = snackView.findViewById(R.id.tv_message);
        tv_message.setText(messageText);

        TextView tv_action_config = snackView.findViewById(R.id.tv_action_config);
        tv_action_config.setVisibility(View.GONE);

        TextView tv_action_cancel = snackView.findViewById(R.id.tv_action_cancel);
        tv_action_cancel.setVisibility(View.GONE);

        layout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        layout.setPadding(0, 0, 0, 0);

        layout.addView(snackView, 0);
        snackbar.show();
    }

    public void showLong() {

        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        layout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackView = LayoutInflater.from(context).inflate(R.layout.layout_snackbar, null);

        ImageView imv_close = snackView.findViewById(R.id.imv_close);
        imv_close.setVisibility(canBeClose ? View.VISIBLE : View.GONE);
        imv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        TextView tv_message = snackView.findViewById(R.id.tv_message);
        tv_message.setText(messageText);

        TextView tv_action_config = snackView.findViewById(R.id.tv_action_config);
        tv_action_config.setVisibility(View.GONE);

        TextView tv_action_cancel = snackView.findViewById(R.id.tv_action_cancel);
        tv_action_cancel.setVisibility(View.GONE);

        layout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        layout.setPadding(0, 0, 0, 0);

        layout.addView(snackView, 0);
        snackbar.show();
    }

    public void showShort(final SetAction setAction) {

        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);

        layout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackView = LayoutInflater.from(context).inflate(R.layout.layout_snackbar, null);

        ImageView imv_close = snackView.findViewById(R.id.imv_close);
        imv_close.setVisibility(canBeClose ? View.VISIBLE : View.GONE);
        imv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        TextView tv_message = snackView.findViewById(R.id.tv_message);
        tv_message.setText(messageText);

        TextView tv_action_config = snackView.findViewById(R.id.tv_action_config);
        tv_action_config.setVisibility(View.GONE);

        TextView tv_action_cancel = snackView.findViewById(R.id.tv_action_cancel);
        tv_action_cancel.setVisibility(View.GONE);

        layout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        layout.setPadding(0, 0, 0, 0);

        layout.addView(snackView, 0);

        if (!actionConfigText.equals("")) {
            tv_action_config.setText(actionConfigText);
        }
        tv_action_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAction.setConfigAction();
                snackbar.dismiss();
            }
        });

        snackbar.show();

        snackbar.show();
    }

    public void showLong(final SetAction setAction) {

        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        layout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackView = LayoutInflater.from(context).inflate(R.layout.layout_snackbar, null);

        ImageView imv_close = snackView.findViewById(R.id.imv_close);
        imv_close.setVisibility(canBeClose ? View.VISIBLE : View.GONE);
        imv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        TextView tv_message = snackView.findViewById(R.id.tv_message);
        tv_message.setText(messageText);

        TextView tv_action_config = snackView.findViewById(R.id.tv_action_config);

        TextView tv_action_cancel = snackView.findViewById(R.id.tv_action_cancel);
        tv_action_cancel.setVisibility(cancelAction ? View.VISIBLE : View.GONE);
        tv_action_cancel.setText(actionCancelText);

        layout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        layout.setPadding(0, 0, 0, 0);

        layout.addView(snackView, 0);

        if (!actionConfigText.equals("")) {
            tv_action_config.setText(actionConfigText);
        }

        tv_action_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAction.setConfigAction();
                snackbar.dismiss();
            }
        });

        tv_action_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        setAction.setCancelAction();
        snackbar.show();

        snackbar.show();
    }

    public void showIndefinite(final SetAction setAction) {

        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);

        layout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackView = LayoutInflater.from(context).inflate(R.layout.layout_snackbar, null);

        ImageView imv_close = snackView.findViewById(R.id.imv_close);
        imv_close.setVisibility(canBeClose ? View.VISIBLE : View.GONE);
        imv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        TextView tv_message = snackView.findViewById(R.id.tv_message);
        tv_message.setText(messageText);

        TextView tv_action_config = snackView.findViewById(R.id.tv_action_config);

        TextView tv_action_cancel = snackView.findViewById(R.id.tv_action_cancel);
        if (actionCancelText != null && !actionCancelText.equals("")) {
            tv_action_cancel.setVisibility(View.VISIBLE);
            tv_action_cancel.setText(actionCancelText);
        } else{
            tv_action_cancel.setVisibility(View.GONE);
        }


            layout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        layout.setPadding(0, 0, 0, 0);

        layout.addView(snackView, 0);

        if (!actionConfigText.equals("")) {
            tv_action_config.setText(actionConfigText);
        }
        tv_action_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAction.setConfigAction();
                snackbar.dismiss();
            }
        });

        tv_action_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAction.setCancelAction();
                snackbar.dismiss();
            }
        });

        snackbar.show();
    }

    public void showIndefinite() {

        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);

        layout = (Snackbar.SnackbarLayout) snackbar.getView();

        View snackView = LayoutInflater.from(context).inflate(R.layout.layout_snackbar, null);

        ImageView imv_close = snackView.findViewById(R.id.imv_close);
        imv_close.setVisibility(canBeClose ? View.VISIBLE : View.GONE);
        imv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        TextView tv_message = snackView.findViewById(R.id.tv_message);
        tv_message.setText(messageText);

        TextView tv_action_config = snackView.findViewById(R.id.tv_action_config);

        TextView tv_action_cancel = snackView.findViewById(R.id.tv_action_cancel);
        if (actionCancelText != null && !actionCancelText.equals("")) {
            tv_action_cancel.setVisibility(View.VISIBLE);
            tv_action_cancel.setText(actionCancelText);
        } else{
            tv_action_cancel.setVisibility(View.GONE);
        }


            layout.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        layout.setPadding(0, 0, 0, 0);

        layout.addView(snackView, 0);

        if (!actionConfigText.equals("")) {
            tv_action_config.setText(actionConfigText);
        }
        tv_action_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        tv_action_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        snackbar.show();
    }

    public interface SetAction {
        void setConfigAction();

        void setCancelAction();
    }

}
