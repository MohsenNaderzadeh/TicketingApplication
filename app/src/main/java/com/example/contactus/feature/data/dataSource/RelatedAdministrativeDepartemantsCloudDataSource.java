package com.example.contactus.feature.data.dataSource;

import com.example.contactus.feature.data.api.ApiService;
import com.example.contactus.feature.data.dataSource.repo.RelatedAdministrativeDepartemantsDataSource;
import com.example.contactus.feature.data.entities.RelatedDepartemants;

import java.util.List;

import io.reactivex.Single;

public class RelatedAdministrativeDepartemantsCloudDataSource implements RelatedAdministrativeDepartemantsDataSource {

    private final ApiService apiService;

    public RelatedAdministrativeDepartemantsCloudDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<RelatedDepartemants>> getAllSubjects() {
        return apiService.getAllTicketsSubject();
    }
}
