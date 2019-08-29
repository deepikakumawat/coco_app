package com.ws.design.coco_ecommerce_ui_kit.utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.io.File;

public class Util {
    private ProgressBar mProgressBar = null;

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private static ProgressDialog progressDialog;
    private static CustomProgressBar customProgressBar;

    public static Dialog showProDialog(Context context) {
       /* try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = new ProgressDialog(context, R.style.NewDialog);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;*/

        customProgressBar = new CustomProgressBar(context); // In onCreate
        customProgressBar.show(); // To show the progress bar
        return null;
    }

    public static Dialog dismissProDialog() {
       /* try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        if (customProgressBar != null) {
            customProgressBar.hide(); // To show the progress bar

        }


        return null;
    }

    public static boolean isDeviceOnline(Context context) {
        boolean isDeviceOnLine = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            isDeviceOnLine = netInfo != null && netInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeviceOnLine;
    }

    public static void showCenteredToast(View view, Context context, String msg, String result) {
        try {
            if (view != null) {
                Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();

                if (!TextUtils.isEmpty(result)) {
                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.greencolor));

                } else {
                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.red));

                }

                snackbar.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void openKeyBoard(final Context context, View textView) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideKeyBoardMethod(final Context con, View view) {
        try {
            InputMethodManager imm = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ProgressBarHandler(Context context) {

        ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        RelativeLayout rl = new RelativeLayout(context);

        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar);

        layout.addView(rl, params);

        hide();
    }

    public void show() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hide() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    public static void showNoInternetDialog(Context context){
        final Dialog dialog1 = new Dialog(context, R.style.df_dialog);
        dialog1.setContentView(R.layout.dialog_no_internet);
        dialog1.setCancelable(true);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.findViewById(R.id.btnSpinAndWinRedeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }

    public static File getPhotoDirectory(Context context) {
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + context.getString(R.string.app_name) + File.separator + Constant.NAME_PHOTO);
        return createDirectory(directory);
    }

    private static File createDirectory(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
            Log.d("Directory : ", "[ " + directory + " ] is created");
        } else {
            Log.d("Directory : ", "[ " + directory + " ] is already exist");
        }
        return directory;
    }
}
