package com.nav.richkart;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nav.richkart.base_fragment.BaseFragment;
import com.nav.richkart.checkout.CheckoutFragment;
import com.nav.richkart.fragment.FragmentManagerUtils;
import com.nav.richkart.my_order.TimelineTestFragment;
import com.nav.richkart.my_order_details.MyOrderDetailsAdapter;
import com.nav.richkart.my_order_details.MyOrderDetailsResponse;
import com.nav.richkart.my_order_details.OrderDetailsPresenter;
import com.nav.richkart.my_order_details.OrderDetailsView;
import com.nav.richkart.my_order_details.OrderProduct;
import com.nav.richkart.product_details.AddToCartResponse;
import com.nav.richkart.product_details.ProductDetailFragment;
import com.nav.richkart.shared_preference.CocoPreferences;
import com.nav.richkart.utility.Constant;
import com.nav.richkart.utility.Util;

import java.util.ArrayList;

import static com.nav.richkart.utility.Util.showCenteredToast;

public class ChangeCountryFragment extends BaseFragment  {



    private View mView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_change_country, container, false);



        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {





    }






}
