package io.mvpstarter.sample;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.mvpstarter.sample.common.TestDataFactory;
import io.mvpstarter.sample.data.model.NamedResource;
import io.mvpstarter.sample.data.model.Pokemon;
import io.mvpstarter.sample.data.model.PokemonListResponse;
import io.mvpstarter.sample.data.remote.ApiManager;
import io.mvpstarter.sample.data.remote.PokemonApi;
import io.mvpstarter.sample.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by shivam on 29/5/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ApiManagerTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    private PokemonApi mockPokemonApi;

    private ApiManager apiManager;

    @Before
    public void setUp() {
        apiManager = new ApiManager(mockPokemonApi);
    }

    @Test
    public void getPokemonListCompletesAndEmitsPokemonList() {
        List<NamedResource> namedResourceList = TestDataFactory.makeNamedResourceList(5);
        PokemonListResponse pokemonListResponse = new PokemonListResponse();
        pokemonListResponse.results = namedResourceList;

        when(mockPokemonApi.getPokemonList(anyInt()))
                .thenReturn(Single.just(pokemonListResponse));

        apiManager.getPokemonList(10)
                .test()
                .assertComplete()
                .assertValue(TestDataFactory.makePokemonNameList(namedResourceList));
    }

    @Test
    public void getPokemonCompletesAndEmitsPokemon() {
        String name = "charmander";
        Pokemon pokemon = TestDataFactory.makePokemon(name);
        when(mockPokemonApi.getPokemon(anyString())).thenReturn(Single.just(pokemon));

        apiManager.getPokemon(name).test().assertComplete().assertValue(pokemon);
    }

}