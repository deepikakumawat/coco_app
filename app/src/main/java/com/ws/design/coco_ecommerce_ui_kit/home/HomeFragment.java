package com.ws.design.coco_ecommerce_ui_kit.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.ExploreActivity;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Banner;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.Categories;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.DealProducts;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.HomeResponse;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductListByCategoryFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import Model.HomeBannerModelClass;
import Model.HomeCategoryModelClass;
import Model.TopTenModelClass;
import fragment.FragmentManagerUtils;
import fragment.ToolbarBaseFragment;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class HomeFragment extends ToolbarBaseFragment implements View.OnClickListener , HomeView{


    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView rvBanner;
//    private RecycleAdapteHomeBanner mAdapter;
//    private Integer image[] = {R.drawable.image95, R.drawable.image95, R.drawable.image95, R.drawable.image95};
private ArrayList<Banner> bannerArrayList = new ArrayList<>();
private HomeBannerAdapter homeBannerAdapter;

    private ArrayList<HomeCategoryModelClass> homeCategoryModelClasses;
    private RecyclerView rvCategory;
    private HomeCategoryAdapter homeCategoryAdapter;
//    private String title[] = {"All Categories", "Mens", "Womens", "Electronics", "Home and Furniture", "Sports"};


    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView rvTopRatedProducts;
//    private RecycleAdapteTopTenHome mAdapter2;
//    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
//    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
//    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};
    private ArrayList<ProductData> productDataArrayList = new ArrayList<>();
    private HomeTopRatedProductsAdapter homeTopRatedProductsAdapter;


    private ArrayList<DealProducts> dealProductsArrayList = new ArrayList<>();
    private HomeLikeProductsAdapter homeLikeProductsAdapter;
    private RecyclerView rvLike;
//    private RecycleAdapteTopTenHome mAdapter3;
//    private Integer image2[] = {R.drawable.mobile1, R.drawable.mobile2, R.drawable.mobile1, R.drawable.mobile2};
//    private String title2[] = {"Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram", "Samsung On Mask 2GB Ram", "Samsung Galaxy 8 6GB Ram"};
//    private String type2[] = {"Phones", "Phones", "Phones", "Phones"};
    private View mView;
    private HomePresenter homePresenter;
    private ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    private ProductData productData;
    private DealProducts dealProduct;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_home, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        Homepage Banner Recyclerview Code is here

        homePresenter = new HomePresenter(this);

        if (Util.isDeviceOnline(getActivity())) {
            homePresenter.getHomeData();

        }else{
            showCenteredToast(getActivity(), getString(R.string.network_connection));

        }


        rvBanner = (RecyclerView) mView.findViewById(R.id.rvBanner);

        homeBannerModelClasses = new ArrayList<>();


       /* for (int i = 0; i < image.length; i++) {
            HomeBannerModelClass beanClassForRecyclerView_contacts = new HomeBannerModelClass(image[i]);

            homeBannerModelClasses.add(beanClassForRecyclerView_contacts);
        }*/


//        mAdapter = new RecycleAdapteHomeBanner(getActivity(), homeBannerModelClasses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBanner.setLayoutManager(mLayoutManager);


        rvBanner.setLayoutManager(mLayoutManager);
        rvBanner.setItemAnimator(new DefaultItemAnimator());
//        rvBanner.setAdapter(mAdapter);


        //        Category Recyclerview Code is here

        rvCategory = (RecyclerView) mView.findViewById(R.id.rvCategory);

        homeCategoryModelClasses = new ArrayList<>();


       /* for (int i = 0; i < title.length; i++) {
            HomeCategoryModelClass beanClassForRecyclerView_contacts = new HomeCategoryModelClass(title[i]);

            homeCategoryModelClasses.add(beanClassForRecyclerView_contacts);
        }*/



        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCategory.setLayoutManager(mLayoutManager1);


        rvCategory.setLayoutManager(mLayoutManager1);
        rvCategory.setItemAnimator(new DefaultItemAnimator());
//        rvCategory.setAdapter(homeCategoryAdapter);


        //        Top Ten  Recyclerview Code is here

        rvTopRatedProducts = (RecyclerView) mView.findViewById(R.id.rvTopRatedProducts);

        topTenModelClasses = new ArrayList<>();


      /*  for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i], title1[i], type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }
*/

       /* mAdapter2 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ProductDetailFragment(), null, false, false);
            }
        });*/
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTopRatedProducts.setLayoutManager(mLayoutManager2);


        rvTopRatedProducts.setLayoutManager(mLayoutManager2);
        rvTopRatedProducts.setItemAnimator(new DefaultItemAnimator());
//        rvTopRatedProducts.setAdapter(mAdapter2);


        //      Like  Recyclerview Code is here

        rvLike = (RecyclerView) mView.findViewById(R.id.rvLike);

//        topTenModelClasses1 = new ArrayList<>();


       /* for (int i = 0; i < image2.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image2[i], title2[i], type2[i]);

            topTenModelClasses1.add(beanClassForRecyclerView_contacts);
        }


        mAdapter3 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses1, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Bundle bundle = new Bundle();
                bundle.putString("productSlug", productData.getmProductSlug());
                bundle.putString("productId", productData.getmProductId());
                bundle.putString("productQty", productData.getmProductQty());

                ProductDetailFragment productDetailActivity = new ProductDetailFragment();
                productDetailActivity.setArguments(bundle);

                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailActivity, null, false, false);


            }
        });*/
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvLike.setLayoutManager(mLayoutManager3);


        rvLike.setLayoutManager(mLayoutManager3);
        rvLike.setItemAnimator(new DefaultItemAnimator());
//        like_recyclerview.setAdapter(mAdapter3);


        //        Recent  Recyclerview Code is here

        rvTopRatedProducts = (RecyclerView) mView.findViewById(R.id.rvTopRatedProducts);

        topTenModelClasses = new ArrayList<>();


      /*  for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i], title1[i], type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter2 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ProductDetailFragment(), null, false, false);
            }
        });*/
        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTopRatedProducts.setLayoutManager(mLayoutManager4);


        rvTopRatedProducts.setLayoutManager(mLayoutManager4);
        rvTopRatedProducts.setItemAnimator(new DefaultItemAnimator());
//        rvTopRatedProducts.setAdapter(mAdapter2);


    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtCategories:
//                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ExploreActivity(), null, false, false);
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ProductListByCategoryFragment(), null, false, false);

                    break;
                case R.id.lyProduct:

                    productData = (ProductData) view.getTag();
//                    removeCorssPostion = (int) view.getTag(R.id.txtCross);
                    if (productData != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmProductQty());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, null, false, false);

                    }

                    break;

                    case R.id.lyLikeProduct:

                    dealProduct = (DealProducts) view.getTag();
//                    removeCorssPostion = (int) view.getTag(R.id.txtCross);
                    if (dealProduct != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", dealProduct.getmProductSlug());
                        bundle.putString("productId", dealProduct.getmProductId());
                        bundle.putString("productQty", dealProduct.getmProductQty());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, null, false, false);

                    }

                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public void getHomeData(HomeResponse homeResponse) {
        if (homeResponse != null) {
            if(homeResponse.getmData() !=null) {
                if (homeResponse.getmData().getmCategories() != null) {
                    categoriesArrayList.clear();
                    categoriesArrayList.addAll(homeResponse.getmData().getmCategories());
                    homeCategoryAdapter = new HomeCategoryAdapter(getActivity(), categoriesArrayList, HomeFragment.this);
                    rvCategory.setAdapter(homeCategoryAdapter);
                }


                if (homeResponse.getmData().getmBanner1() != null) {
                    bannerArrayList.clear();
                    bannerArrayList.addAll(homeResponse.getmData().getmBanner1());
                    homeBannerAdapter = new HomeBannerAdapter(getActivity(), bannerArrayList, HomeFragment.this);
                    rvBanner.setAdapter(homeBannerAdapter);
                }

                if (homeResponse.getmData().getmProductData() != null) {
                    productDataArrayList.clear();
                    productDataArrayList.addAll(homeResponse.getmData().getmProductData());
                    homeTopRatedProductsAdapter = new HomeTopRatedProductsAdapter(getActivity(), productDataArrayList, HomeFragment.this);
                    rvTopRatedProducts.setAdapter(homeTopRatedProductsAdapter);
                }

                if (homeResponse.getmData().getmDealProducts() != null) {
                    dealProductsArrayList.clear();
                    dealProductsArrayList.addAll(homeResponse.getmData().getmDealProducts());
                    homeLikeProductsAdapter = new HomeLikeProductsAdapter(getActivity(), dealProductsArrayList, HomeFragment.this);
                    rvLike.setAdapter(homeLikeProductsAdapter);
                }




            }
        }

    }
}
