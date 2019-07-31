package com.ws.design.coco_ecommerce_ui_kit.base_fragment;

import android.content.Context;
import android.view.View;

import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFragmentListener;

import fragment.ToolbarBaseFragment;


public abstract class BaseFragment extends ToolbarBaseFragment implements View.OnClickListener {

    View mRootview;
    private IFragmentListener mListener;


    @Override
    public void onResume() {
        super.onResume();

        if (this.mListener != null) {
            this.mListener.setScreenTitle(getActionbarTitle());
            this.mListener.isCartIconVisible(isCartIconVisible());
            this.mListener.isSearchIconVisible(isSearchIconVisible());

        }
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DrawerActivity) context;
    }

    protected String getActionbarTitle() {
        return "";
    }

    protected boolean isCartIconVisible() {
        return true;
    }

    protected boolean isSearchIconVisible() {
        return true;
    }


}
