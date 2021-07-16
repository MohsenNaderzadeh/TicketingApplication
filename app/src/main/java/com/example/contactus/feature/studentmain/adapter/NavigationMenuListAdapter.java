package com.example.contactus.feature.studentmain.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.contactus.R;
import com.example.contactus.feature.base.RvAdapter;
import com.example.contactus.feature.base.RvViewHolder;
import com.example.contactus.feature.data.entities.MenuItem;

public class NavigationMenuListAdapter extends RvAdapter<MenuItem, NavigationMenuListAdapter.NavigationMenuListViewHolder> {


    @Override
    public Filter setFilter() {
        return null;
    }

    @NonNull
    @Override
    public NavigationMenuListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NavigationMenuListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_navigation, parent, false));
    }

    public class NavigationMenuListViewHolder extends RvViewHolder<MenuItem> {
        private final ImageView item_main_navigation_item_icon_Iv;
        private final TextView item_main_navigation_item_title_tv;

        public NavigationMenuListViewHolder(@NonNull View itemView) {
            super(itemView);
            item_main_navigation_item_icon_Iv = itemView.findViewById(R.id.item_main_navigation_item_icon_Iv);
            item_main_navigation_item_title_tv = itemView.findViewById(R.id.item_main_navigation_item_title_tv);

        }

        @Override
        public void bindData(MenuItem item) {
            item_main_navigation_item_icon_Iv.setImageResource(item.getItemIcon());
            item_main_navigation_item_title_tv.setText(item.getItemText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRvItemsClickListener.OnItemClicked(item, getAdapterPosition());
                }
            });
        }
    }
}
