package com.ws.design.coco_ecommerce_ui_kit.legal_policies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;

import fragment.FragmentManagerUtils;

public class LegalPoliciesFragment extends BaseFragment implements View.OnClickListener {


    private View mView;
    private TextView txtPrivacyPolicy;
    private TextView txtTermsConditions;
    private TextView txtReturningPolicy;
    private TextView txtPaymentSecurity;
    private TextView txtShipping;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_legal_policies, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        txtPrivacyPolicy = view.findViewById(R.id.txtPrivacyPolicy);
        txtTermsConditions = view.findViewById(R.id.txtTermsConditions);
        txtReturningPolicy = view.findViewById(R.id.txtReturningPolicy);
        txtPaymentSecurity = view.findViewById(R.id.txtPaymentSecurity);
        txtShipping = view.findViewById(R.id.txtShipping);

        txtPrivacyPolicy.setOnClickListener(this);
        txtTermsConditions.setOnClickListener(this);
        txtReturningPolicy.setOnClickListener(this);
        txtPaymentSecurity.setOnClickListener(this);
        txtShipping.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        String screenTitle = "";
        String webUrl = "";
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtPrivacyPolicy:
                    screenTitle = txtPrivacyPolicy.getText().toString().trim();
                    webUrl = "https://www.richkart.com/privacy-policy";
                    openWebView(webUrl, screenTitle);
                    break;

                case R.id.txtTermsConditions:
                    screenTitle = txtTermsConditions.getText().toString().trim();
                    webUrl = "https://www.richkart.com/terms-conditions";
                    openWebView(webUrl, screenTitle);
                    break;

                case R.id.txtReturningPolicy:
                    screenTitle = txtReturningPolicy.getText().toString().trim();
                    webUrl = "https://www.richkart.com/return-policy";
                    openWebView(webUrl, screenTitle);
                    break;

                case R.id.txtPaymentSecurity:
                    screenTitle = txtPaymentSecurity.getText().toString().trim();
                    webUrl = "https://www.richkart.com/payment-security";
                    openWebView(webUrl, screenTitle);
                    break;

                case R.id.txtShipping:
                    screenTitle = txtShipping.getText().toString().trim();
                    webUrl = "https://www.richkart.com/shipping";
                    openWebView(webUrl, screenTitle);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String getActionbarTitle() {
        return getString(R.string.legal_policies);
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    private void openWebView(String webUrl, String screenTitle) {
        Bundle bundle = new Bundle();
        bundle.putString("webUrl", webUrl);
        bundle.putString("screenTitle", screenTitle);
        TermsConditionFragment termsConditionFragment = new TermsConditionFragment();
        termsConditionFragment.setArguments(bundle);
        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), termsConditionFragment, null, true, false);

    }
}
