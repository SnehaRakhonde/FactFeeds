package com.feedfacts.example.factfeeds.network.service;

import com.feedfacts.example.factfeeds.model.FactFeed;
import com.feedfacts.example.factfeeds.network.ExecutorCallback;
import com.feedfacts.example.factfeeds.network.FactFeedsCall;
import com.feedfacts.example.factfeeds.network.NetworkUtilCall;

import retrofit2.Call;

/**
 * Fetches fact feeds from server.
 *
 * @author Sneha Rakhonde
 */

public class FactFeedServiceImpl implements FactFeedService, OnDataFetchResponse<FactFeed> {

  private OnDataFetchResponse<FactFeed> onDataFetchResponse;

  public FactFeedServiceImpl(OnDataFetchResponse<FactFeed> onDataFetchResponse) {
    this.onDataFetchResponse = onDataFetchResponse;
  }

  /**
   * Fetches the fact feeds from appropriate location.
   */
  @Override
  public void fetchFactFeeds() {
    final FactFeedsCall facts = NetworkUtilCall.getRetrofitClient().create(FactFeedsCall.class);
    Call<FactFeed> call = facts.getFactFeeds();
    call.enqueue(new ExecutorCallback<FactFeed>(this));
  }

  @Override
  public void onSuccess(FactFeed response) {
    onDataFetchResponse.onSuccess(response);
  }

  @Override
  public void onFailure(Throwable throwable) {
    onDataFetchResponse.onFailure(throwable);
  }

  @Override
  public void onFailure() {
    onDataFetchResponse.onFailure();
  }

}
