package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryPresenter;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductByCategoryView;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import Adapter.RecycleAdapteProductList;
import Model.ProductGridModellClass;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;


public class PopularListFragment extends Fragment implements View.OnClickListener ,ProductByCategoryView {

    private ArrayList<ProductData> productGridModellClasses;
    private RecyclerView recyclerview;
    private RecycleAdapteProductList mAdapter2;
    ProductByCategoryPresenter productByCategoryPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular_list, container, false);
        productByCategoryPresenter = new ProductByCategoryPresenter(this);

        //      New Arrival Kitchen  Recyclerview Code is here

        recyclerview = (RecyclerView)view.findViewById(R.id.recyclerview);

        productGridModellClasses = new ArrayList<>();




        if (Util.isDeviceOnline(getActivity())) {
            productByCategoryPresenter.getProductByCat("19");

        }else{
            showCenteredToast(getActivity(), getString(R.string.network_connection));

        }



        return view;
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getProductByCategory(ProductByCategoryResponse productByCategoryResponse) {
        if (productByCategoryResponse != null) {
            if(productByCategoryResponse.getmData() !=null) {
                if (productByCategoryResponse.getmData().getmProduct() != null) {
                    productGridModellClasses.clear();
                    productGridModellClasses.addAll(productByCategoryResponse.getmData().getmProduct());
                    mAdapter2 = new RecycleAdapteProductList(getActivity(),productGridModellClasses, PopularListFragment.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerview.setLayoutManager(mLayoutManager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.ly_root:

                    ProductData productData = (ProductData) view.getTag();
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


                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
