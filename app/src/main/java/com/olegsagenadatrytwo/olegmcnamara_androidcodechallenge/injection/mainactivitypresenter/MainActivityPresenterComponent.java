package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivitypresenter;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainActivityPresenterModule.class} )
public interface MainActivityPresenterComponent {
    void insert(MainActivityPresenter mainActivityPresenter);
}