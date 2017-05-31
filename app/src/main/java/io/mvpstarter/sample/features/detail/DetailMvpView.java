package io.mvpstarter.sample.features.detail;

import io.mvpstarter.sample.data.model.response.Pokemon;
import io.mvpstarter.sample.data.model.response.Statistic;
import io.mvpstarter.sample.features.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showPokemon(Pokemon pokemon);

    void showStat(Statistic statistic);

    void showProgress(boolean show);

    void showError(Throwable error);
}
