package com.ws.design.coco_ecommerce_ui_kit.utility;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class CustomProgressBar {

    private final RelativeLayout rl;
    private ProgressBar mProgressBar;
    private Context mContext;

    public CustomProgressBar(Context context) {
        mContext = context;

        ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);



        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        rl = new RelativeLayout(context);

        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar, 100, 100);


        layout.addView(rl, params);

        hide();
    }

    public void show() {
        rl.setClickable(true);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hide() {
        rl.setClickable(false);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
