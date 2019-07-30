package com.ws.design.coco_ecommerce_ui_kit.legal_policies;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
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

public class TermsConditionFragment extends BaseFragment {

    private View mView;

//    private final String webUrl = "https://www.richkart.com/terms-conditions";

    private WebView webView;
    private IFragmentListener mListener;
    private RelativeLayout ryParent;
    private String webUrl;
    private String screenTitle;


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


        if (getArguments() != null) {
            webUrl = getArguments().getString("webUrl");
            screenTitle = getArguments().getString("screenTitle");


        }


        ryParent = view.findViewById(R.id.ryParent);
        webView = view.findViewById(R.id.webView);
        showWebPage();


    }


    private void showWebPage() {


        if (Util.isDeviceOnline(getActivity())) {
            webView.loadUrl(webUrl);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new TermsConditionWebClient());

        } else {
            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection), "");

        }
    }


    private class TermsConditionWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Util.showProDialog(getActivity());
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Util.dismissProDialog();
        }
    }


    @Override
    protected String getActionbarTitle() {
        return screenTitle;
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }
}
