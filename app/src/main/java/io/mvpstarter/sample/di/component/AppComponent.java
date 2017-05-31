package io.mvpstarter.sample.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.mvpstarter.sample.data.remote.DataManager;
import io.mvpstarter.sample.di.ApplicationContext;
import io.mvpstarter.sample.di.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();

}
