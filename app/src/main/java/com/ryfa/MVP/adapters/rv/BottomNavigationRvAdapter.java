package com.ryfa.MVP.adapters.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ryfa.MVP.R;
import com.ryfa.MVP.models.BottomNavigationModel;
import com.ryfa.MVP.widgets.CircleImageView;

import java.util.ArrayList;


public class BottomNavigationRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<BottomNavigationModel> bottomNavigationList;
    OnItemClickListener onItemClickListener;

    public BottomNavigationRvAdapter(Context context, ArrayList<BottomNavigationModel> bottomNavigationList) {
        this.context = context;
        this.bottomNavigationList = bottomNavigationList;
    }

    public BottomNavigationRvAdapter() {
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_rv_bottom_navigation_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        BottomNavigationModel bottomNavigation = bottomNavigationList.get(i);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.img_bottomNavigationIcon.setImageResource(bottomNavigation.getIcon());
        viewHolder.tv_title.setText(bottomNavigation.getText());

        if (bottomNavigation.isSelect()) {
            if (bottomNavigation.getIconSelected() != 0) {
                viewHolder.img_bottomNavigationIcon.setImageResource(bottomNavigation.getIconSelected());
            }
            viewHolder.img_bottomNavigationIcon.setColorFilter(context.getResources().getColor(R.color.colorIndigo));
            viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.colorIndigo));
        } else {
            if (bottomNavigation.getIconSelected() != 0) {
                viewHolder.img_bottomNavigationIcon.setImageResource(bottomNavigation.getIcon());
            }
            viewHolder.img_bottomNavigationIcon.setColorFilter(context.getResources().getColor(R.color.colorGrey_600));
            viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.colorGrey_600));
        }
    }

    @Override
    public int getItemCount() {
        return bottomNavigationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_bottomNavigationIcon;
        CircleImageView img_bnShadow;
        TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_bottomNavigationIcon = itemView.findViewById(R.id.img_bottomNavigationIcon);
            img_bnShadow = itemView.findViewById(R.id.img_bnShadow);
            tv_title = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.ItemClick(getAdapterPosition(), bottomNavigationList.get(getAdapterPosition()));
                }
            });
        }
    }


    public interface OnItemClickListener {
        void ItemClick(int position, BottomNavigationModel bottomNavigation);
    }

    public BottomNavigationRvAdapter setOnItemClickListener(OnItemClickListener onTabItemListener) {
        this.onItemClickListener = onTabItemListener;
        return this;
    }
}

