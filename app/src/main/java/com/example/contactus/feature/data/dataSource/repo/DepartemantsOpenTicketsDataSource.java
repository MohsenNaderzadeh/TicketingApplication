package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.DepartemantOpenTicketsResponse;

import io.reactivex.Single;

public interface DepartemantsOpenTicketsDataSource {
    
    Single<DepartemantOpenTicketsResponse> getAll();
}
