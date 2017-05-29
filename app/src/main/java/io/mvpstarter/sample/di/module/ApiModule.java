package io.mvpstarter.sample.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.mvpstarter.sample.data.remote.PokemonApi;
import retrofit2.Retrofit;

/**
 * Created by shivam on 29/5/17.
 */

@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    PokemonApi providePokemonApi(Retrofit retrofit) {
        return retrofit.create(PokemonApi.class);
    }
}