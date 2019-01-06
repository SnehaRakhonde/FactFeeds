package com.feedfacts.example.factfeeds.basemvp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.feedfacts.example.factfeeds.R;
import com.feedfacts.example.factfeeds.network.NetworkUtils;
import com.feedfacts.example.factfeeds.utils.DialogUtil;

/**
 * Base App Activity.
 * Handles all operations in common
 *
 * @param <T> - Presenter implementor.
 * @author Sneha Rakhonde
 */
public abstract class BaseAppActivity <T extends IPresenter> extends AppCompatActivity
        implements DialogUtil.OnDialogClick {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        NetworkUtils.isConnectedToInternet = NetworkUtils.isConnectedToInternet(this);
    }

    protected abstract T getPresenter();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Checks the internet connectivity.
     *
     * @return
     */
    protected boolean checkInternet() {
        if (! NetworkUtils.isConnectedToInternet ) {
            DialogUtil.showErrorDialog(this, getString(R.string.error), getString(R.string.no_internet),
                    this);
            return false;
        }
        return true;
    }

    /**
     * Shows the progress dialog.
     */
    public void showLoading() {
        DialogUtil.showProgressDialog(this);
    }

    /**
     * Hides the progress dialog.
     */
    public void hideLoading() {
        DialogUtil.hideProgressDialog();
    }

    /**
     * Handles the no network connection error dialogs ok click in generic way by finishing the
     * activity.
     *
     * @param dialog
     */
    @Override
    public void onPositiveButtonClick(DialogInterface dialog) {
        dialog.dismiss();
        finish();
    }
}
