package io.mvpstarter.sample.common.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.mvpstarter.sample.data.remote.ApiManager;
import io.mvpstarter.sample.data.remote.PokemonApi;
import io.mvpstarter.sample.di.ApplicationContext;

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment This allows
 * injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {
    private final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    /** *********** MOCKS *********** */
    @Provides
    @Singleton
    ApiManager providesDataManager() {
        return mock(ApiManager.class);
    }

    @Provides
    @Singleton
    PokemonApi provideMvpBoilerplateService() {
        return mock(PokemonApi.class);
    }
}
