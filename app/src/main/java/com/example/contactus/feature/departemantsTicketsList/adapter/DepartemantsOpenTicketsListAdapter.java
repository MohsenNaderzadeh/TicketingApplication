package com.example.contactus.feature.departemantsTicketsList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.contactus.R;
import com.example.contactus.feature.base.RvAdapter;
import com.example.contactus.feature.base.RvViewHolder;
import com.example.contactus.feature.data.entities.TicketInfo;

import java.util.ArrayList;
import java.util.List;


public class DepartemantsOpenTicketsListAdapter extends RvAdapter<TicketInfo, DepartemantsOpenTicketsListAdapter.TicketListViewHolder> {
    
    private final List<TicketInfo> filteredList = new ArrayList<>();
    private List<TicketInfo> mainList = new ArrayList<>();
    private Context context;
    private boolean isSearchBoxEmpty = false;
    private OnSearchCallBack onSearchCallBack;
    
    public DepartemantsOpenTicketsListAdapter(List<TicketInfo> items) {
        super(items);
        this.mainList = items;
    }
    
    
    public DepartemantsOpenTicketsListAdapter(Context context) {
        this.context = context;
    }
    
    public List<TicketInfo> getFilteredList() {
        return filteredList;
    }
    
    @Override
    public void setItems(List<TicketInfo> items) {
        super.setItems(items);
        this.mainList = items;
    }
    
    public List<TicketInfo> getMainList() {
        return mainList;
    }
    
    @NonNull
    @Override
    public TicketListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tickets_list, parent, false));
    }
    
    @Override
    public Filter setFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if (charSequence.toString().isEmpty()) {
                    filteredList.clear();
                    
                    isSearchBoxEmpty = true;
                    
                    
                } else {
                    filteredList.clear();
                    for (TicketInfo ticketResponse : mainList) {
                        if (ticketResponse.getTicketTitle().contains(charSequence.toString())) {
                            if (!filteredList.contains(ticketResponse)) {
                                filteredList.add(ticketResponse);
                                
                            }
                        }
                    }
                    
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterResults;
                return filterResults;
            }
            
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filteredList.size() > 0) {
                    if (onSearchCallBack != null) {
                        onSearchCallBack.searchHasResult();
                    }
                    DepartemantsOpenTicketsListAdapter.super.setItems(filteredList);
                } else {
                    if (onSearchCallBack != null) {
                        if (!isSearchBoxEmpty) {
                            onSearchCallBack.searchHasNoResult();
                        } else {
                            onSearchCallBack.searchBoxNoValue();
                        }
                    }
                }
            }
        };
        
    }
    
    public void setOnSearchCallBack(OnSearchCallBack onSearchCallBack) {
        this.onSearchCallBack = onSearchCallBack;
    }
    
    public interface OnSearchCallBack {
        void searchHasNoResult();
        
        void searchHasResult();
        
        void searchBoxNoValue();
    }
    
    public class TicketListViewHolder extends RvViewHolder<TicketInfo> {
        private final TextView ticket_short_title_tv;
        private final TextView ticket_title;
        private final TextView ticket_desc_tv;
        private final View ticket_card;
        private final TextView ticket_status_tv;
        private final TextView ticket_status_value_tv;
        private final TextView sender_type_tv;
        
        public TicketListViewHolder(@NonNull View itemView) {
            super(itemView);
            ticket_short_title_tv = itemView.findViewById(R.id.ticket_short_title_tv);
            ticket_title = itemView.findViewById(R.id.ticket_title);
            ticket_desc_tv = itemView.findViewById(R.id.ticket_desc_tv);
            ticket_card = itemView.findViewById(R.id.ticket_card);
            ticket_status_tv = itemView.findViewById(R.id.ticket_status_tv);
            ticket_status_value_tv = itemView.findViewById(R.id.ticket_status_value_tv);
            sender_type_tv = itemView.findViewById(R.id.sender_type_tv);
        }
        
        @Override
        public void bindData(TicketInfo item) {
            ticket_short_title_tv.setText(item.getTicketTitle().substring(0, 1));
            ticket_title.setText(item.getTicketTitle());
            ticket_desc_tv.setText(item.getTicketLastMessage().getMessageText());
            ticket_status_value_tv.setText(getTicketStatus(item.getTicketStatus(), ticket_status_value_tv));
            ticket_card.setOnClickListener(view -> {
                if (onRvItemsClickListener != null) {
                    onRvItemsClickListener.OnItemClicked(item, getAdapterPosition());
                }
            });
            if (item.getTicketLastMessage().getMessageSendType() == 1) {
                sender_type_tv.setVisibility(View.VISIBLE);
                sender_type_tv.setText("دانشجو : ");
            } else {
                sender_type_tv.setVisibility(View.VISIBLE);
                sender_type_tv.setText("شما : ");
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRvItemsClickListener.OnItemClicked(item, getAdapterPosition());
                }
            });
        }
        
        public String getTicketStatus(int ticketStatus, TextView ticket_status_value_tv) {
            switch (ticketStatus) {
                case 1:
                    ticket_status_value_tv.setTextColor(context.getResources().getColor(R.color.studentMessageTicketStatusColor));
                    return "پاسخ دانشجو";
                case 2:
                    ticket_status_value_tv.setTextColor(context.getResources().getColor(R.color.supporterMessageTicketStatusColor));
                    return "پاسخ اپراتور";
                case 3:
                    ticket_status_value_tv.setTextColor(context.getResources().getColor(R.color.studentTicketInProgressTicketStatucColor));
                    return "در دست پیگیری";
                case 4:
                    return "بسته شده توسط دانشجو";
                case 5:
                    return "بسته شده توسط اپراتور";
            }
            return null;
        }
    }
    
    
}
