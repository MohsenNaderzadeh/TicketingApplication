package com.example.contactus.feature.chat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactus.R;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.chat.adapter.ChatAdapter;
import com.example.contactus.feature.data.entities.Message;
import com.example.contactus.feature.data.entities.Ticket;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.main.MainActivity;
import com.example.contactus.feature.utils.MenuUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends ObserverActivity {

    private View main_hambergur_back_ic;
    private TextView chat_ticket_title_tv;
    private Ticket input_Intent;
    private RecyclerView chat_ticketsList_rv;
    private ChatAdapter chatAdapter;
    private EditText chat_user_message_ed;
    private View chat_send_message_iv;
    private View chat_option_Iv;
    private PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        input_Intent = getIntent().getParcelableExtra(MainActivity.EXTRA_KEY_TICKET_TO_CHAT);
    }

    @Override
    public void observe() {
        chat_send_message_iv.setOnClickListener(view -> {
            Message message = new Message();
            message.setType(0);
            message.setText(chat_user_message_ed.getText().toString());
            chatAdapter.addMessage(message);
        });
        chat_option_Iv.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(this, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.chat_acitvity_menu, popup.getMenu());
            popup.show();
            Menu menu = popup.getMenu();
            for (int i = 0; i < menu.size(); i++) {
                MenuItem mi = menu.getItem(i);
                MenuUtils.applyFontToMenuItem(mi, ChatActivity.this);
            }
        });


    }

    @Override
    public void setUpViews() {
        main_hambergur_back_ic = findViewById(R.id.main_hambergur_back_ic);
        chat_ticket_title_tv = findViewById(R.id.chat_ticket_title_tv);
        chat_ticket_title_tv.setText(input_Intent.getTitle());
        chat_ticketsList_rv = findViewById(R.id.chat_ticketsList_rv);
        chat_user_message_ed = findViewById(R.id.chat_user_message_ed);
        chat_send_message_iv = findViewById(R.id.chat_send_message_iv);
        chat_ticketsList_rv.setLayoutManager(new LinearLayoutManager(ChatActivity.this, RecyclerView.VERTICAL, false));
        chatAdapter = new ChatAdapter();
        chat_ticketsList_rv.setAdapter(chatAdapter);
        chatAdapter.setMessageList(createMessages());
        chat_option_Iv = findViewById(R.id.chat_option_Iv);
        popup = new PopupMenu(ChatActivity.this, chat_option_Iv);

    }

    @Override
    public void setTextofToolbar(ConnectedInternet connectedInternet) {
        super.setTextofToolbar(connectedInternet);
    }

    @Override
    public void setTextofToolbar(DisConnectedInternet disConnectedInternet) {
        super.setTextofToolbar(disConnectedInternet);
    }


    public List<Message> createMessages() {
        List<Message> messages = new ArrayList<>();
        Message stdMessage = new Message();
        stdMessage.setText("سلام وقت بخیر");
        stdMessage.setType(0);
        Message userMessage = new Message();
        userMessage.setText("سلام وقت شما هم بخیر");
        userMessage.setType(1);
        Message stdmessage2 = new Message();
        stdmessage2.setText("در مورد ناد سوال داشتم");
        stdmessage2.setType(0);
        messages.add(stdMessage);
        messages.add(userMessage);
        messages.add(stdmessage2);
        return messages;
    }
}