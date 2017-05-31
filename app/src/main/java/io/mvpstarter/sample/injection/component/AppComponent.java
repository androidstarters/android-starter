package io.mvpstarter.sample.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.mvpstarter.sample.data.DataManager;
import io.mvpstarter.sample.injection.ApplicationContext;
import io.mvpstarter.sample.injection.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}
