package com.example.contactus.feature.base;

import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class RvAdapter<T, E extends RvViewHolder<T>> extends RecyclerView.Adapter<E> implements Filterable {
    private List<T> items;
    private List<T> filteredList;
    private Filter filter;


    public List<T> getFilteredList() {
        return filteredList;
    }


    protected OnRvItemsClickListener<T> onRvItemsClickListener;


    public void setOnRvItemsClickListener(OnRvItemsClickListener<T> onRvItemsClickListener) {
        this.onRvItemsClickListener = onRvItemsClickListener;
    }

    public abstract Filter setFilter();
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public RvAdapter(List<T> items) {
        this.items = items;
    }
    public RvAdapter(){

    }
    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void deleteItem(T item,int position)
    {
        this.items.remove(item);
        notifyItemRemoved(position);
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull E holder, int position) {
        holder.bindData(items.size()>0?items.get(position):filteredList.get(position));
    }

    @Override
    public Filter getFilter() {
        return setFilter();
    }
}
