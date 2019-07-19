package com.ws.design.coco_ecommerce_ui_kit.product_details;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.RemoveWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.ReviewActivity;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import Adapter.ViewpagerProductDetailsAdapter;
import Model.TopTenModelClass;
import fragment.ToolbarBaseFragment;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class ProductDetailFragment extends ToolbarBaseFragment implements ProductDetailsView, View.OnClickListener {

    RelativeLayout rightNav;

    LinearLayout linear1, linear2, linear3, linear4;
    TextView txt1, txt2, txt3, txt4;

    LinearLayout right1, right2, right3;
    ImageView right1_imag, right2_imag, right3_imag;


    private ViewPager viewPager;
    private ViewpagerProductDetailsAdapter viewpagerAdapter;


    private ArrayList<TopTenModelClass> topTenModelClasses;
//    private RecyclerView top_ten_crecyclerview;
//    private RecycleAdapteTopTenHome mAdapter2;
//    private Integer image1[]={R.drawable.ac,R.drawable.headphones,R.drawable.ac,R.drawable.headphones};
//    private String title1[] ={"Vigo Atom Personal Air Condi....","Bosh Head Phone Blue Color","Vigo Atom Personal Air Condi....","Bosh Head Phone Blue Color",};
//    private String type[] = {"Kitenid","HeadPhones","Kitenid","HeadPhones"};

    private RecyclerView rvTopRatedProducts;
    private ArrayList<ProductDetailsSimilier> productDetailsSimilierArrayList = new ArrayList<>();
    private ProductDetailsTopRatedProductsAdapter productDetailsTopRatedProductsAdapter;

    private View mView;

    private ProductDetailsPresenter productDetailsPresenter;
    private TextView txtAddToWishlist;
    private TextView txtAddToCart;

    private ArrayList<String> productDetailsImagesArrayList = new ArrayList<>();
    private ProductDetailsViewPager productDetailsViewPager;
    private TextView txtProductName;
    private TextView txtProductPrice;
    private TextView txtRating;
    private TextView txtProductSalePrice;
    private String productSlug = null;
    private String productId = null;
    private String productQty = null;
    private TextView txtRemoveWishlist;
    private String screen;
    private String wishListId;
    private TextView txtReview;
    private TextView txtBuyNow;
    private boolean isClickOnBuyNow = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_product_detail, container, false);


        Bundle bundle = getArguments();
        productSlug = bundle.getString("productSlug");
        productId = bundle.getString("productId");
        productQty = bundle.getString("productQty");

        if (bundle.containsKey("screen")) {
            screen = bundle.getString("screen");

        }

        if (bundle.containsKey("wishlistId")) {
            wishListId = bundle.getString("wishlistId");

        }


        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        productDetailsPresenter = new ProductDetailsPresenter(this);

        if (Util.isDeviceOnline(getActivity())) {
            productDetailsPresenter.getProductDetails(productSlug);

        } else {
            showCenteredToast(getActivity(), getString(R.string.network_connection));

        }

        txtBuyNow = mView.findViewById(R.id.txtBuyNow);
        txtAddToWishlist = mView.findViewById(R.id.txtAddToWishlist);
        txtRemoveWishlist = mView.findViewById(R.id.txtRemoveWishlist);
        txtAddToCart = mView.findViewById(R.id.txtAddToCart);
        txtProductName = mView.findViewById(R.id.txtProductName);
        txtProductPrice = mView.findViewById(R.id.txtProductPrice);
        txtProductSalePrice = mView.findViewById(R.id.txtProductSalePrice);
        rvTopRatedProducts = mView.findViewById(R.id.rvTopRatedProducts);
        txtRating = mView.findViewById(R.id.txtRating);
        txtReview = mView.findViewById(R.id.txtReview);
        txtAddToWishlist.setOnClickListener(this);
        txtRemoveWishlist.setOnClickListener(this);
        txtAddToCart.setOnClickListener(this);
        txtBuyNow.setOnClickListener(this);


        right1 = mView.findViewById(R.id.right1);
        right2 = mView.findViewById(R.id.right2);
        right3 = mView.findViewById(R.id.right3);

        mView.findViewById(R.id.btn_review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getActivity(), ReviewActivity.class));
                intent.putExtra("productId", productId);
                startActivity(intent);
            }
        });

       /* relative1 = mView.findViewById(R.id.relative1);
        relative2 = mView.findViewById(R.id.relative2);*/
//        relative3 = mView.findViewById(R.id.relative3);
//        relative4 = mView.findViewById(R.id.relative4);

      /*  relative1.setOnClickListener(this);
        relative2.setOnClickListener(this);*/
//        relative3.setOnClickListener(this);
//        relative4.setOnClickListener(this);

        right1.setOnClickListener(this);
        right2.setOnClickListener(this);
        right3.setOnClickListener(this);

        right1_imag = mView.findViewById(R.id.right1_img);
        right2_imag = mView.findViewById(R.id.right2_img);
        right3_imag = mView.findViewById(R.id.right3_img);


        linear1 = (LinearLayout) mView.findViewById(R.id.linear1);
        linear2 = (LinearLayout) mView.findViewById(R.id.linear2);
        linear3 = (LinearLayout) mView.findViewById(R.id.linear3);
        linear4 = (LinearLayout) mView.findViewById(R.id.linear4);


        txt1 = (TextView) mView.findViewById(R.id.txt1);
        txt2 = (TextView) mView.findViewById(R.id.txt2);
        txt3 = (TextView) mView.findViewById(R.id.txt3);
        txt4 = (TextView) mView.findViewById(R.id.txt4);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);

        rightNav = (RelativeLayout) mView.findViewById(R.id.rightNav);
        viewPager = (ViewPager) mView.findViewById(R.id.viewpager_product_detail);
      /*  viewpagerAdapter = new ViewpagerProductDetailsAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewpagerAdapter);*/

        // Images right navigatin
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });


        //        Top Ten  Recyclerview Code is here

//        top_ten_crecyclerview = (RecyclerView) mView.findViewById(R.id.top_ten_recyclerview);

        topTenModelClasses = new ArrayList<>();



       /* for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i],title1[i],type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }

*/
       /* mAdapter2 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });*/
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTopRatedProducts.setLayoutManager(mLayoutManager2);


        rvTopRatedProducts.setLayoutManager(mLayoutManager2);
        rvTopRatedProducts.setItemAnimator(new DefaultItemAnimator());


        if (!TextUtils.isEmpty(screen) && screen.equalsIgnoreCase("MyWishList")) {
            txtRemoveWishlist.setVisibility(View.VISIBLE);
            txtAddToWishlist.setVisibility(View.GONE);
        } else {
            txtRemoveWishlist.setVisibility(View.GONE);
            txtAddToWishlist.setVisibility(View.VISIBLE);
        }
/*
        top_ten_crecyclerview.setAdapter(mAdapter2);
*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.linear1:

                linear1.setBackgroundResource(R.drawable.red_rect_normal);
                linear2.setBackgroundResource(R.drawable.storage_gray_rect);
                linear3.setBackgroundResource(R.drawable.storage_gray_rect);
                linear4.setBackgroundResource(R.drawable.storage_gray_rect);
                txt1.setTextColor(Color.parseColor("#d44334"));
                txt2.setTextColor(Color.parseColor("#d0d0d0"));
                txt3.setTextColor(Color.parseColor("#d0d0d0"));
                txt4.setTextColor(Color.parseColor("#d0d0d0"));


                break;

            case R.id.linear2:

                linear1.setBackgroundResource(R.drawable.storage_gray_rect);
                linear2.setBackgroundResource(R.drawable.red_rect_normal);
                linear3.setBackgroundResource(R.drawable.storage_gray_rect);
                linear4.setBackgroundResource(R.drawable.storage_gray_rect);
                txt1.setTextColor(Color.parseColor("#d0d0d0"));
                txt2.setTextColor(Color.parseColor("#d44334"));
                txt3.setTextColor(Color.parseColor("#d0d0d0"));
                txt4.setTextColor(Color.parseColor("#d0d0d0"));

                break;

            case R.id.linear3:

                linear1.setBackgroundResource(R.drawable.storage_gray_rect);
                linear2.setBackgroundResource(R.drawable.storage_gray_rect);
                linear3.setBackgroundResource(R.drawable.red_rect_normal);
                linear4.setBackgroundResource(R.drawable.storage_gray_rect);
                txt1.setTextColor(Color.parseColor("#d0d0d0"));
                txt2.setTextColor(Color.parseColor("#d0d0d0"));
                txt3.setTextColor(Color.parseColor("#d44334"));
                txt4.setTextColor(Color.parseColor("#d0d0d0"));

                break;

            case R.id.linear4:


                linear1.setBackgroundResource(R.drawable.storage_gray_rect);
                linear2.setBackgroundResource(R.drawable.storage_gray_rect);
                linear3.setBackgroundResource(R.drawable.storage_gray_rect);
                linear4.setBackgroundResource(R.drawable.red_rect_normal);
                txt1.setTextColor(Color.parseColor("#d0d0d0"));
                txt2.setTextColor(Color.parseColor("#d0d0d0"));
                txt3.setTextColor(Color.parseColor("#d0d0d0"));
                txt4.setTextColor(Color.parseColor("#d44334"));

                break;


            case R.id.right1:
                right1_imag.setVisibility(View.VISIBLE);
                right2_imag.setVisibility(View.GONE);
                right3_imag.setVisibility(View.GONE);
                break;

            case R.id.right2:
                right2_imag.setVisibility(View.VISIBLE);
                right1_imag.setVisibility(View.GONE);
                right3_imag.setVisibility(View.GONE);
                break;

            case R.id.right3:
                right3_imag.setVisibility(View.VISIBLE);
                right2_imag.setVisibility(View.GONE);
                right1_imag.setVisibility(View.GONE);
                break;

          /*  case R.id.relative1:
                viewPager.setCurrentItem(0);
                break;

            case R.id.relative2:

                viewPager.setCurrentItem(1);
                break;*/


            case R.id.relative3:

                viewPager.setCurrentItem(2);
                break;


          /*  case R.id.relative4:

                viewPager.setCurrentItem(3);
                break;*/

            case R.id.txtAddToWishlist:
                if (Util.isDeviceOnline(getActivity())) {
                    productDetailsPresenter.addToWishList(CocoPreferences.getUserId(), productId);

                } else {
                    showCenteredToast(getActivity(), getString(R.string.network_connection));

                }
                break;
            case R.id.txtBuyNow:
                if (Util.isDeviceOnline(getActivity())) {
                    isClickOnBuyNow = true;
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, productQty);

                } else {
                    showCenteredToast(getActivity(), getString(R.string.network_connection));

                }

                break;
            case R.id.txtAddToCart:
                if (Util.isDeviceOnline(getActivity())) {
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, productQty);

                } else {
                    showCenteredToast(getActivity(), getString(R.string.network_connection));

                }

                break;
            case R.id.txtRemoveWishlist:

                if (!TextUtils.isEmpty(wishListId)) {

                    if (Util.isDeviceOnline(getActivity())) {
                        productDetailsPresenter.removeWishList(wishListId);

                    } else {
                        showCenteredToast(getActivity(), getString(R.string.network_connection));

                    }

                }
                break;
            default:
                break;

        }
    }

    @Override
    public void showWait() {
        showProDialog(getActivity());
    }

    @Override
    public void removeWait() {
        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {

        showCenteredToast(getActivity(), appErrorMessage);
    }

    @Override
    public void addToWishList(AddToWishListResponse addToWishListResponse) {
        if (!TextUtils.isEmpty(addToWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(addToWishListResponse.getmStatus()))) {
            showCenteredToast(getActivity(), addToWishListResponse.getmMessage());



        } else {
            showCenteredToast(getActivity(), addToWishListResponse.getmMessage());
        }
    }

    @Override
    public void getProductDetails(ProductDetailsResponse productDetailsResponse) {

        if (productDetailsResponse != null) {

            if (productDetailsResponse.getmData() != null) {
                if (productDetailsResponse.getmData().getmProduct() != null) {
                    productDetailsImagesArrayList.clear();
                    productDetailsImagesArrayList.add(productDetailsResponse.getmData().getmProduct().getmProductImg());

                    productDetailsViewPager = new ProductDetailsViewPager(getActivity(), productDetailsImagesArrayList);
                    viewPager.setAdapter(productDetailsViewPager);

                    txtProductName.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmProductName()) ? productDetailsResponse.getmData().getmProduct().getmProductName() : "-");
                    txtProductSalePrice.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmSalePrice()) ? productDetailsResponse.getmData().getmProduct().getmSalePrice() : "-");

                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmPrice())) {
                        txtProductPrice.setText(productDetailsResponse.getmData().getmProduct().getmPrice());
                        txtProductPrice.setPaintFlags(txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        txtProductPrice.setText("-");
                    }


                    txtRating.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmAvgRating()) ? productDetailsResponse.getmData().getmAvgRating() : "0");
                    txtReview.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmAvgRating()) ? productDetailsResponse.getmData().getmAvgRating() : "0");


                    if (productDetailsResponse.getmData().getmProductDetailsSimilier() != null) {
                        productDetailsSimilierArrayList.clear();
                        productDetailsSimilierArrayList.addAll(productDetailsResponse.getmData().getmProductDetailsSimilier());
                        productDetailsTopRatedProductsAdapter = new ProductDetailsTopRatedProductsAdapter(getActivity(), productDetailsSimilierArrayList, ProductDetailFragment.this);
                        rvTopRatedProducts.setAdapter(productDetailsTopRatedProductsAdapter);
                    }


                }

            }
        }
    }

    @Override
    public void addToCart(AddToCartResponse addToCartResponse) {
        if (!TextUtils.isEmpty(addToCartResponse.getmStatus()) && ("1".equalsIgnoreCase(addToCartResponse.getmStatus()))) {
            showCenteredToast(getActivity(), addToCartResponse.getmMessage());

            if (isClickOnBuyNow) {

                Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                intent.putExtra("cartList", addToCartResponse.getmData().getmProductData());
                intent.putExtra("totalPrice", addToCartResponse.getmData().getmTotalPrice());
                startActivity(intent);
            }

        } else {
            showCenteredToast(getActivity(), addToCartResponse.getmMessage());
        }
    }

    @Override
    public void removeWishList(RemoveWishListResponse removeWishListResponse) {
        if (!TextUtils.isEmpty(removeWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(removeWishListResponse.getmStatus()))) {
            showCenteredToast(getActivity(), removeWishListResponse.getmMessage());
            txtAddToWishlist.setVisibility(View.VISIBLE);
            txtRemoveWishlist.setVisibility(View.GONE);
        } else {
            showCenteredToast(getActivity(), removeWishListResponse.getmData());
        }
    }


}