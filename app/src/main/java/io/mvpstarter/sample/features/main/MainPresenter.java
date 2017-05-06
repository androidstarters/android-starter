package io.mvpstarter.sample.features.main;

import javax.inject.Inject;

import io.mvpstarter.sample.data.DataManager;
import io.mvpstarter.sample.features.base.BasePresenter;
import io.mvpstarter.sample.injection.ConfigPersistent;
import io.mvpstarter.sample.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getMvpView().showProgress(true);
        mDataManager
                .getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        pokemons -> {
                            getMvpView().showProgress(false);
                            getMvpView().showPokemon(pokemons);
                        },
                        throwable -> {
                            getMvpView().showProgress(false);
                            getMvpView().showError(throwable);
                        });
    }
}
