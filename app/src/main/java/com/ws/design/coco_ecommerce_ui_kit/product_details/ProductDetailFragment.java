package com.ws.design.coco_ecommerce_ui_kit.product_details;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutFragment;
import com.ws.design.coco_ecommerce_ui_kit.common_interface.IFragmentListener;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.RemoveWishListResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductAttributeData;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductBroughtData;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductGalleryData;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.TopReview;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.ReviewActivity;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.project_details_response.ProductDetailsSimilier;
import com.ws.design.coco_ecommerce_ui_kit.seller.SellerProductFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.ViewpagerProductDetailsAdapter;
import fragment.FragmentManagerUtils;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class ProductDetailFragment extends BaseFragment implements ProductDetailsView, View.OnClickListener {

    private static final int ADD_REVIEW_ACTION = 201;
    RelativeLayout rightNav;
    private IFragmentListener mListener;


    private ViewPager viewPager;
    private ViewpagerProductDetailsAdapter viewpagerAdapter;


    private RecyclerView rvTopRatedProducts;
    private ArrayList<ProductDetailsSimilier> productDetailsSimilierArrayList = new ArrayList<>();
    private ArrayList<ProductBroughtData> productBroughtDataArrayList = new ArrayList<>();
    private ArrayList<ProductDetailsSimilier> productRecentViewsDataArrayList = new ArrayList<>();
    private ProductDetailsTopRatedProductsAdapter productDetailsTopRatedProductsAdapter;
    private ProductDetailsBroughtDataAdapter productDetailsBroughtDataAdapter;


    private ProductDetailsPresenter productDetailsPresenter;
    private TextView txtAddToWishlist;
    private TextView txtAddToCart;

    private ArrayList<String> productDetailsImagesArrayList = new ArrayList<>();
    private ArrayList<ProductAttributeData> productAttributeDataArrayList = new ArrayList<>();
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
    private LinearLayout lyImageView;
    private RecyclerView rvBroughtProducts;
    private TextView txtUserComment;
    private TextView txtUserNameDate;
    private TextView txtViewAllReview;
    private TextView txtReviewCount;
    private TextView txtRatingCount;
    private TextView txtDiscout;
    private LinearLayout lyTopReview;
    private LinearLayout lyBroughtProduct;
    private LinearLayout lySimilarProduct;
    private TextView txtProductDesSpecValue;
    private TextView txtProductShortDesValue;
    private TextView txtProductDesc;
    private TextView txtSpecification;
    private String productDetails;
    private String productSpecification;
    private String productSpecificationName;
    private RecyclerView rvProductAttr;
    private ProductDetailsAttributeAdapter productDetailsAttributeAdapter;
    private TextView txtProductShortDesc;
    private String productShortDesc;
    private TextView txtSellerName;
    private RecyclerView rvColor;
    private ProductDetailsColorAdapter productDetailsColorAdapter;
    private ProductDetailsSizeAdapter productDetailsSizeAdapter;
    private ProductDetailsRecentViewsProductsAdapter productDetailsRecentViewsProductsAdapter;
    private RecyclerView rvRecentViewsProducts;
    private LinearLayout lyRecentViewsProduct;
    private String sellerId;
    private ColorSizeData selectedColorData;
    private ColorSizeData selectedSizeData;
    private boolean isColorVewVisible = false;
    private boolean isSizeVewVisible = false;
    private LinearLayout lyColor;
    private ScrollView scrollViewTop;
    private RelativeLayout ryParent;
    private ImageView imgVideo;
    private RelativeLayout ryVideo;
    private String videoUrl;
    private LinearLayout lySize;
    private RecyclerView rvSize;
    private TextView txtOutOfStock;
    private ShimmerFrameLayout mShimmerViewContainer;
    boolean isShimmerShow = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mView = inflater.inflate(R.layout.activity_product_detail, container, false);


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


        init(view);


    }

    private void init(View view) {
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        ryVideo = view.findViewById(R.id.ryVideo);
        imgVideo = view.findViewById(R.id.imgVideo);
        ryParent = view.findViewById(R.id.ryParent);
        scrollViewTop = view.findViewById(R.id.scrollViewTop);
        lyColor = view.findViewById(R.id.lyColor);
        rvColor = view.findViewById(R.id.rvColor);
        lySize = view.findViewById(R.id.lySize);
        rvSize = view.findViewById(R.id.rvSize);
        rvProductAttr = view.findViewById(R.id.rvProductAttr);
        txtProductDesc = view.findViewById(R.id.txtProductDesc);
        txtProductShortDesc = view.findViewById(R.id.txtProductShortDesc);
        txtSpecification = view.findViewById(R.id.txtSpecification);
        txtProductDesSpecValue = view.findViewById(R.id.txtProductDesSpecValue);
        txtProductShortDesValue = view.findViewById(R.id.txtProductShortDesValue);
        lySimilarProduct = view.findViewById(R.id.lySimilarProduct);
        lyBroughtProduct = view.findViewById(R.id.lyBroughtProduct);
        lyRecentViewsProduct = view.findViewById(R.id.lyRecentViewsProduct);
        lyTopReview = view.findViewById(R.id.lyTopReview);
        lyImageView = view.findViewById(R.id.lyImageView);
        txtBuyNow = view.findViewById(R.id.txtBuyNow);
        txtOutOfStock = view.findViewById(R.id.txtOutOfStock);
        txtAddToWishlist = view.findViewById(R.id.txtAddToWishlist);
        txtRemoveWishlist = view.findViewById(R.id.txtRemoveWishlist);
        txtAddToCart = view.findViewById(R.id.txtAddToCart);
        txtProductName = view.findViewById(R.id.txtProductName);
        txtSellerName = view.findViewById(R.id.txtSellerName);
        txtProductPrice = view.findViewById(R.id.txtProductPrice);
        txtProductSalePrice = view.findViewById(R.id.txtProductSalePrice);
        rvTopRatedProducts = view.findViewById(R.id.rvTopRatedProducts);
        rvBroughtProducts = view.findViewById(R.id.rvBroughtProducts);
        rvRecentViewsProducts = view.findViewById(R.id.rvRecentViewsProducts);
        txtRating = view.findViewById(R.id.txtRating);
        txtReview = view.findViewById(R.id.txtReview);
        txtUserComment = view.findViewById(R.id.txtUserComment);
        txtUserNameDate = view.findViewById(R.id.txtUserNameDate);
        txtViewAllReview = view.findViewById(R.id.txtViewAllReview);
        txtReviewCount = view.findViewById(R.id.txtReviewCount);
        txtDiscout = view.findViewById(R.id.txtDiscout);
        txtRatingCount = view.findViewById(R.id.txtRatingCount);
        txtAddToWishlist.setOnClickListener(this);
        txtRemoveWishlist.setOnClickListener(this);
        txtAddToCart.setOnClickListener(this);
        txtBuyNow.setOnClickListener(this);
        txtViewAllReview.setOnClickListener(this);
        txtReviewCount.setOnClickListener(this);
        txtRatingCount.setOnClickListener(this);
        txtProductDesc.setOnClickListener(this);
        txtSpecification.setOnClickListener(this);
        txtProductShortDesc.setOnClickListener(this);
        txtSellerName.setOnClickListener(this);
        imgVideo.setOnClickListener(this);


        rightNav = view.findViewById(R.id.rightNav);
        viewPager = view.findViewById(R.id.viewpager_product_detail);

        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = viewPager.getCurrentItem();
                tab++;
                viewPager.setCurrentItem(tab);
            }
        });


        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTopRatedProducts.setLayoutManager(mLayoutManager2);
        rvTopRatedProducts.setLayoutManager(mLayoutManager2);
        rvTopRatedProducts.setItemAnimator(new DefaultItemAnimator());


        RecyclerView.LayoutManager mLayoutManagerBroughtData = new LinearLayoutManager(getActivity());
        rvBroughtProducts.setLayoutManager(mLayoutManagerBroughtData);

        RecyclerView.LayoutManager mLayoutManagerRecentData = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvRecentViewsProducts.setLayoutManager(mLayoutManagerRecentData);
        rvRecentViewsProducts.setLayoutManager(mLayoutManagerRecentData);
        rvRecentViewsProducts.setItemAnimator(new DefaultItemAnimator());


        RecyclerView.LayoutManager mLayoutManagerColorData = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvColor.setLayoutManager(mLayoutManagerColorData);
        rvColor.setLayoutManager(mLayoutManagerColorData);
        rvColor.setItemAnimator(new DefaultItemAnimator());


        RecyclerView.LayoutManager mLayoutManagerSizeData = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvSize.setLayoutManager(mLayoutManagerSizeData);
        rvSize.setLayoutManager(mLayoutManagerSizeData);
        rvSize.setItemAnimator(new DefaultItemAnimator());


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvProductAttr.setLayoutManager(mLayoutManager);
        rvProductAttr.setItemAnimator(new DefaultItemAnimator());


        if (!TextUtils.isEmpty(screen) && screen.equalsIgnoreCase("MyWishList")) {
            txtRemoveWishlist.setVisibility(View.VISIBLE);
            txtAddToWishlist.setVisibility(View.GONE);
        } else {
            txtRemoveWishlist.setVisibility(View.GONE);
            txtAddToWishlist.setVisibility(View.VISIBLE);
        }

        if (Util.isDeviceOnline(getActivity())) {
            productDetailsPresenter.getProductDetails(productId, CocoPreferences.getUserId());

        } else {
            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

        }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.txtAddToWishlist:
                if (Util.isDeviceOnline(getActivity())) {
                    isShimmerShow = false;
                    productDetailsPresenter.addToWishList(CocoPreferences.getUserId(), productId);

                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                }
                break;
            case R.id.txtBuyNow:


                if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {


                    onClickViewButNow();

                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;
            case R.id.txtAddToCart:

                if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {

                    onClickViewAddToCart();

                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;
            case R.id.txtRemoveWishlist:

                if (!TextUtils.isEmpty(wishListId)) {

                    if (Util.isDeviceOnline(getActivity())) {
                        isShimmerShow = false;
                        productDetailsPresenter.removeWishList(wishListId);

                    } else {
                        showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                    }

                }
                break;
            case R.id.txtViewAllReview:
            case R.id.txtRatingCount:
            case R.id.txtReviewCount:
                Intent intent = (new Intent(getActivity(), ReviewActivity.class));
                intent.putExtra("productId", productId);
                startActivityForResult(intent, ADD_REVIEW_ACTION);

                break;

            case R.id.imgAddToCart:


                ProductBroughtData productBroughtData = ((ProductBroughtData) v.getTag());

                if (productBroughtData != null) {

                    if (Util.isDeviceOnline(getActivity())) {
                        isShimmerShow = false;
                        productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productBroughtData.getmProductId(), "1", "");

                    } else {
                        showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                    }

                }
                break;
            case R.id.txtProductDesc:
                unSelectAllTabs();
                selectClickedTab(((TextView) v));
                setProductDes(productDetails);
                break;

            case R.id.txtSpecification:
                unSelectAllTabs();
                selectClickedTab(((TextView) v));
                setProductSpecfication(productAttributeDataArrayList);
                break;
            case R.id.txtProductShortDesc:
                unSelectAllTabs();
                selectClickedTab(((TextView) v));
                setProductShortDes(productShortDesc);
                break;
            case R.id.lyProduct:
                scrollViewTop.fullScroll(ScrollView.FOCUS_UP);

                ProductDetailsSimilier productDetailsSimilier = (ProductDetailsSimilier) v.getTag();
                if (productDetailsSimilier != null) {
                    unSelectAllTabs();
                    onClick(txtProductShortDesc);
                    productId = productDetailsSimilier.getmProductId();

                    isShimmerShow = true;

                    productDetailsPresenter.getProductDetails(productDetailsSimilier.getmProductId(), CocoPreferences.getUserId());
                }

                break;
            case R.id.lyBroughtProduct:

                scrollViewTop.fullScroll(ScrollView.FOCUS_UP);
                productBroughtData = (ProductBroughtData) v.getTag();
                if (productBroughtData != null) {
                    unSelectAllTabs();
                    onClick(txtProductShortDesc);
                    productId = productBroughtData.getmProductId();

                    isShimmerShow = true;

                    productDetailsPresenter.getProductDetails(productBroughtData.getmProductId(), CocoPreferences.getUserId());
                }

                break;
            case R.id.txtSellerName:
                SellerProductFragment sellerProductFragment = new SellerProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString("sellerId", sellerId);
                sellerProductFragment.setArguments(bundle);
                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), sellerProductFragment, "SellerProductFragment", true, false);

                break;
            case R.id.lyColor:

                selectedColorData = ((ColorSizeData) v.getTag());
                if (productDetailsColorAdapter != null) {
                    selectedColorData.setSelected(true);
                    productDetailsColorAdapter.notifyDataSetChanged();
                }

                break;

            case R.id.lySize:

                selectedSizeData = ((ColorSizeData) v.getTag());
                if (productDetailsSizeAdapter != null) {
                    selectedSizeData.setSelected(true);
                    productDetailsSizeAdapter.notifyDataSetChanged();
                }

                break;


            case R.id.imgVideo:
                openYouTube();
                break;
            default:
                break;

        }
    }

    private void onClickViewButNow() {
        if (isColorVewVisible && isSizeVewVisible) {

            if (selectedColorData != null && selectedSizeData != null) {
                if (Util.isDeviceOnline(getActivity())) {
                    isClickOnBuyNow = true;
                    isShimmerShow = false;
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", selectedColorData.getmAttributeId() + "," + selectedSizeData.getmAttributeId());

                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                }
            } else {
                if (selectedColorData == null) {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.select_color),"");

                } else if (selectedSizeData == null) {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.select_size),"");

                }

            }


        } else if (isColorVewVisible) {

            if (selectedColorData != null) {
                if (Util.isDeviceOnline(getActivity())) {
                    isClickOnBuyNow = true;
                    isShimmerShow = false;
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", selectedColorData.getmAttributeId());

                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                }
            } else {
                showCenteredToast(ryParent, getActivity(), getString(R.string.select_color),"");

            }


        } else if (isSizeVewVisible) {

            if (selectedSizeData != null) {
                if (Util.isDeviceOnline(getActivity())) {
                    isClickOnBuyNow = true;
                    isShimmerShow = false;
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", selectedSizeData.getmAttributeId());

                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                }
            } else {
                showCenteredToast(ryParent, getActivity(), getString(R.string.select_size),"");

            }


        } else {
            if (Util.isDeviceOnline(getActivity())) {
                isClickOnBuyNow = true;
                isShimmerShow = false;
                productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", "");

            } else {
                showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

            }
        }
    }

    private void onClickViewAddToCart() {

        if (isColorVewVisible && isSizeVewVisible) {

            if (selectedColorData != null && selectedSizeData != null) {
                if (Util.isDeviceOnline(getActivity())) {

                    isShimmerShow = false;
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", selectedColorData.getmAttributeId() + "," + selectedSizeData.getmAttributeId());

                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                }
            } else {

                if (selectedColorData == null) {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.select_color),"");

                } else if (selectedSizeData == null) {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.select_size),"");

                }


            }

        } else if (isColorVewVisible) {
            if (selectedColorData != null) {
                if (Util.isDeviceOnline(getActivity())) {
                    isShimmerShow = false;
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", selectedColorData.getmAttributeId());

                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                }
            } else {
                showCenteredToast(ryParent, getActivity(), getString(R.string.select_color),"");

            }
        } else if (isSizeVewVisible) {
            if (selectedSizeData != null) {
                if (Util.isDeviceOnline(getActivity())) {
                    isShimmerShow = false;
                    productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", selectedSizeData.getmAttributeId());

                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                }
            } else {
                showCenteredToast(ryParent, getActivity(), getString(R.string.select_size),"");

            }
        } else {
            if (Util.isDeviceOnline(getActivity())) {
                isShimmerShow = false;
                productDetailsPresenter.addToCart(CocoPreferences.getUserId(), productId, "1", "");

            } else {
                showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

            }
        }
    }


    private void selectClickedTab(TextView view) {
        view.setEnabled(false);
        view.setSelected(true);
    }

    private void unSelectAllTabs() {
        txtProductDesc.setSelected(false);
        txtSpecification.setSelected(false);
        txtProductShortDesc.setSelected(false);
        txtProductDesc.setEnabled(true);
        txtSpecification.setEnabled(true);
        txtProductShortDesc.setEnabled(true);
    }

    @Override
    public void showWait() {

        if (isShimmerShow) {
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mShimmerViewContainer.startShimmerAnimation();
        } else {
            showProDialog(getActivity());

        }


    }

    @Override
    public void removeWait() {


        if (isShimmerShow) {
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
        } else {
            dismissProDialog();
        }


    }

    @Override
    public void onFailure(String appErrorMessage) {

        showCenteredToast(ryParent, getActivity(), appErrorMessage,"");
    }

    @Override
    public void addToWishList(AddToWishListResponse addToWishListResponse) {
        if (!TextUtils.isEmpty(addToWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(addToWishListResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), addToWishListResponse.getmMessage(),Constant.API_SUCCESS);


        } else {
            showCenteredToast(ryParent, getActivity(), addToWishListResponse.getmMessage(),"");
        }
    }

    @Override
    public void getProductDetails(ProductDetailsResponse productDetailsResponse) {

        if (productDetailsResponse != null) {

            if (productDetailsResponse.getmData() != null) {
                if (productDetailsResponse.getmData().getmProduct() != null) {
                    productDetailsImagesArrayList.clear();
                    productDetailsImagesArrayList.add(Constant.MEDIA_THUMBNAIL_BASE_URL + productDetailsResponse.getmData().getmProduct().getmProductImg());

                    if (!productDetailsResponse.getmData().getmProductGallery().isEmpty()) {
                        for (ProductGalleryData productGalleryData : productDetailsResponse.getmData().getmProductGallery()) {

                            productDetailsImagesArrayList.add(Constant.MEDIA_THUMBNAIL_BASE_URL + productGalleryData.getmProImgUrl());
                        }
                    }

                    if (!productDetailsResponse.getmData().getmProductAttributes().isEmpty()) {
                        productAttributeDataArrayList.clear();
                        productAttributeDataArrayList.addAll(productDetailsResponse.getmData().getmProductAttributes());
                        setProductSpecfication(productAttributeDataArrayList);
                        setColorCode(productAttributeDataArrayList);
                        setSize(productAttributeDataArrayList);

                    }

                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmProductQty())
                            && Integer.parseInt(productDetailsResponse.getmData().getmProduct().getmProductQty()) == 0) {
                        txtBuyNow.setVisibility(View.GONE);
                        txtAddToCart.setVisibility(View.GONE);
                        txtOutOfStock.setVisibility(View.VISIBLE);
                    } else {
                        txtBuyNow.setVisibility(View.VISIBLE);
                        txtAddToCart.setVisibility(View.VISIBLE);
                        txtOutOfStock.setVisibility(View.GONE);

                    }

                    productDetailsViewPager = new ProductDetailsViewPager(getActivity(), productDetailsImagesArrayList);
                    viewPager.setAdapter(productDetailsViewPager);

                    setLyImages(productDetailsImagesArrayList);
                    topRating(productDetailsResponse.getmData().getmTopReview());

                    txtProductName.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmProductName()) ? productDetailsResponse.getmData().getmProduct().getmProductName() : "-");
                    sellerId = productDetailsResponse.getmData().getmProduct().getmSellerId();
                    txtSellerName.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmSellerName()) ? "Sold by: " + productDetailsResponse.getmData().getmProduct().getmSellerName() : "-");
                    txtProductSalePrice.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmSalePrice()) ? productDetailsResponse.getmData().getmProduct().getmSalePrice() : "-");


                    videoUrl = productDetailsResponse.getmData().getmProduct().getmProductVideo();
                    ryVideo.setVisibility(!TextUtils.isEmpty(videoUrl) ? View.VISIBLE : View.GONE);


                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmPrice())) {
                        txtProductPrice.setText(productDetailsResponse.getmData().getmProduct().getmPrice());
                        txtProductPrice.setPaintFlags(txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        txtProductPrice.setText("-");
                    }

                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmProductDetails())) {
                        productDetails = productDetailsResponse.getmData().getmProduct().getmProductDetails();
                    }
                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmAttrType())) {
                        productSpecification = productDetailsResponse.getmData().getmProduct().getmAttrType();
                    }

                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmAttrName())) {
                        productSpecificationName = productDetailsResponse.getmData().getmProduct().getmAttrName();
                    }
                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmProductShortDetails())) {
                        productShortDesc = productDetailsResponse.getmData().getmProduct().getmProductShortDetails();
                    }


                    setProductDes(productDetails);
                    setProductShortDes(productShortDesc);

                    setTxtDiscout(productDetailsResponse);

                    if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmRatingCount())) {
                        txtReviewCount.setText(productDetailsResponse.getmData().getmRatingCount() + " Reviews");
                        txtRatingCount.setText(productDetailsResponse.getmData().getmRatingCount() + " Reviews");

                    } else {
                        txtReviewCount.setText(0 + " Reviews");
                        txtRatingCount.setText(0 + " Reviews");

                    }

                    txtRating.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmAvgRating()) ? productDetailsResponse.getmData().getmAvgRating() : "0");
                    txtReview.setText(!TextUtils.isEmpty(productDetailsResponse.getmData().getmAvgRating()) ? productDetailsResponse.getmData().getmAvgRating() : "0");


                    if (!productDetailsResponse.getmData().getmProductDetailsSimilier().isEmpty()) {
                        lySimilarProduct.setVisibility(View.VISIBLE);

                        productDetailsSimilierArrayList.clear();
                        productDetailsSimilierArrayList.addAll(productDetailsResponse.getmData().getmProductDetailsSimilier());
                        productDetailsTopRatedProductsAdapter = new ProductDetailsTopRatedProductsAdapter(getActivity(), productDetailsSimilierArrayList, ProductDetailFragment.this);
                        rvTopRatedProducts.setAdapter(productDetailsTopRatedProductsAdapter);
                    } else {
                        lySimilarProduct.setVisibility(View.GONE);
                    }

                    if (!productDetailsResponse.getmData().getmProductBroughtData().isEmpty()) {

                        lyBroughtProduct.setVisibility(View.VISIBLE);

                        productBroughtDataArrayList.clear();
                        productBroughtDataArrayList.addAll(productDetailsResponse.getmData().getmProductBroughtData());
                        productDetailsBroughtDataAdapter = new ProductDetailsBroughtDataAdapter(getActivity(), productBroughtDataArrayList, ProductDetailFragment.this);
                        rvBroughtProducts.setAdapter(productDetailsBroughtDataAdapter);
                    } else {
                        lyBroughtProduct.setVisibility(View.GONE);
                    }


                    if (!productDetailsResponse.getmData().getmUserRecentViews().isEmpty()) {

                        lyRecentViewsProduct.setVisibility(View.VISIBLE);

                        productRecentViewsDataArrayList.clear();
                        productRecentViewsDataArrayList.addAll(productDetailsResponse.getmData().getmUserRecentViews());
                        productDetailsRecentViewsProductsAdapter = new ProductDetailsRecentViewsProductsAdapter(getActivity(), productRecentViewsDataArrayList, ProductDetailFragment.this);
                        rvRecentViewsProducts.setAdapter(productDetailsRecentViewsProductsAdapter);
                    } else {
                        lyRecentViewsProduct.setVisibility(View.GONE);
                    }


                }

            }
        }
    }


    private void openYouTube() {
        try {
            if (!TextUtils.isEmpty(videoUrl)) {
                boolean isAppInstalled = appInstalledOrNot(Constant.YOUTUBE_PACKAGES_NAME);

                if (isAppInstalled) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(videoUrl));
                    intent.setPackage(Constant.YOUTUBE_PACKAGES_NAME);
                    startActivity(intent);
                } else {
                    showCenteredToast(ryParent, getActivity(), getString(R.string.app_not_insalled),"");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            showCenteredToast(ryParent, getActivity(), getString(R.string.somethingWentWrong),"");
        }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    private void setProductShortDes(String productShortDese) {
        try {
            rvProductAttr.setVisibility(View.GONE);
            txtProductDesSpecValue.setVisibility(View.GONE);
            txtProductShortDesValue.setVisibility(View.VISIBLE);

            if (!TextUtils.isEmpty(productShortDese)) {
                txtProductShortDesValue.setText(Html.fromHtml(productShortDese));
            } else {
                txtProductShortDesValue.setText("-");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setProductDes(String productDese) {
        try {
            txtProductShortDesValue.setVisibility(View.GONE);
            rvProductAttr.setVisibility(View.GONE);
            txtProductDesSpecValue.setVisibility(View.VISIBLE);

            if (!TextUtils.isEmpty(productDese)) {
                txtProductDesSpecValue.setText(Html.fromHtml(productDese));
            } else {
                txtProductDesSpecValue.setText("-");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setColorCode(ArrayList<ProductAttributeData> productAttributeDataArrayList) {
        String attributeRelatedData = "";
        String attributeId = "";
        String attributeName = "";
        isColorVewVisible = false;
        try {
            for (ProductAttributeData productAttributeData : productAttributeDataArrayList) {
                if (productAttributeData.getmAttrType().equalsIgnoreCase("COLOR")) {

                    attributeRelatedData = productAttributeData.getmAttributeRelatedData();
                    attributeId = productAttributeData.getmAttributeId();
                    attributeName = productAttributeData.getmAttributeName();
                }
            }

            if (!TextUtils.isEmpty(attributeRelatedData)) {
                List<String> attributeRelatedDataList = Arrays.asList(attributeRelatedData.split("\\s*,\\s*"));
                List<String> attrIdList = Arrays.asList(attributeId.split("\\s*,\\s*"));
                List<String> attrTypeList = Arrays.asList(attributeName.split("\\s*,\\s*"));


                List<ColorSizeData> colorCodeList = new ArrayList<>();


                for (int i = 0; i < attributeRelatedDataList.size(); i++) {

                    ColorSizeData colorSizeData = new ColorSizeData();

                    if (!TextUtils.isEmpty(attributeRelatedDataList.get(i)) && !attributeRelatedDataList.get(i).equalsIgnoreCase("NO")) {

                        colorSizeData.setmAttrbuteRelatedData(attributeRelatedDataList.get(i));
                        colorSizeData.setmAttributeId(attrIdList.get(i));
                        colorSizeData.setmAttrbuteType(attrTypeList.get(i));
                        colorSizeData.setSelected(false);

                        isColorVewVisible = true;

                    }

                    colorCodeList.add(colorSizeData);
                }


                if (isColorVewVisible) {

                    lyColor.setVisibility(View.VISIBLE);
                    productDetailsColorAdapter = new ProductDetailsColorAdapter(getActivity(), colorCodeList, ProductDetailFragment.this);
                    rvColor.setAdapter(productDetailsColorAdapter);

                } else {
                    lyColor.setVisibility(View.GONE);
                }


            } else {
                lyColor.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setSize(ArrayList<ProductAttributeData> productAttributeDataArrayList) {
        String attributeRelatedData = "";
        String attributeId = "";
        String attributeName = "";
        isSizeVewVisible = false;
        try {
            for (ProductAttributeData productAttributeData : productAttributeDataArrayList) {
                if (productAttributeData.getmAttrType().equalsIgnoreCase("SIZE-UK/INDIA")) {

                    attributeRelatedData = productAttributeData.getmAttributeRelatedData();
                    attributeId = productAttributeData.getmAttributeId();
                    attributeName = productAttributeData.getmAttributeName();
                }
            }

            if (!TextUtils.isEmpty(attributeRelatedData)) {
                List<String> attributeRelatedDataList = Arrays.asList(attributeRelatedData.split("\\s*,\\s*"));
                List<String> attrIdList = Arrays.asList(attributeId.split("\\s*,\\s*"));
                List<String> attrTypeList = Arrays.asList(attributeName.split("\\s*,\\s*"));


                List<ColorSizeData> sizeList = new ArrayList<>();


                for (int i = 0; i < attributeRelatedDataList.size(); i++) {

                    ColorSizeData colorSizeData = new ColorSizeData();

                    if (!TextUtils.isEmpty(attributeRelatedDataList.get(i)) && !attributeRelatedDataList.get(i).equalsIgnoreCase("NO")) {

                        colorSizeData.setmAttrbuteRelatedData(attributeRelatedDataList.get(i));
                        colorSizeData.setmAttributeId(attrIdList.get(i));
                        colorSizeData.setmAttrbuteType(attrTypeList.get(i));
                        colorSizeData.setSelected(false);

                        isSizeVewVisible = true;

                    }

                    sizeList.add(colorSizeData);
                }


                if (isSizeVewVisible) {

                    lySize.setVisibility(View.VISIBLE);
                    productDetailsSizeAdapter = new ProductDetailsSizeAdapter(getActivity(), sizeList, ProductDetailFragment.this);
                    rvSize.setAdapter(productDetailsSizeAdapter);

                } else {
                    lySize.setVisibility(View.GONE);
                }


            } else {
                lySize.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setProductSpecfication(ArrayList<ProductAttributeData> productAttributeDataArrayList) {
        try {
            rvProductAttr.setVisibility(View.VISIBLE);
            txtProductDesSpecValue.setVisibility(View.GONE);
            txtProductShortDesValue.setVisibility(View.GONE);


            productDetailsAttributeAdapter = new ProductDetailsAttributeAdapter(getActivity(), productAttributeDataArrayList, ProductDetailFragment.this);
            rvProductAttr.setAdapter(productDetailsAttributeAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setLyImages(ArrayList<String> productDetailsImagesArrayList) {

        lyImageView.removeAllViews();

        LayoutInflater layoutInflater = getLayoutInflater();
        View view;
        for (int i = 0; i < productDetailsImagesArrayList.size(); i++) {
            view = layoutInflater.inflate(R.layout.image_layout, lyImageView, false);
            ImageView imgProduct = view.findViewById(R.id.imgProduct);
            Glide.with(this).load(productDetailsImagesArrayList.get(i)).placeholder(R.drawable.richkart).into(imgProduct);

            imgProduct.setTag(i);
            imgProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(((int) view.getTag()));

                }
            });
            lyImageView.addView(imgProduct);

        }
    }

    private void topRating(TopReview topReview) {
        if (topReview != null) {
            lyTopReview.setVisibility(View.VISIBLE);
            txtUserComment.setText(TextUtils.isEmpty(topReview.getmUserComment()) ? "-" : topReview.getmUserComment());

            if (!TextUtils.isEmpty(topReview.getmUserName()) && !TextUtils.isEmpty(topReview.getmRatingTime())) {
                txtUserNameDate.setText("By " + topReview.getmUserName() + " on " + topReview.getmRatingTime());

            } else if (!TextUtils.isEmpty(topReview.getmUserName()) && TextUtils.isEmpty(topReview.getmRatingTime())) {
                txtUserNameDate.setText("By " + topReview.getmUserName());

            } else if (TextUtils.isEmpty(topReview.getmUserName()) && !TextUtils.isEmpty(topReview.getmRatingTime())) {
                txtUserNameDate.setText("on " + topReview.getmRatingTime());

            } else {
                txtUserNameDate.setText("-");

            }

        } else {
            lyTopReview.setVisibility(View.GONE);
        }

    }

    private void setTxtDiscout(ProductDetailsResponse productDetailsResponse) {

        try {

            if (!TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmPrice()) &&
                    !TextUtils.isEmpty(productDetailsResponse.getmData().getmProduct().getmSalePrice())) {

                double price = 0;
                double salesPrice = 0;

                price = Double.parseDouble(productDetailsResponse.getmData().getmProduct().getmPrice());
                salesPrice = Double.parseDouble(productDetailsResponse.getmData().getmProduct().getmSalePrice());

                double increases = price - salesPrice;
                double divide = increases / price;
                double dicount = divide * 100;

                int dis = (int) dicount;

                txtDiscout.setText(dis + "% off");

            } else {
                txtDiscout.setText("0%");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addToCart(AddToCartResponse addToCartResponse) {
        if (!TextUtils.isEmpty(addToCartResponse.getmStatus()) && ("1".equalsIgnoreCase(addToCartResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(),Constant.API_SUCCESS);

            if (isClickOnBuyNow) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("cartList", addToCartResponse.getmData().getmProductData());
                bundle.putString("totalPrice", addToCartResponse.getmData().getmTotalPrice());

                CheckoutFragment checkoutFragment = new CheckoutFragment();
                checkoutFragment.setArguments(bundle);

                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), checkoutFragment, "CheckoutFragment", true, false);


            }

        } else {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage(),"");
        }
    }

    @Override
    public void removeWishList(RemoveWishListResponse removeWishListResponse) {
        if (!TextUtils.isEmpty(removeWishListResponse.getmStatus()) && ("1".equalsIgnoreCase(removeWishListResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), removeWishListResponse.getmMessage(),Constant.API_SUCCESS);
            txtAddToWishlist.setVisibility(View.VISIBLE);
            txtRemoveWishlist.setVisibility(View.GONE);
        } else {
            showCenteredToast(ryParent, getActivity(), removeWishListResponse.getmMessage(),"");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ADD_REVIEW_ACTION) {
            if (resultCode == Activity.RESULT_OK) {

                if (data != null) {

                    boolean isReviewAdded = data.getBooleanExtra("isReviewAdded", false);

                    if (isReviewAdded) {
                        if (Util.isDeviceOnline(getActivity())) {
                            productDetailsPresenter.getProductDetails(productId, CocoPreferences.getUserId());

                        } else {
                            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection),"");

                        }

                    }
                }


            }
        }
    }


    @Override
    protected String getActionbarTitle() {
        return getString(R.string.product_details);
    }

    @Override
    protected boolean isSearchIconVisible() {
        return true;
    }

    @Override
    protected boolean isCartIconVisible() {
        return true;
    }
}