package com.nav.richkart.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.nav.richkart.R;
import com.nav.richkart.utility.Util;

public class ToolbarBaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && getActivity() != null) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.hideKeyboard(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String getTitle() {
        return "";
    }

    public boolean onBackPressed() {
        return true;
    }




    // TODO: Temp code for OOB flow implementation & sprint review
    protected Toolbar mToolbar;
    private int customTitle;

    protected void showHomeButton(boolean showHomeAsUp) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(showHomeAsUp);
        }
    }

    public void setTitle(int title) {
        customTitle = title;
    }

    protected void initToolbar(View fragmentView, boolean showUp) {
        populateToolbar(fragmentView, showUp);
    }

    private void populateToolbar(View fragmentView, boolean showUp) {
        mToolbar = fragmentView.findViewById(R.id.Toolbar);
        mToolbar.setTitle(customTitle);
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            if (showUp) {
                showHomeButton(true);
                mToolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
            } else {
                showHomeButton(false);
            }
            mToolbar.setOnClickListener(null);
        }
    }
}
