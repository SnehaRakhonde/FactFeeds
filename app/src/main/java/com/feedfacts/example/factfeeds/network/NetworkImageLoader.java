package com.feedfacts.example.factfeeds.network;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class NetworkImageLoader {
    private String mUrl;
    private Context mContext;
    private ImageView mImageView;

    private int mErrorDrawable;
    private int mPlaceHolderDrawable;

    private NetworkImageLoader(ImageLoaderBuilder imageLoaderBuilder) {
        mUrl = imageLoaderBuilder.mUrl;
        mContext = imageLoaderBuilder.mContext;
        mImageView = imageLoaderBuilder.mImageView;
        mErrorDrawable = imageLoaderBuilder.mErrorDrawable;
        mPlaceHolderDrawable = imageLoaderBuilder.mPlaceHolderDrawable;
    }

    public void load() {
        final RequestCreator picasso = Picasso.with(mContext)
                .load(mUrl).networkPolicy(NetworkPolicy.OFFLINE);

        if (mErrorDrawable > 0) {
            picasso.error(mErrorDrawable);
        }
        if (mPlaceHolderDrawable > 0) {
            picasso.placeholder(mPlaceHolderDrawable);
        }

        picasso.into(mImageView, new Callback() {
            @Override
            public void onSuccess() {
                //No Implementation.
            }

            @Override
            public void onError() {
                Picasso.with(mContext).load(mUrl).into(mImageView);
            }
        });
    }

    /**
     * Image loader Builder.
     */
    public static class ImageLoaderBuilder {
        private String mUrl;
        private Context mContext;
        private ImageView mImageView;

        private int mErrorDrawable;
        private int mPlaceHolderDrawable;

        public ImageLoaderBuilder(String mUrl, Context mContext, ImageView mImageView) {
            this.mUrl = mUrl;
            this.mContext = mContext;
            this.mImageView = mImageView;
        }

        public ImageLoaderBuilder setErrorDrawable(int mErrorDrawable) {
            this.mErrorDrawable = mErrorDrawable;
            return this;
        }

        public ImageLoaderBuilder setPlaceHolderDrawable(int mPlaceHolderDrawable) {
            this.mPlaceHolderDrawable = mPlaceHolderDrawable;
            return this;
        }

        public NetworkImageLoader build() {
            return new NetworkImageLoader(this);
        }
    }
}