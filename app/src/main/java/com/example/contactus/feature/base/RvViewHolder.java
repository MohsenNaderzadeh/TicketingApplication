package com.example.contactus.feature.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  abstract class RvViewHolder<T> extends RecyclerView.ViewHolder {
    public RvViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(T item);


}
