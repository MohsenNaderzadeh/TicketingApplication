package com.example.contactus.feature.data;

import java.util.ArrayList;
import java.util.List;

public class DataFakeGenerator {


    public static List<Ticket> getTicketsList(){
        List<Ticket> tickets=new ArrayList<>();
        tickets.add(new Ticket(1,"تست","سمیاد"));
        tickets.add(new Ticket(2,"ناد","ریست رمز"));
        tickets.add(new Ticket(3,"نمره","سمیاد"));
        tickets.add(new Ticket(4,"درس","سمیاد"));
        tickets.add(new Ticket(5,"نظام وظیفه","سمیاد"));

        return tickets;


    }
}
