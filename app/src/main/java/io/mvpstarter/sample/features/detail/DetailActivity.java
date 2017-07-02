package io.mvpstarter.sample.features.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import io.mvpstarter.sample.R;
import io.mvpstarter.sample.data.model.response.Pokemon;
import io.mvpstarter.sample.data.model.response.Statistic;
import io.mvpstarter.sample.features.base.BaseActivity;
import io.mvpstarter.sample.features.common.ErrorView;
import io.mvpstarter.sample.features.detail.widget.StatisticView;
import io.mvpstarter.sample.injection.component.ActivityComponent;
import timber.log.Timber;

public class DetailActivity extends BaseActivity implements DetailMvpView, ErrorView.ErrorListener {

    public static final String EXTRA_POKEMON_NAME = "EXTRA_POKEMON_NAME";

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.image_pokemon)
    ImageView pokemonImage;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.layout_stats)
    LinearLayout statLayout;

    @BindView(R.id.layout_pokemon)
    View pokemonLayout;

    private String pokemonName;

    public static Intent getStartIntent(Context context, String pokemonName) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_POKEMON_NAME, pokemonName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pokemonName = getIntent().getStringExtra(EXTRA_POKEMON_NAME);
        if (pokemonName == null) {
            throw new IllegalArgumentException("Detail Activity requires a pokemon name@");
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle(pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1));

        errorView.setErrorListener(this);

        detailPresenter.getPokemon(pokemonName);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        detailPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        detailPresenter.detachView();
    }

    @Override
    public void showPokemon(Pokemon pokemon) {
        if (pokemon.sprites != null && pokemon.sprites.frontDefault != null) {
            Glide.with(this).load(pokemon.sprites.frontDefault).into(pokemonImage);
        }
        pokemonLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showStat(Statistic statistic) {
        StatisticView statisticView = new StatisticView(this);
        statisticView.setStat(statistic);
        statLayout.addView(statisticView);
    }

    @Override
    public void showProgress(boolean show) {
        errorView.setVisibility(View.GONE);
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        pokemonLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was a problem retrieving the pokemon...");
    }

    @Override
    public void onReloadData() {
        detailPresenter.getPokemon(pokemonName);
    }
}
