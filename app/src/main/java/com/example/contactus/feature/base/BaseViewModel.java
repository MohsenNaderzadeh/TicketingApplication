package com.example.contactus.feature.base;

import io.reactivex.subjects.BehaviorSubject;

public class BaseViewModel {
    protected final BehaviorSubject<Boolean> shouldShowProgressBar = BehaviorSubject.create();

    public BehaviorSubject<Boolean> getShouldShowProgressBar() {
        return shouldShowProgressBar;
    }

}
