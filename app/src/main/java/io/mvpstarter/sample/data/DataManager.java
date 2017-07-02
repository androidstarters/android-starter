package io.mvpstarter.sample.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.mvpstarter.sample.data.model.response.Pokemon;
import io.mvpstarter.sample.data.remote.PokemonService;
import io.reactivex.Single;

/**
 * Created by shivam on 29/5/17.
 */
@Singleton
public class DataManager {

    private PokemonService pokemonService;

    @Inject
    public DataManager(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public Single<List<String>> getPokemonList(int limit) {
        return pokemonService
                .getPokemonList(limit)
                .toObservable()
                .flatMapIterable(namedResources -> namedResources.results)
                .map(namedResource -> namedResource.name)
                .toList();
    }

    public Single<Pokemon> getPokemon(String name) {
        return pokemonService.getPokemon(name);
    }
}
