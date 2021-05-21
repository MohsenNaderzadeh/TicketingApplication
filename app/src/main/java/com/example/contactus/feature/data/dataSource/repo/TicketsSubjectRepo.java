package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.CloudDataSource;
import com.example.contactus.feature.data.entities.RelatedDepartemants;

import java.util.List;

import io.reactivex.Single;

public class TicketsSubjectRepo implements TicketsSubjectDataSource {
    private final CloudDataSource cloudDataSource;

    public TicketsSubjectRepo(CloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    @Override
    public Single<List<RelatedDepartemants>> getAllSubjects() {
        return cloudDataSource.getAllSubjects();
    }
}
