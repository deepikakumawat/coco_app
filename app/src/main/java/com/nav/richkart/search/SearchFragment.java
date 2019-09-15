package com.nav.richkart.search;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nav.richkart.R;
import com.nav.richkart.base_fragment.BaseFragment;
import com.nav.richkart.product_details.ProductDetailFragment;
import com.nav.richkart.product_details.project_details_response.ProductDetailsSimilier;
import com.nav.richkart.utility.Util;

import java.util.ArrayList;

import com.nav.richkart.fragment.FragmentManagerUtils;

public class SearchFragment extends BaseFragment implements SearchView, TextWatcher, View.OnClickListener, TextView.OnEditorActionListener {

    private AutoCompleteTextView autoTxtSearch;
    private ArrayList<ProductDetailsSimilier> productDetailsSimilierList = new ArrayList<>();
    private ArrayList<ProductDetailsSimilier> topFiveProductSearchList = new ArrayList<>();
    //    private SearchAutoAdapter searchAutoAdapter;
    private SearchPresenter searchPresenter;
    private LinearLayout lyParent;
    private View mView;
    private ImageView imgSearch;
    private RecyclerView rvProducts;
    private SearchAutoProductAdapter mAdapter2;
    private ImageView imgGoogleSearch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_search, container, false);


        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        searchPresenter = new SearchPresenter(this);


        init(view);


    }

    private void init(View view) {
        lyParent = view.findViewById(R.id.lyParent);
        imgSearch = view.findViewById(R.id.imgSearch);
        autoTxtSearch = view.findViewById(R.id.autoTxtSearch);
        imgGoogleSearch = view.findViewById(R.id.imgGoogleSearch);
        rvProducts = view.findViewById(R.id.rvProducts);
        autoTxtSearch.addTextChangedListener(this);
        autoTxtSearch.setOnEditorActionListener(this);
        imgSearch.setOnClickListener(this);
        imgGoogleSearch.setOnClickListener(this);
    }


    @Override
    public void showWait() {

//       Util.showProDialog(getActivity());
    }

    @Override
    public void removeWait() {
//        Util.dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Util.showCenteredToast(lyParent, getActivity(), appErrorMessage, "");
    }

    @Override
    public void getSearchProduct(SearchResponse searchResponse) {
        if (searchResponse != null) {
            if (!searchResponse.getmSearchData().getmProducts().isEmpty()) {
                productDetailsSimilierList.clear();


                productDetailsSimilierList.addAll(searchResponse.getmSearchData().getmProducts());

//                searchAutoAdapter = new SearchAutoAdapter(getActivity(), R.layout.list_item_search_auto_product, productDetailsSimilierList, SearchFragment.this);
                /*autoTxtSearch.setAdapter(searchAutoAdapter);*/

                mAdapter2 = new SearchAutoProductAdapter(getActivity(), productDetailsSimilierList, SearchFragment.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                rvProducts.setLayoutManager(mLayoutManager);
                rvProducts.setItemAnimator(new DefaultItemAnimator());
                rvProducts.setAdapter(mAdapter2);


            }
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s != null) {
            String str = s.toString().trim();
            if (str.length() >= 3) {
                if (Util.isDeviceOnline(getActivity())) {
                    searchPresenter.getSearchItem(str);

                } else {
                    Util.showNoInternetDialog(getActivity());
                }
            }

            if (str.length() < 3) {
              clearList();
            }
        }
    }

    private void clearList() {
        productDetailsSimilierList.clear();
        if (mAdapter2 != null)
            mAdapter2.notifyDataSetChanged();
        autoTxtSearch.dismissDropDown();
    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtProductName:
                    ProductDetailsSimilier productDetailsSimilier = ((ProductDetailsSimilier) view.getTag());
                    if (productDetailsSimilier != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productDetailsSimilier.getmProductSlug());
                        bundle.putString("productId", productDetailsSimilier.getmProductId());
                        bundle.putString("productQty", productDetailsSimilier.getmProductQty());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }
                    break;
                case R.id.imgSearch:
                    openSearchList(autoTxtSearch.getText().toString());
                    break;
                case R.id.imgGoogleSearch:
                    googleSearch();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void googleSearch() {

        autoTxtSearch.setText("");
        clearList();


        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException a) {

            Util.showCenteredToast(lyParent,getActivity(),"Oops! Your device doesn't support Speech to Text","");
        }
    }

    @Override
    protected boolean isCartIconVisible() {
        return false;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }

    @Override
    protected String getActionbarTitle() {
        return getString(R.string.search_title);
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // Your piece of code on keyboard search click


            openSearchList(textView.getText().toString());

            return true;
        }
        return false;
    }

    private void openSearchList(String searchTerm) {
        Bundle bundle = new Bundle();
        bundle.putString("searchTerm", searchTerm);


        SearchListFragment searchListFragment = new SearchListFragment();
        searchListFragment.setArguments(bundle);

        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), searchListFragment, "SearchListFragment", true, false);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    String yourResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                    Log.d("result", yourResult);
                    autoTxtSearch.setText(yourResult);

                }
                break;
            }
        }
    }
}
