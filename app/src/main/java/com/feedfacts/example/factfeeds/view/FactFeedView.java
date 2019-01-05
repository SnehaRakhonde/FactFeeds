package com.feedfacts.example.factfeeds.view;

import com.feedfacts.example.factfeeds.basemvp.IView;
import com.feedfacts.example.factfeeds.model.FactFeed;

/**
 * Fact feed view contract.
 *
 * @author Sneha Rakhonde
 */

public interface FactFeedView extends IView {

  /**
   * Show feeds.
   *
   * @param fact - to display.
   */
  void showFeeds(FactFeed fact);

  /**
   * Shows error.
   *
   * @param error - to display.
   */
  void showError(String error);


  /**
   * Shows no feeds available error.
   */
  void showNoFeedsAvailableError();
}
