package com.feedfacts.example.factfeeds.network;




import com.feedfacts.example.factfeeds.network.service.OnDataFetchResponse;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to get all the responses .
 *
 * @author Sneha Rakhonde.
 */

public class ExecutorCallback<T> implements Callback<T> {

  private OnDataFetchResponse onServerCallResponse;

  public ExecutorCallback(OnDataFetchResponse onServerCallResponse) {
    this.onServerCallResponse = onServerCallResponse;
  }

  @Override
  public void onResponse(Call<T> call, Response<T> response) {

    if (null != onServerCallResponse) {
      if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
        onServerCallResponse.onSuccess(response.body());
      } else {
        onServerCallResponse.onFailure();
      }
    }
  }

  @Override
  public void onFailure(Call<T> call, Throwable t) {
    if (null != onServerCallResponse) {
      onServerCallResponse.onFailure(t);
    }
  }

}
