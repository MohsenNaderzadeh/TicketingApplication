package com.example.contactus.feature.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.contactus.R;
import com.example.contactus.feature.base.RvAdapter;
import com.example.contactus.feature.base.RvViewHolder;
import com.example.contactus.feature.data.Ticket;

import java.util.ArrayList;
import java.util.List;


public class TicketListAdapter extends RvAdapter<Ticket, TicketListAdapter.TicketListViewHolder> {

    public List<Ticket> getFilteredList() {
        return filteredList;
    }

    private final List<Ticket> filteredList = new ArrayList<>();

    public TicketListAdapter(List<Ticket> items) {
        super(items);



    }
    @NonNull
    @Override
    public TicketListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tickets_list,parent,false));
    }
    @Override
    public Filter setFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                if(charSequence.toString().isEmpty())
                {
                    filteredList.clear();
                }else
                {
                    filteredList.clear();
                    for(Ticket ticket: main_items)
                    {
                        if(ticket.getTitle().contains(charSequence.toString()))
                        {
                            if(!filteredList.contains(ticket))
                            {
                                filteredList.add(ticket);

                            }
                        }
                    }
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filterResults;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(filteredList.size()>0)
                {
                    TicketListAdapter.super.setItems(filteredList);
                }
                else
                {
                   TicketListAdapter.super.setItems(main_items);
                }
            }
        };

    }

    public class TicketListViewHolder extends RvViewHolder<Ticket> {
        private final TextView ticket_short_title_tv;
        private final TextView ticket_title;
        private final TextView ticket_desc_tv;
        private final TextView ticket_badage_tv;
        private CardView ticket_card;

        //TODO:add TextView decleration for status of ticket later and use PlaceHolder for putting status ...
        public TicketListViewHolder(@NonNull View itemView) {
            super(itemView);
            ticket_short_title_tv = itemView.findViewById(R.id.ticket_short_title_tv);
            ticket_title = itemView.findViewById(R.id.ticket_title);
            ticket_desc_tv = itemView.findViewById(R.id.ticket_desc_tv);
            ticket_badage_tv = itemView.findViewById(R.id.ticket_badage_tv);
        }


        @Override
        public void bindData(Ticket item) {
            ticket_short_title_tv.setText(item.getTitle().substring(0,2));
            ticket_title.setText(item.getTitle());
            ticket_desc_tv.setText(item.getDescription());

        }
    }



}
