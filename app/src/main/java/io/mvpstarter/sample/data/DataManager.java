package io.mvpstarter.sample.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mvpstarter.sample.data.model.Pokemon;
import io.mvpstarter.sample.data.remote.MvpStarterService;
import io.reactivex.Single;

@Singleton
public class DataManager {

    private final MvpStarterService mvpStarterService;

    @Inject
    public DataManager(MvpStarterService mvpStarterService) {
        this.mvpStarterService = mvpStarterService;
    }

    public Single<List<String>> getPokemonList(int limit) {
        return mvpStarterService
                .getPokemonList(limit)
                .toObservable()
                .flatMapIterable(namedResources -> namedResources.results)
                .map(namedResource -> namedResource.name)
                .toList();
    }

    public Single<Pokemon> getPokemon(String name) {
        return mvpStarterService.getPokemon(name);
    }
}
