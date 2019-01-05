package com.feedfacts.example.factfeeds.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feedfacts.example.factfeeds.R;
import com.feedfacts.example.factfeeds.adpater.FactFeedAdapter;
import com.feedfacts.example.factfeeds.basemvp.BaseAppActivity;
import com.feedfacts.example.factfeeds.model.FactFeed;
import com.feedfacts.example.factfeeds.presenter.FactFeedsPresenterImpl;
import com.feedfacts.example.factfeeds.utils.CollectionUtil;
import com.feedfacts.example.factfeeds.utils.DialogUtil;
import com.feedfacts.example.factfeeds.view.FactFeedView;

public class MainActivity extends BaseAppActivity<FactFeedsPresenterImpl> implements
        FactFeedView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mFactFeedList;
    private TextView tvNoFeedsAvailableMessage;
    private FactFeedAdapter mFactFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.feedfacts.example.factfeeds.R.layout.activity_main);
        initialiseUi();
        getFeeds();
    }

    @Override
    protected FactFeedsPresenterImpl getPresenter() {
        return new FactFeedsPresenterImpl(this);
    }

    /**
     * Initialises UI components.
     */
    private void initialiseUi() {
        mFactFeedList = (RecyclerView) findViewById(R.id.recycler_fact);
        tvNoFeedsAvailableMessage = (TextView) findViewById(R.id.txt_data_error);

        /*final Toolbar toolbar = (Toolbar) findViewById(R.id.);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.fetching_fact_feeds));*/


        mFactFeedAdapter = new FactFeedAdapter(null);
        mFactFeedList.setLayoutManager(new LinearLayoutManager(this));
        mFactFeedList.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        mFactFeedList.addItemDecoration(dividerItemDecoration);
        mFactFeedList.setAdapter(mFactFeedAdapter);
    }

    private void getFeeds() {
        if (checkInternet()) {
            mPresenter.getFactFeeds();
        }
    }
    @Override
    public void showFeeds(FactFeed fact) {
        getSupportActionBar().setTitle(fact.getTitle());
        if (CollectionUtil.isEmpty(fact.getRows())) {
            showNoFeedsAvailableError();
        } else {
            mFactFeedList.setVisibility(View.VISIBLE);
            tvNoFeedsAvailableMessage.setVisibility(View.GONE);
            mFactFeedAdapter.refreshList(fact.getRows());
        }
    }

    @Override
    public void showError(String error) {
        getSupportActionBar().setTitle(getString(R.string.error));
        DialogUtil.showErrorDialog(this, getString(R.string.error), error,
                new DialogUtil.OnDialogClick() {
                    @Override
                    public void onPositiveButtonClick(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void showNoFeedsAvailableError() {
        mFactFeedList.setVisibility(View.GONE);
        tvNoFeedsAvailableMessage.setVisibility(View.VISIBLE);
    }

    /**
     * Handles no internet connection error dialogs positive button click.
     *
     * @param dialog
     */
    @Override
    public void onPositiveButtonClick(DialogInterface dialog) {
        getSupportActionBar().setTitle(getString(R.string.error));
        dialog.dismiss();
    }
}
