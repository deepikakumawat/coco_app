package com.ws.design.coco_ecommerce_ui_kit.terms_condition;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.common_interface.IFragmentListener;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListAdapter;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListPresenter;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListView;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.RemoveWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import fragment.FragmentManagerUtils;
import fragment.ToolbarBaseFragment;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class TermsConditionFragment extends ToolbarBaseFragment  {

    private View mView;

    private final String url = "https://www.richkart.com/terms-conditions";

    private WebView webView;
    private IFragmentListener mListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_terms_condition, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        webView = view.findViewById(R.id.webView);
        showWebPage();



    }





    private void showWebPage() {


        if (Util.isDeviceOnline(getActivity())) {
            webView.loadUrl(url);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new NavFreshWebClient());

        } else {
            showCenteredToast(getActivity(), getString(R.string.network_connection));

        }
    }





    private class NavFreshWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DrawerActivity) context;

    }

    @Override
    public void onResume() {
        super.onResume();

        if (this.mListener != null ) {
            this.mListener.setScreenTitle(getString(R.string.terms_conditions));

        }

    }


}
