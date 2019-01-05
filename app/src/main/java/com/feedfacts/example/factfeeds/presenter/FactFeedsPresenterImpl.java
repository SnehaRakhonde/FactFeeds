package com.feedfacts.example.factfeeds.presenter;

import com.feedfacts.example.factfeeds.model.FactFeed;
import com.feedfacts.example.factfeeds.network.service.FactFeedService;
import com.feedfacts.example.factfeeds.network.service.FactFeedServiceImpl;
import com.feedfacts.example.factfeeds.network.service.OnDataFetchResponse;
import com.feedfacts.example.factfeeds.view.FactFeedView;

/**
 * Implements Fact feeds presenter.
 *
 * @author Sneha Rakhonde
 */

public class FactFeedsPresenterImpl implements FactFeedsPresenter, OnDataFetchResponse<FactFeed> {

  private FactFeedView factFeedView;
  private FactFeedService factFeedService;

  public FactFeedsPresenterImpl(FactFeedView factFeedView) {
    this.factFeedView = factFeedView;
    factFeedService = new FactFeedServiceImpl(this);
  }

  /**
   * Fetches the fact feeds.
   */
  @Override
  public void getFactFeeds() {
    if (null == factFeedView) {
      return;
    }
    factFeedView.showLoading();
    factFeedService.fetchFactFeeds();
  }

  @Override
  public void onSuccess(FactFeed fact) {
    if (null == factFeedView) {
      return;
    }
    factFeedView.hideLoading();
    if (null == fact) {
      factFeedView.showError("Something went wrong");
      return;
    }

    factFeedView.showFeeds(fact);
  }

  @Override
  public void onFailure(Throwable throwable) {
    if (null == factFeedView) {
      return;
    }
    factFeedView.hideLoading();
    factFeedView.showError(null != throwable ? throwable.getMessage() :
        "Something went wrong");
  }

  @Override
  public void onFailure() {
    if (null == factFeedView) {
      return;
    }
    factFeedView.showError(
       "Something went wrong");
  }
}
