package io.mvpstarter.sample.features.main;

import java.util.List;

import io.mvpstarter.sample.common.base.MvpView;

public interface MainMvpView extends MvpView {

    void showPokemon(List<String> pokemon);

    void showProgress(boolean show);

    void showError(Throwable error);
}
