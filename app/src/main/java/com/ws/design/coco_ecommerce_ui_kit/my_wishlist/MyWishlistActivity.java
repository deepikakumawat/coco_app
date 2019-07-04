package com.ws.design.coco_ecommerce_ui_kit.my_wishlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToWishListResponse;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class MyWishlistActivity extends AppCompatActivity implements MyWishListView, View.OnClickListener {
    TextView txt1;

    private MyWishListAdapter myWishListAdapter;
    private RecyclerView rvMyWishList;
    private ArrayList<MyWishListResponse.ProductData> productDataArrayList = new ArrayList<>();
    private MyWishListPresenter myWishListPresenter;
    private LinearLayout lyEmpty;
    private int removePosstion = -1;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        myWishListPresenter = new MyWishListPresenter(this);

        txt1 = (TextView) findViewById(R.id.txt1);
        lyEmpty = (LinearLayout) findViewById(R.id.lyEmpty);
        imgBack = findViewById(R.id.imgBack);
        rvMyWishList = (RecyclerView) findViewById(R.id.rvMyWishList);
        txt1.setText("Whishlist");

        myWishListPresenter.getMyWishList("87");
        imgBack.setOnClickListener(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMyWishList.setLayoutManager(gridLayoutManager);
//        rvMyWishList.addItemDecoration(new GridSpacingItemDecoration(2, 30, true));



     /*   LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyWishList.setLayoutManager(layoutManager);*/

    }

    @Override
    public void showWait() {
        lyEmpty.setVisibility(View.GONE);
        showProDialog(this);
    }

    @Override
    public void removeWait() {
        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        if (productDataArrayList.isEmpty()) {
            lyEmpty.setVisibility(View.VISIBLE);

        } else {
            lyEmpty.setVisibility(View.GONE);

        }
        showCenteredToast(this, appErrorMessage);
    }

    @Override
    public void getMyWishList(MyWishListResponse myWishListResponse) {
        if (myWishListResponse != null) {
            if (!myWishListResponse.getmMyWishlistData().getmProductData().isEmpty()) {
                productDataArrayList.clear();
                productDataArrayList.addAll(myWishListResponse.getmMyWishlistData().getmProductData());

                myWishListAdapter = new MyWishListAdapter(this, productDataArrayList, MyWishlistActivity.this);
                rvMyWishList.setAdapter(myWishListAdapter);
            }
        }
    }


    @Override
    public void removeWishList(RemoveWishListResponse removeWishListResponse) {
        if (!TextUtils.isEmpty(removeWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(removeWishListResponse.getmStatus()))) {
            showCenteredToast(this, removeWishListResponse.getmData());
            if (myWishListAdapter != null) {
                productDataArrayList.remove(removePosstion);
                myWishListAdapter.notifyDataSetChanged();
            }
        } else {
            showCenteredToast(this, removeWishListResponse.getmData());
        }
    }


    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtProductName:
                    MyWishListResponse.ProductData productData = ((MyWishListResponse.ProductData) view.getTag());
                    removePosstion = (int) view.getTag(R.id.txtProductName);

                    if (productData != null) {

                        myWishListPresenter.removeWishList(productData.getmProductId());

                    }
                    break;
                case R.id.imgBack:
                    finish();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
