package com.example.contactus.feature.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactus.R;
import com.example.contactus.feature.base.MSingleObserver;
import com.example.contactus.feature.base.ObserverActivity;
import com.example.contactus.feature.chat.adapter.ChatAdapter;
import com.example.contactus.feature.data.TokenContainer;
import com.example.contactus.feature.data.api.ApiServiceProvider;
import com.example.contactus.feature.data.dataSource.TicketsCloudDataSource;
import com.example.contactus.feature.data.dataSource.TicketsMessageCloudDataSource;
import com.example.contactus.feature.data.dataSource.repo.AuthenticateDataSource;
import com.example.contactus.feature.data.dataSource.repo.TicketsMessagesRepo;
import com.example.contactus.feature.data.dataSource.repo.TicketsRepository;
import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.AddToSupporterInboxResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
import com.example.contactus.feature.data.entities.Message;
import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.RelatedDepartemants;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;
import com.example.contactus.feature.data.entities.TicketInfo;
import com.example.contactus.feature.data.entities.TicketOwnerInfo;
import com.example.contactus.feature.eventbusevents.ConnectedInternet;
import com.example.contactus.feature.eventbusevents.DisConnectedInternet;
import com.example.contactus.feature.studentmain.TicketsListActivity;
import com.example.contactus.feature.utils.MenuUtils;
import com.example.contactus.feature.view.LoadingDialogFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChatActivity extends ObserverActivity {
    private static final String TAG = "ChatActivity";
    private View main_hambergur_back_ic;
    private TextView chat_ticket_title_tv;
    private TicketInfo inputTicket;
    private RecyclerView chat_ticketsList_rv;
    private ChatAdapter chatAdapter;
    private EditText chat_user_message_ed;
    private View chat_send_message_iv;
    private View chat_option_Iv;
    private RelatedDepartemants selectedDepartement;
    private String newTicketTitle;
    private TextView chat_ticketList_emptyState_tv;
    private ChatActivityViewModel viewModel;
    private ProgressBar loadingMessage_ProgressBar;
    private View ticket_closed_message_container;
    private TextView ticket_closed_message_tv;
    private View chat_message_rl;
    private String originActivity;
    private int supporterId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        inputTicket = getIntent().getParcelableExtra(TicketsListActivity.EXTRA_KEY_TICKET_TO_CHAT);
        originActivity = getIntent().getStringExtra("OriginActivity");
        supporterId = getIntent().getIntExtra("SupporterId", -1);
        if (TokenContainer.isStudent()) {
            selectedDepartement = getIntent().getParcelableExtra(TicketsListActivity.EXTRA_KEY_SELECTED_DEPARTEMANT);
            newTicketTitle = getIntent().getStringExtra(TicketsListActivity.EXTRA_KEY_NEW_TICKET_TITLE);
        }
        
    }

    @Override
    public void observe() {
    
        if (inputTicket != null && TokenContainer.isStudent()) {
            loadingMessage_ProgressBar.setVisibility(View.VISIBLE);
            viewModel.getAll(inputTicket.getTicketId(), AuthenticateDataSource.UserType.USER)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MSingleObserver<MessageListResponse>(compositeDisposable) {
                        @Override
                        public void onSuccess(MessageListResponse messageListResponse) {
                            chat_ticketList_emptyState_tv.setVisibility(View.GONE);
                            loadingMessage_ProgressBar.setVisibility(View.VISIBLE);
                        
                            chatAdapter.setMessageList(messageListResponse.getMessages());
                            loadingMessage_ProgressBar.setVisibility(View.GONE);
                        }
                    });
        } else if (inputTicket != null && TokenContainer.isIsSupporter()) {
            loadingMessage_ProgressBar.setVisibility(View.VISIBLE);
            viewModel.getAll(inputTicket.getTicketId(), AuthenticateDataSource.UserType.SUPPORTER)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MSingleObserver<MessageListResponse>(compositeDisposable) {
                        @Override
                        public void onSuccess(MessageListResponse messageListResponse) {
                            chat_ticketList_emptyState_tv.setVisibility(View.GONE);
                            loadingMessage_ProgressBar.setVisibility(View.VISIBLE);
                            chatAdapter.setMessageList(messageListResponse.getMessages());
                            chat_ticketsList_rv.setVisibility(View.VISIBLE);
                            loadingMessage_ProgressBar.setVisibility(View.GONE);
                            if (originActivity == null) {
                                ticket_closed_message_container.setVisibility(View.VISIBLE);
                                ticket_closed_message_tv.setText("?????????? ???????? ???? ???? ?????????? ???????????????? ?????? ?????????? ????????");
                                chat_option_Iv.setVisibility(View.VISIBLE);
                            } else if (inputTicket.getTicketStatus() != 4 && inputTicket.getTicketStatus() != 5) {
                                chat_message_rl.setVisibility(View.VISIBLE);
                            }
                        
                        }
                    });
        }
        chat_send_message_iv.setOnClickListener(view -> {
            if (inputTicket == null && TokenContainer.isStudent()) {
                //new Ticket Submit
                chat_ticketList_emptyState_tv.setVisibility(View.GONE);
                chat_ticketsList_rv.setVisibility(View.VISIBLE);
                Message message = new Message();
                message.setMessageSendType(1);
                message.setMessageText(chat_user_message_ed.getText().toString());
                chatAdapter.addMessage(message);
                viewModel.submitNewTicket(newTicketTitle, selectedDepartement.getDepartemantId()).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MSingleObserver<AddTicketResponse>(compositeDisposable) {
                            @Override
                            public void onSuccess(AddTicketResponse addTicketResponse) {
                                Log.i(TAG, "onSuccess: " + addTicketResponse);
                                viewModel.submitNewTicketMessage(addTicketResponse.getTicketInfo().getTicketOwnerId(), 1, chat_user_message_ed.getText().toString(), addTicketResponse.getTicketInfo().getTicketId(), AuthenticateDataSource.UserType.USER, -1)
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new MSingleObserver<SubmitNewTicketMessageResponse>(compositeDisposable) {
                                            @Override
                                            public void onSuccess(SubmitNewTicketMessageResponse submitNewTicketMessageResponse) {
                                                Message newMessage = submitNewTicketMessageResponse.getMessage();
                                                newMessage.setMessageSendStatus(Message.sendStatus.SEND);
                                                chatAdapter.updateMessage(message, newMessage);
                                                chat_user_message_ed.setText("");
                                            }
                                        });
                            }
                        });
            } else if (TokenContainer.isStudent()) {
                //Add New Message to older open tickets
                viewModel.submitNewTicketMessage(inputTicket.getTicketOwnerId(), 1, chat_user_message_ed.getText().toString(), inputTicket.getTicketId(), AuthenticateDataSource.UserType.USER, -1)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MSingleObserver<SubmitNewTicketMessageResponse>(compositeDisposable) {
                            @Override
                            public void onSuccess(SubmitNewTicketMessageResponse submitNewTicketMessageResponse) {
                                Message newMessage = submitNewTicketMessageResponse.getMessage();
                                newMessage.setMessageSendStatus(Message.sendStatus.SEND);
                                chatAdapter.addMessage(newMessage);
                                chat_user_message_ed.setText("");
                            }
                        });
        
            } else if (TokenContainer.isIsSupporter() && inputTicket != null) {
                viewModel.submitNewTicketMessage(-1, 0, chat_user_message_ed.getText().toString(), inputTicket.getTicketId(), AuthenticateDataSource.UserType.SUPPORTER, supporterId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MSingleObserver<SubmitNewTicketMessageResponse>(compositeDisposable) {
                            @Override
                            public void onSuccess(SubmitNewTicketMessageResponse submitNewTicketMessageResponse) {
                                Message newMessage = submitNewTicketMessageResponse.getMessage();
                                newMessage.setMessageSendStatus(Message.sendStatus.SEND);
                                chatAdapter.addMessage(newMessage);
                                chat_user_message_ed.setText("");
                            }
                        });
            }
        });


    }

    @Override
    public void setUpViews() {
        main_hambergur_back_ic = findViewById(R.id.main_hambergur_back_ic);
        chat_ticket_title_tv = findViewById(R.id.chat_ticket_title_tv);
        chat_ticket_title_tv.setText(inputTicket != null ? inputTicket.getTicketTitle() : newTicketTitle);
        chat_ticketsList_rv = findViewById(R.id.chat_ticketsList_rv);
        chat_user_message_ed = findViewById(R.id.chat_user_message_ed);
        chat_send_message_iv = findViewById(R.id.chat_send_message_iv);
        ticket_closed_message_container = findViewById(R.id.ticket_closed_message_container);
        ticket_closed_message_tv = findViewById(R.id.ticket_closed_message_tv);
        chat_message_rl = findViewById(R.id.chat_message_rl);
        chat_option_Iv = findViewById(R.id.chat_option_Iv);
        loadingMessage_ProgressBar = findViewById(R.id.loadingMessage_ProgressBar);
        viewModel = new ChatActivityViewModel(new TicketsRepository(new TicketsCloudDataSource(ApiServiceProvider.getApiService())), new TicketsMessagesRepo(new TicketsMessageCloudDataSource(ApiServiceProvider.getApiService())));
        chat_ticketList_emptyState_tv = findViewById(R.id.chat_ticketList_emptyState_tv);
        chat_ticketsList_rv.setLayoutManager(new LinearLayoutManager(ChatActivity.this, RecyclerView.VERTICAL, false));
        chatAdapter = new ChatAdapter();
        chat_ticketsList_rv.setAdapter(chatAdapter);
        if (inputTicket != null && TokenContainer.isStudent()) {
            chat_ticketList_emptyState_tv.setVisibility(View.GONE);
            chat_ticketsList_rv.setVisibility(View.VISIBLE);
            if (inputTicket.getTicketStatus() == 4) {
                ticket_closed_message_container.setVisibility(View.VISIBLE);
                ticket_closed_message_tv.setText("?????? ???????? ???????? ???????????? ???????? ?????? ??????.");
                chat_option_Iv.setVisibility(View.GONE);
            } else if (inputTicket.getTicketStatus() == 5) {
                ticket_closed_message_container.setVisibility(View.VISIBLE);
                ticket_closed_message_tv.setText("?????? ???????? ???????? ?????????????? ?????????? ???????? ?????? ??????.");
                chat_option_Iv.setVisibility(View.GONE);
            } else {
                chat_message_rl.setVisibility(View.VISIBLE);
            }
        
        } else if (inputTicket == null && TokenContainer.isStudent()) {
            chat_option_Iv.setVisibility(View.GONE);
            chat_message_rl.setVisibility(View.VISIBLE);
        } else if (inputTicket == null && TokenContainer.isIsSupporter()) {
            ticket_closed_message_container.setVisibility(View.VISIBLE);
            ticket_closed_message_tv.setText("???????? ???????????????? ???? ?????? ???????? ???????? ?????????? ???? ???? ???? ?????????? ???????????????? ?????? ?????????? ???????? ");
            chat_option_Iv.setVisibility(View.VISIBLE);
        } else if (TokenContainer.isIsSupporter() && inputTicket.getTicketStatus() == 4) {
            chat_message_rl.setVisibility(View.GONE);
            ticket_closed_message_container.setVisibility(View.VISIBLE);
            ticket_closed_message_tv.setText("?????? ???????? ???????? ???????????? ???????? ?????? ??????.");
            chat_option_Iv.setVisibility(View.GONE);
        } else if (TokenContainer.isIsSupporter() && inputTicket.getTicketStatus() == 5) {
            chat_message_rl.setVisibility(View.GONE);
            ticket_closed_message_container.setVisibility(View.VISIBLE);
            ticket_closed_message_tv.setText("?????? ???????? ???????? ?????? ???????? ?????? ??????.");
            chat_option_Iv.setVisibility(View.GONE);
        }


        chat_option_Iv.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(this, view);
            MenuInflater inflater = popup.getMenuInflater();
            if (TokenContainer.isStudent()) {
                inflater.inflate(R.menu.chat_acitvity_menu, popup.getMenu());
            } else if (TokenContainer.isIsSupporter() && inputTicket.getTicketStatus() == 8) {
                inflater.inflate(R.menu.chat_acitvity_menu_supporter_main, popup.getMenu());
            } else {
                inflater.inflate(R.menu.chat_acitvity_menu_supporter, popup.getMenu());
            }
            popup.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.close_ticket && TokenContainer.isStudent()) {
                    LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
                    loadingDialogFragment.show(getSupportFragmentManager(), null);
                    viewModel.closeTicket(inputTicket.getTicketId())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new MSingleObserver<CloseTicketResponse>(compositeDisposable) {
                                @Override
                                public void onSuccess(CloseTicketResponse closeTicketResponse) {
                                    //TODO:Should Publish the Updated Ticket
                                    loadingDialogFragment.dismiss();
                                    inputTicket.setTicketStatus(4);
                                    chat_message_rl.setVisibility(View.GONE);
                                    ticket_closed_message_container.setVisibility(View.VISIBLE);
                                    ticket_closed_message_tv.setText("?????? ???????? ???????? ???????????? ???????? ?????? ??????.");
                                    chat_option_Iv.setVisibility(View.GONE);
                            
                                }
                            });
                    //TODO://Implement Close Ticket And Change Status of ticket for supporter
                } else if (menuItem.getItemId() == R.id.addToInbox && TokenContainer.isIsSupporter()) {
                    LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
                    loadingDialogFragment.show(getSupportFragmentManager(), null);
    
                    viewModel.addTicketToInbox(inputTicket.getTicketId())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new MSingleObserver<AddToSupporterInboxResponse>(compositeDisposable) {
                                @Override
                                public void onSuccess(AddToSupporterInboxResponse addToSupporterInboxResponse) {
                                    if (addToSupporterInboxResponse.isSuccess()) {
                                        finish();
                                    }
                                }
                            });
                } else if (menuItem.getItemId() == R.id.close_ticket && TokenContainer.isIsSupporter()) {
                    loadingDialogFragment.show(getSupportFragmentManager(), null);
                    viewModel.closeTicketBySupporter(inputTicket.getTicketId())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new MSingleObserver<CloseTicketResponse>(compositeDisposable) {
                                @Override
                                public void onSuccess(CloseTicketResponse closeTicketResponse) {
                                    //TODO:Should Publish the Updated Ticket
                                    loadingDialogFragment.dismiss();
                                    inputTicket.setTicketStatus(5);
                                    chat_message_rl.setVisibility(View.GONE);
                                    ticket_closed_message_container.setVisibility(View.VISIBLE);
                                    ticket_closed_message_tv.setText("?????? ???????? ???????? ?????? ???????? ?????? ??????");
                                    chat_option_Iv.setVisibility(View.GONE);
                                }
                            });
                } else if (menuItem.getItemId() == R.id.getOwnerInfo && TokenContainer.isIsSupporter()) {
                    loadingDialogFragment.show(getSupportFragmentManager(), null);
                    viewModel.getTicketOwnerInfo(inputTicket.getTicketOwnerId())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new MSingleObserver<TicketOwnerInfo>(compositeDisposable) {
                                @Override
                                public void onSuccess(@NonNull TicketOwnerInfo ticketOwnerInfo) {
                                    if (ticketOwnerInfo.isSuccess()) {
                                        loadingDialogFragment.dismiss();
                                        String stdName = ticketOwnerInfo.getOwnerInfo().getStudentName() + " " + ticketOwnerInfo.getOwnerInfo().getStudentLastName();
                                        String stdNationalCode = ticketOwnerInfo.getOwnerInfo().getStudentNationalCode();
                                        String stdUniversityCode = ticketOwnerInfo.getOwnerInfo().getStudentUniversityCode();
                                        String stdMajorName = ticketOwnerInfo.getOwnerInfo().getMajorName();
                                        String stdMajorGrade = ticketOwnerInfo.getOwnerInfo().getGradeName();
                        
                                        ShowTicketOwnerInfoDialog showTicketOwnerInfoDialog = ShowTicketOwnerInfoDialog.newInstance(stdName, stdNationalCode, stdUniversityCode, stdMajorName, stdMajorGrade);
                                        showTicketOwnerInfoDialog.show(getSupportFragmentManager(), null);
                                    }
                                }
                            });
    
    
                }
                return true;
            });
            popup.show();
            Menu menu = popup.getMenu();
            for (int i = 0; i < menu.size(); i++) {
                MenuItem mi = menu.getItem(i);
                MenuUtils.applyFontToMenuItem(mi, ChatActivity.this);
            }
        });
    
        main_hambergur_back_ic.setOnClickListener(view -> {
            onBackPressed();
        });
    
    
    }

    @Override
    public void setTextofToolbar(ConnectedInternet connectedInternet) {
        super.setTextofToolbar(connectedInternet);
    }

    @Override
    public void setTextofToolbar(DisConnectedInternet disConnectedInternet) {
        super.setTextofToolbar(disConnectedInternet);
    }


}