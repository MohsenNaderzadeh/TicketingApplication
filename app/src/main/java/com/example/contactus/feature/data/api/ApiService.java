package com.example.contactus.feature.data.api;

import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.AddToSupporterInboxResponse;
import com.example.contactus.feature.data.entities.CloseTicketResponse;
import com.example.contactus.feature.data.entities.DepartemantOpenTicketsResponse;
import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.data.entities.LogoutResponse;
import com.example.contactus.feature.data.entities.MessageListResponse;
import com.example.contactus.feature.data.entities.RelatedDepartemants;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;
import com.example.contactus.feature.data.entities.SupporterTicketInboxResponse;
import com.example.contactus.feature.data.entities.TicketsResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @POST("User/Login.php")
    @FormUrlEncoded
    Single<LoginResponse> userAuthenticate(@Field("username") String userName, @Field("password") String password);

    @POST("Supporter/Login.php")
    @FormUrlEncoded
    Single<LoginResponse> supporterAuthenticate(@Field("username") String userName, @Field("password") String password);


    @GET("User/GetTicketList.php")
    Single<TicketsResponse> getAllTicketsList();

    @GET("User/GetDepartemantList.php")
    Single<List<RelatedDepartemants>> getAllTicketsSubject();
    
    @POST("User/CreateTicket.php")
    @FormUrlEncoded
    Single<AddTicketResponse> submitNewTicket(@Field("ticketTitle") String ticketTitle, @Field("ticketRelatedAdministrativeDepartemantId") int relatedDepartemantsId);
    
    @POST("User/AddNewTicketMessage.php")
    @FormUrlEncoded
    Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicket(@Field("studentId") int studentId, @Field("MessageSendType") int messageSendType, @Field("MessageText") String messageText, @Field("TicketId") int ticketId);
    
    @POST("Supporter/AddNewTicketMessage.php")
    @FormUrlEncoded
    Single<SubmitNewTicketMessageResponse> submitNewMessageOfTicketForSupporter(@Field("CoworkerId") int coworkerId, @Field("MessageSendType") int messageSendType, @Field("MessageText") String messageText, @Field("TicketId") int ticketId);
    
    @GET("User/GetTicketsMessages.php")
    Single<MessageListResponse> getAllTicketsMessage(@Query("ticketId") int ticketId);
    
    
    @POST("User/CloseTicket.php")
    @FormUrlEncoded
    Single<CloseTicketResponse> closeTicket(@Field("ticketId") int ticketId);
    
    @GET("Supporter/GetAllInboxTickets.php")
    Single<SupporterTicketInboxResponse> getAllSupporterTicketInbox();
    
    
    @GET("Supporter/GetDepartemantsTicketsList.php")
    Single<DepartemantOpenTicketsResponse> getAll();
    
    @PUT("User/LogOut.php")
    Single<LogoutResponse> logoutStudent();
    
    @PUT("Supporter/LogOut.php")
    Single<LogoutResponse> logoutSupporter();
    
    @GET("Supporter/GetTicketsMessages.php")
    Single<MessageListResponse> getAllMessageForSupporter(@Query("ticketId") int ticketId);
    
    
    @POST("Supporter/AddTicketToInbox.php")
    @FormUrlEncoded
    Single<AddToSupporterInboxResponse> addTicketToInbox(@Field("ticketId") int ticketId);
    
    
}
