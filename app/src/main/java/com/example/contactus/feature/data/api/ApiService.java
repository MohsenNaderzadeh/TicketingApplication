package com.example.contactus.feature.data.api;

import com.example.contactus.feature.data.entities.AddTicketResponse;
import com.example.contactus.feature.data.entities.LoginResponse;
import com.example.contactus.feature.data.entities.RelatedDepartemants;
import com.example.contactus.feature.data.entities.SubmitNewTicketMessageResponse;
import com.example.contactus.feature.data.entities.TicketsResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
