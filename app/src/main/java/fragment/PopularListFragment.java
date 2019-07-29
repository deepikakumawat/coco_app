package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryPresenter;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryView;
import com.ws.design.coco_ecommerce_ui_kit.product_details.AddToCartResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryAdapter;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;


public class PopularListFragment extends Fragment implements View.OnClickListener, ProductByCategoryView {

    private ArrayList<ProductData> productGridModellClasses;
    private RecyclerView recyclerview;
    private ProductByCategoryAdapter mAdapter2;
    ProductByCategoryPresenter productByCategoryPresenter;
    private String catId;
    private ShimmerFrameLayout mShimmerViewContainer;
    private TextView txtNoDataFound;
    private RelativeLayout ryParent;
    private int tabPostion;

    public static PopularListFragment newInstance(String catId, int tabPostion) {
        PopularListFragment fragment = new PopularListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("catId", catId);
        bundle.putInt("tabPostion", tabPostion);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular_list, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productByCategoryPresenter = new ProductByCategoryPresenter(this);

        if (getArguments() != null) {
            catId = getArguments().getString("catId");
            tabPostion = getArguments().getInt("tabPostion", 0);


        }

        ryParent = view.findViewById(R.id.ryParent);
        recyclerview = view.findViewById(R.id.recyclerview);
        txtNoDataFound = view.findViewById(R.id.txtNoDataFound);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        productGridModellClasses = new ArrayList<>();

        if (Util.isDeviceOnline(getActivity())) {
            productByCategoryPresenter.getProductByCat(catId);

        } else {
            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection));

        }


    }

    @Override
    public void showWait() {
//        showProDialog(getActivity());
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void removeWait() {
//        dismissProDialog();
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
//        showCenteredToast(getActivity(), appErrorMessage);
        nodataFound(appErrorMessage);
    }


    @Override
    public void getProductByCategory(ProductByCategoryResponse productByCategoryResponse) {
        if (productByCategoryResponse != null) {
            if (productByCategoryResponse.getmData() != null) {
                if (!productByCategoryResponse.getmData().getmProduct().isEmpty()) {
                    productGridModellClasses.clear();
                    productGridModellClasses.addAll(productByCategoryResponse.getmData().getmProduct());

                    recyclerview.setVisibility(View.VISIBLE);
                    txtNoDataFound.setVisibility(View.GONE);

                   /* if (tabPostion == 0) {
                        sortByRating(productGridModellClasses);

                    } else */if (tabPostion == 1) {
                        sortByLowPrice(productGridModellClasses);
                    } else if (tabPostion == 2) {
                        sortByHighPrice(productGridModellClasses);
                    }

                    mAdapter2 = new ProductByCategoryAdapter(getActivity(), productGridModellClasses, PopularListFragment.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerview.setLayoutManager(mLayoutManager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                }
            }
        }

    }

    private void nodataFound(String appErrorMessage) {
        txtNoDataFound.setVisibility(View.VISIBLE);
        txtNoDataFound.setText(appErrorMessage);
        recyclerview.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.lyProduct:

                    ProductData productData = (ProductData) view.getTag();
                    if (productData != null) {

                        Bundle bundle = new Bundle();
                        bundle.putString("productSlug", productData.getmProductSlug());
                        bundle.putString("productId", productData.getmProductId());
                        bundle.putString("productQty", productData.getmProductQty());

                        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
                        productDetailFragment.setArguments(bundle);

                        FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), productDetailFragment, "ProductDetailFragment", true, false);

                    }

                    break;
                case R.id.lyAddToCart:


                    productData = ((ProductData) view.getTag());

                    if (productData != null) {

                        if (Util.isDeviceOnline(getActivity())) {
                            productByCategoryPresenter.addToCart(CocoPreferences.getUserId(), productData.getmProductId(), "1", "");

                        } else {
                            showCenteredToast(ryParent, getActivity(), getString(R.string.network_connection));

                        }

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
    public void addToCart(AddToCartResponse addToCartResponse) {
        if (!TextUtils.isEmpty(addToCartResponse.getmStatus()) && ("1".equalsIgnoreCase(addToCartResponse.getmStatus()))) {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage());


        } else {
            showCenteredToast(ryParent, getActivity(), addToCartResponse.getmMessage());
        }
    }


    private void sortByRating(ArrayList<ProductData> productDataArrayList) {
        Collections.sort(productDataArrayList, new Comparator<ProductData>() {
            @Override
            public int compare(ProductData productData1, ProductData productData2) {

                int rating1 = 0;
                int rating2 = 0;

                if (!TextUtils.isEmpty(productData1.getmAvgRating())) {
                     rating1 = Integer.parseInt(productData1.getmAvgRating());

                }

                if (!TextUtils.isEmpty(productData2.getmAvgRating())) {
                     rating2 = Integer.parseInt(productData2.getmAvgRating());

                }




                return Integer.compare(rating2, rating1);
            }
        });
    }

    private void sortByLowPrice(ArrayList<ProductData> productDataArrayList) {
        Collections.sort(productDataArrayList, new Comparator<ProductData>() {
            @Override
            public int compare(ProductData productData1, ProductData productData2) {
                int salesPrice2 = Integer.parseInt(productData2.getmSalePrice());
                int salesPrice1 = Integer.parseInt(productData1.getmSalePrice());
                return Integer.compare(salesPrice1, salesPrice2);
            }
        });
    }

    private void sortByHighPrice(ArrayList<ProductData> productDataArrayList) {
        Collections.sort(productDataArrayList, new Comparator<ProductData>() {
            @Override
            public int compare(ProductData productData1, ProductData productData2) {
                int salesPrice2 = Integer.parseInt(productData2.getmSalePrice());
                int salesPrice1 = Integer.parseInt(productData1.getmSalePrice());
                return Integer.compare(salesPrice2, salesPrice1);
            }
        });
    }
}
