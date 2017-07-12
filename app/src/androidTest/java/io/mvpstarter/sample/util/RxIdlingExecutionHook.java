package io.mvpstarter.sample.util;

import rx.Observable;
import rx.Subscription;
import rx.plugins.RxJavaObservableExecutionHook;

/**
 * RxJava Observable execution hook that handles updating the active subscription count for a given
 * Espresso RxIdlingResource.
 */
public class RxIdlingExecutionHook extends RxJavaObservableExecutionHook {

    private RxIdlingResource rxIdlingResource;

    public RxIdlingExecutionHook(RxIdlingResource rxIdlingResource) {
        this.rxIdlingResource = rxIdlingResource;
    }

    @Override
    public <T> Observable.OnSubscribe<T> onSubscribeStart(
            Observable<? extends T> observableInstance, Observable.OnSubscribe<T> onSubscribe) {
        rxIdlingResource.incrementActiveSubscriptionsCount();
        return super.onSubscribeStart(observableInstance, onSubscribe);
    }

    @Override
    public <T> Throwable onSubscribeError(Throwable e) {
        rxIdlingResource.decrementActiveSubscriptionsCount();
        return super.onSubscribeError(e);
    }

    @Override
    public <T> Subscription onSubscribeReturn(Subscription subscription) {
        rxIdlingResource.decrementActiveSubscriptionsCount();
        return super.onSubscribeReturn(subscription);
    }
}
