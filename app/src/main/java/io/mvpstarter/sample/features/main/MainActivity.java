package io.mvpstarter.sample.features.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.mvpstarter.sample.R;
import io.mvpstarter.sample.features.base.BaseActivity;
import io.mvpstarter.sample.features.common.ErrorView;
import io.mvpstarter.sample.features.detail.DetailActivity;
import timber.log.Timber;

public class MainActivity extends BaseActivity
        implements MainMvpView, PokemonAdapter.ClickListener, ErrorView.ErrorListener {

    private static final int POKEMON_COUNT = 20;

    @Inject PokemonAdapter pokemonAdapter;
    @Inject MainPresenter mainPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recycler_pokemon)
    RecyclerView pokemonRecycler;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mainPresenter.attachView(this);

        setSupportActionBar(toolbar);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> mainPresenter.getPokemon(POKEMON_COUNT));

        pokemonAdapter.setClickListener(this);
        pokemonRecycler.setLayoutManager(new LinearLayoutManager(this));
        pokemonRecycler.setAdapter(pokemonAdapter);

        errorView.setErrorListener(this);

        mainPresenter.getPokemon(POKEMON_COUNT);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showPokemon(List<String> pokemon) {
        pokemonAdapter.setPokemon(pokemon);
        pokemonAdapter.notifyDataSetChanged();

        pokemonRecycler.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (pokemonRecycler.getVisibility() == View.VISIBLE
                    && pokemonAdapter.getItemCount() > 0) {
                swipeRefreshLayout.setRefreshing(true);
            } else {
                progressBar.setVisibility(View.VISIBLE);

                pokemonRecycler.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.GONE);
            }

            errorView.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        pokemonRecycler.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void onPokemonClick(String pokemon) {
        startActivity(DetailActivity.getStartIntent(this, pokemon));
    }

    @Override
    public void onReloadData() {
        mainPresenter.getPokemon(POKEMON_COUNT);
    }
}
