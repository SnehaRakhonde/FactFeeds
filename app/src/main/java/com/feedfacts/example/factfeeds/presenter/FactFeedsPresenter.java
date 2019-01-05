package com.feedfacts.example.factfeeds.presenter;


import com.feedfacts.example.factfeeds.basemvp.IPresenter;

/**
 * Fact feeds presenter contract.
 *
 * @author Sneha Rakhonde
 */

public interface FactFeedsPresenter extends IPresenter {

  /**
   * Fetches the fact feeds.
   */
  void getFactFeeds();

}
