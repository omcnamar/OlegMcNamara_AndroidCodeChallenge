package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity;


import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.entities.Posts;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivitypresenter.DaggerMainActivityPresenterComponent;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivitypresenter.MainActivityPresenterModule;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.model.remote.IRemote;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.model.remote.Remote;

import javax.inject.Inject;

public class MainActivityPresenter implements MainActivityContract.Presenter, IRemote {

    @Inject
    Remote remote;
    private MainActivityContract.View view;

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void attachRemote(){
        DaggerMainActivityPresenterComponent
                .builder()
                .mainActivityPresenterModule(new MainActivityPresenterModule(this))
                .build()
                .insert(this);
    }

    @Override
    public void makeRestCallForReddit​Post(String query) {
        remote.getPosts(query);
    }

    @Override
    public void sendData(final Posts posts) {
        ((MainActivity)view).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.updateUI(posts);
            }
        });

    }
}
