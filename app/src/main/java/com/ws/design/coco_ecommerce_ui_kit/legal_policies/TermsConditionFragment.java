package com.ws.design.coco_ecommerce_ui_kit.legal_policies;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFragmentListener;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

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
