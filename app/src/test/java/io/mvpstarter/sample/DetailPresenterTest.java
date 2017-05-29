package io.mvpstarter.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.mvpstarter.sample.common.TestDataFactory;
import io.mvpstarter.sample.data.model.Pokemon;
import io.mvpstarter.sample.data.remote.ApiManager;
import io.mvpstarter.sample.features.detail.DetailMvpView;
import io.mvpstarter.sample.features.detail.DetailPresenter;
import io.mvpstarter.sample.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    DetailMvpView mockDetailMvpView;
    @Mock
    ApiManager mockApiManager;
    private DetailPresenter detailPresenter;

    @Before
    public void setUp() {
        detailPresenter = new DetailPresenter(mockApiManager);
        detailPresenter.attachView(mockDetailMvpView);
    }

    @After
    public void tearDown() {
        detailPresenter.detachView();
    }

    @Test
    public void getPokemonDetailReturnsPokemon() throws Exception {
        Pokemon pokemon = TestDataFactory.makePokemon("id");
        when(mockApiManager.getPokemon(anyString())).thenReturn(Single.just(pokemon));

        detailPresenter.getPokemon(anyString());

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showPokemon(pokemon);
        verify(mockDetailMvpView, never()).showError(new RuntimeException());
    }

    @Test
    public void getPokemonDetailReturnsError() throws Exception {
        when(mockApiManager.getPokemon("id")).thenReturn(Single.error(new RuntimeException()));

        detailPresenter.getPokemon("id");

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showError(any(Throwable.class));
        verify(mockDetailMvpView, never()).showPokemon(any(Pokemon.class));
    }
}
