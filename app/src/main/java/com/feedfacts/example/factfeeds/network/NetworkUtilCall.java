package com.feedfacts.example.factfeeds.network;

import com.feedfacts.example.factfeeds.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Returns the retrofit instance.
 *
 * @author Sneha Rakhonde
 */

public class NetworkUtilCall {

  private static Retrofit retrofit;

  /**
   * @return - retrofit client.
   */
  public static Retrofit getRetrofitClient() {

    if (null == retrofit) {
      retrofit = new Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit;
  }
}
