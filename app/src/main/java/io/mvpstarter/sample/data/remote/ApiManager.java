package io.mvpstarter.sample.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mvpstarter.sample.data.model.Pokemon;
import io.reactivex.Single;

/**
 * Created by shivam on 29/5/17.
 */

@Singleton
public class ApiManager {

    private PokemonApi pokemonApi;

    @Inject
    public ApiManager(PokemonApi pokemonApi) {
        this.pokemonApi = pokemonApi;
    }

    public Single<List<String>> getPokemonList(int limit) {
        return pokemonApi
                .getPokemonList(limit)
                .toObservable()
                .flatMapIterable(namedResources -> namedResources.results)
                .map(namedResource -> namedResource.name)
                .toList();
    }

    public Single<Pokemon> getPokemon(String name) {
        return pokemonApi.getPokemon(name);
    }

}