package com.feedfacts.example.factfeeds.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.feedfacts.example.factfeeds.R;

/**
 * Dialog utility.
 *
 * @author Sneha Rakhonde
 */

public class DialogUtil {

  private static final String TAG = DialogUtil.class.getSimpleName();
  private static ProgressDialog sProgressDialog;

  public static void showErrorDialog(Context context, String title, String message,
                                     final OnDialogClick onDialogClick) {
    new AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            if (null != onDialogClick) {
              onDialogClick.onPositiveButtonClick(dialog);
            } else {
              dialog.dismiss();
            }
          }
        })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
  }

  /**
   * Shows progress dialog.
   *
   * @param context - Live context.
   */
  public static void showProgressDialog(Context context) {
    if (null != sProgressDialog && sProgressDialog.isShowing()) {
      sProgressDialog.hide();
    }
    sProgressDialog = new ProgressDialog(context);

    sProgressDialog.setMessage(context.getString(R.string.loading));
    sProgressDialog.setIndeterminate(true);
    sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    sProgressDialog.setCancelable(false);

    if (context instanceof Activity) {
      if (!((Activity) context).isFinishing()) {
        sProgressDialog.show();
      }
    } else {
      sProgressDialog.show();
    }
  }

  /**
   * Hides the progress dialog.
   */
  public static void hideProgressDialog() {
    try {
      if (null != sProgressDialog && sProgressDialog.isShowing()) {

        Context context = sProgressDialog.getContext();

        if (context instanceof Activity) {

          if (!((Activity) context).isFinishing()) {
            sProgressDialog.dismiss();
            sProgressDialog = null;
          }
        } else {
          sProgressDialog.dismiss();
          sProgressDialog = null;
        }
      }
    } catch (IllegalArgumentException e) {
      Log.w(TAG, "Simple ignore the exceprion", e);
    }
  }

  public interface OnDialogClick {
    void onPositiveButtonClick(DialogInterface dialog);
  }
}
