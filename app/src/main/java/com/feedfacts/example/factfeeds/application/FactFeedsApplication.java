package com.feedfacts.example.factfeeds.application;

import android.app.Application;

import com.squareup.picasso.Picasso;

/**
 * Application class.
 *
 * @author Sneha Rakhonde
 */

public class FactFeedsApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    initImageCaching();

  }

    /*initialise image caching of facts*/
   private void initImageCaching(){
      Picasso.Builder builder = new Picasso.Builder(this);
    builder.downloader(new com.jakewharton.picasso.OkHttp3Downloader(this, Integer.MAX_VALUE));
    Picasso built = builder.build();
    built.setIndicatorsEnabled(true);
    built.setLoggingEnabled(true);
    Picasso.setSingletonInstance(built);
   }

}
