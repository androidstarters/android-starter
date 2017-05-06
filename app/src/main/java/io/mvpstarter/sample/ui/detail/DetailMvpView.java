package io.mvpstarter.sample.ui.detail;

import io.mvpstarter.sample.data.model.Pokemon;
import io.mvpstarter.sample.data.model.Statistic;
import io.mvpstarter.sample.ui.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showPokemon(Pokemon pokemon);

    void showStat(Statistic statistic);

    void showProgress(boolean show);

    void showError(Throwable error);

}