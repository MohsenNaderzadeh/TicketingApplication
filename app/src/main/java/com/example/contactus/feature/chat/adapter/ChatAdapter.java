package com.example.contactus.feature.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactus.R;
import com.example.contactus.feature.base.RvViewHolder;
import com.example.contactus.feature.data.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int STD_USER_MESSAGE_TYPE = 0;
    private static final int USER_MESSAGE_TYPE = 1;
    private List<Message> messageList = new ArrayList<>();


    public ChatAdapter() {
    }

    public ChatAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }


    public void addMessage(Message message) {
        this.messageList.add(message);
        notifyItemInserted(messageList.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (messageList.get(position).getMessageSendType() == 1) {
            return STD_USER_MESSAGE_TYPE;
        } else {
            return USER_MESSAGE_TYPE;
        }
    }

    public void updateMessage(Message oldMessage, Message newMessage) {
        if (this.messageList.indexOf(oldMessage) != RecyclerView.NO_POSITION) {
            int position = this.messageList.indexOf(oldMessage);
            this.messageList.set(position, newMessage);
            notifyItemChanged(position);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == STD_USER_MESSAGE_TYPE) {

            return new StdTextChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stduser_chat, parent, false));
        } else if (viewType == USER_MESSAGE_TYPE) {
            return new UserTextChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workeruser_chat, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof UserTextChatViewHolder) {
            ((UserTextChatViewHolder) holder).bindData(messageList.get(position));
        } else if (holder instanceof StdTextChatViewHolder) {
            ((StdTextChatViewHolder) holder).bindData(messageList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class UserTextChatViewHolder extends RvViewHolder<Message> {

        private final TextView userText;

        public UserTextChatViewHolder(@NonNull View itemView) {
            super(itemView);
            userText = itemView.findViewById(R.id.UserChat_text_tv);
        }

        @Override
        public void bindData(Message item) {
            userText.setText(item.getMessageText());
        }
    }

    class StdTextChatViewHolder extends RvViewHolder<Message> {
        private final TextView stdText;
        private final View sendStatus_iv;
        public StdTextChatViewHolder(@NonNull View itemView) {
            super(itemView);
            stdText = itemView.findViewById(R.id.stdUserChat_text_tv);
            sendStatus_iv = itemView.findViewById(R.id.sendStatus_iv);
        }

        @Override
        public void bindData(Message item) {

            stdText.setText(item.getMessageText());
            if (item.getMessageSendStatus() == Message.sendStatus.SEND) {
                sendStatus_iv.setBackgroundResource(0);
                sendStatus_iv.setBackgroundResource(R.drawable.ic_check);
            } else {
                sendStatus_iv.setBackgroundResource(R.drawable.unsend);
            }
        }
    }
}
