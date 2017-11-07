package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivitypresenter;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.model.remote.Remote;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityPresenterModule {

    private MainActivityPresenter mainActivityPresenter;

    public MainActivityPresenterModule(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    @Provides
    Remote providesRemote(){
        return new Remote(mainActivityPresenter);
    }
}