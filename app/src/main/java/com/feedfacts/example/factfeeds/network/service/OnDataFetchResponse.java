package com.feedfacts.example.factfeeds.network.service;

/**
 * Callback to respond back with fact feeds.
 *
 * @author Sneha Rakhonde
 */

public interface OnDataFetchResponse<T> {

  void onSuccess(T response);

  void onFailure(Throwable throwable);

  void onFailure();
}
