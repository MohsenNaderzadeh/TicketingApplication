package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.dataSource.RelatedAdministrativeDepartemantsCloudDataSource;
import com.example.contactus.feature.data.entities.RelatedDepartemants;

import java.util.List;

import io.reactivex.Single;

public class RelatedAdministrativeDepartemantsRepo implements RelatedAdministrativeDepartemantsDataSource {
    private final RelatedAdministrativeDepartemantsCloudDataSource cloudDataSource;

    public RelatedAdministrativeDepartemantsRepo(RelatedAdministrativeDepartemantsCloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    @Override
    public Single<List<RelatedDepartemants>> getAllSubjects() {
        return cloudDataSource.getAllSubjects();
    }
}
