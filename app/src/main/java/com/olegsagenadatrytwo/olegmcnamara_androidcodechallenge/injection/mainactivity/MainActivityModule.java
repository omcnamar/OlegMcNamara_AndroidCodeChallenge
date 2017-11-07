package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivity;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    public MainActivityModule() {
    }

    @Provides
    MainActivityPresenter providesMainActivityPresenter(){
        return new MainActivityPresenter();
    }
}
