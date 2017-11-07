package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.R;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.entities.Posts;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivity.DaggerMainActivityComponent;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.injection.mainactivity.MainActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    MainActivityPresenter presenter;
    @BindView(R.id.searchView)
    SearchView mSearchView;
    @BindView(R.id.tvError)
    TextView mTvError;
    @BindView(R.id.rvRecyclerView)
    RecyclerView mRvRecyclerView;
    @BindView(R.id.btnSearch)
    Button mBtnSearch;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind ButterKnife
        ButterKnife.bind(this);

        //perform set up
        setUpDagger();
        initRecyclerView();
        initPresenter();

        //make the default request
        presenter.makeRestCallForReddit​Post("funny");
    }

    /**
     * method to set Up Dagger
     */
    private void setUpDagger() {
        DaggerMainActivityComponent.create().insert(this);
        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule()).build().insert(this);
    }

    /**
     * method that initializes Recycler View
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRvRecyclerView.getContext(), layoutManager.getOrientation());
        mRvRecyclerView.setLayoutManager(layoutManager);
        mRvRecyclerView.addItemDecoration(dividerItemDecoration);
        mRvRecyclerView.setItemAnimator(itemAnimator);
    }

    /**
     * method that initializes Presenter
     */
    private void initPresenter() {
        presenter.attachView(this);
        presenter.attachRemote();
    }

    /**
     * method that will show the error if no results were found
     */
    @Override
    public void showError(String error) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvError.getLayoutParams();
        params.height = 70;
        mTvError.setLayoutParams(params);
        mTvError.setText(error);
    }

    /**
     * method that is called after data has been downloaded
     */
    @Override
    public void updateUI(Posts posts) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvError.getLayoutParams();
        params.height = 0;
        mTvError.setLayoutParams(params);
        if (adapter == null) {
            if(posts != null) {
                adapter = new PostAdapter(posts.getData().getChildren(), getApplicationContext());
                mRvRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (posts.getData().getChildren().size() == 0) {
                    showError("No Results");
                }
            }else{
                showError("No results");
            }
        } else {
            if(posts != null) {
                adapter.setPosts(posts.getData().getChildren());
                adapter.notifyDataSetChanged();
                if (posts.getData().getChildren().size() == 0) {
                    showError("No Results");
                }
            }else{
                showError("No Results");
            }
        }

    }

    @OnClick(R.id.btnSearch)
    public void onViewClicked() {
        String query = mSearchView.getQuery().toString();
        presenter.makeRestCallForReddit​Post(query.trim());
    }
}
