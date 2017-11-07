package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.BasePresenter;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.BaseView;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.entities.Posts;

public interface MainActivityContract {

    interface View extends BaseView {
        void updateUI(Posts posts);
    }

    interface Presenter extends BasePresenter<View> {
        void attachRemote();
        void makeRestCallForReddit​Post(String query);
    }

}
