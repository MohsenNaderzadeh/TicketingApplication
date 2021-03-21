package com.example.contactus.feature.data;

import java.util.ArrayList;
import java.util.List;

public class DataFakeGenerator {


    public static List<Ticket> getTicketsList(){
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(1, "تست", "سمیاد", "در حال بررسی"));
        tickets.add(new Ticket(2, "ناد", "ریست رمز", "در حال بررسی"));
        tickets.add(new Ticket(3, "نمره", "سمیاد", "در حال بررسی"));
        tickets.add(new Ticket(4, "درس", "سمیاد", "در حال بررسی"));
        tickets.add(new Ticket(5, "نظام وظیفه", "سمیاد", "در حال بررسی"));

        return tickets;


    }
}
