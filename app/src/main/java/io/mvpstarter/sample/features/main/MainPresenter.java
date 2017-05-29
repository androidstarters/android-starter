package io.mvpstarter.sample.features.main;

import javax.inject.Inject;

import io.mvpstarter.sample.common.base.BasePresenter;
import io.mvpstarter.sample.data.remote.ApiManager;
import io.mvpstarter.sample.di.ConfigPersistent;
import io.mvpstarter.sample.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final ApiManager apiManager;

    @Inject
    public MainPresenter(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getView().showProgress(true);
        apiManager
                .getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        pokemons -> {
                            getView().showProgress(false);
                            getView().showPokemon(pokemons);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}
