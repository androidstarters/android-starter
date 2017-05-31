package io.mvpstarter.sample.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.mvpstarter.sample.di.ActivityContext;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }
}
