package io.mvpstarter.sample.features.detail;

import javax.inject.Inject;

import io.mvpstarter.sample.common.base.BasePresenter;
import io.mvpstarter.sample.data.model.Statistic;
import io.mvpstarter.sample.data.remote.ApiManager;
import io.mvpstarter.sample.di.ConfigPersistent;
import io.mvpstarter.sample.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class DetailPresenter extends BasePresenter<DetailMvpView> {

    private final ApiManager apiManager;

    @Inject
    public DetailPresenter(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    @Override
    public void attachView(DetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPokemon(String name) {
        checkViewAttached();
        getView().showProgress(true);
        apiManager
                .getPokemon(name)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        pokemon -> {
                            // It should be always checked if MvpView (Fragment or Activity) is attached.
                            // Calling showProgress() on a not-attached fragment will throw a NPE
                            // It is possible to ask isAdded() in the fragment, but it's better to ask in the presenter
                            getView().showProgress(false);
                            getView().showPokemon(pokemon);
                            for (Statistic statistic : pokemon.stats) {
                                getView().showStat(statistic);
                            }
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}
