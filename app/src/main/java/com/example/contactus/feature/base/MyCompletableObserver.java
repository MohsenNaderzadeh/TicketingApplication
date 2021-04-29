package com.example.contactus.feature.base;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MyCompletableObserver implements CompletableObserver {

    private final CompositeDisposable disposable;

    public MyCompletableObserver(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable.add(d);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

}
