package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void removeView();

}
