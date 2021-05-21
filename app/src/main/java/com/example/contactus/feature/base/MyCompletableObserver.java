package com.example.contactus.feature.base;

import android.util.Log;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MyCompletableObserver implements CompletableObserver {

    private final CompositeDisposable disposable;
    private static final String TAG = "MyCompletableObserver";

    public MyCompletableObserver(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable.add(d);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.i(TAG, "onError: " + e);
    }

}
