package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivity;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void insert(MainActivity mainActivity);
}
