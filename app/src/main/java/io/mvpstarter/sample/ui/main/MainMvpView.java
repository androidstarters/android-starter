package io.mvpstarter.sample.ui.main;

import java.util.List;

import io.mvpstarter.sample.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showPokemon(List<String> pokemon);

    void showProgress(boolean show);

    void showError(Throwable error);

}