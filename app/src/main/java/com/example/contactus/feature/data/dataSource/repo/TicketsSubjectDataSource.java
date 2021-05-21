package com.example.contactus.feature.data.dataSource.repo;

import com.example.contactus.feature.data.entities.RelatedDepartemants;

import java.util.List;

import io.reactivex.Single;

public interface TicketsSubjectDataSource {


    Single<List<RelatedDepartemants>> getAllSubjects();
}
