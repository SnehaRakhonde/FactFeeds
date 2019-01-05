package com.feedfacts.example.factfeeds.network;


import com.feedfacts.example.factfeeds.model.FactFeed;
import com.feedfacts.example.factfeeds.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Web API interface.
 *
 * @author manishpatole
 */

public interface FactFeedsCall {

  /**
   * Searches the facts.
   *
   * @return list of facts.
   */

  @GET(Constants.SEARCH_FEEDS)
  Call<FactFeed> getFactFeeds();
}
