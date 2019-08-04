package com.ws.design.coco_ecommerce_ui_kit.categories;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFragmentListener;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class CategoryFragment extends BaseFragment implements CategoriesView {

    TextView title;


    private RecyclerView rvCategory;
    private ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList = new ArrayList<>();


    private AllCategoriesAdapter allCategories_adapter;
    private View mView;
    private IFragmentListener mListener;
    CategoriesPresenter categoriesPresenter;
    private LinearLayout lyAllCategories;
    private LinearLayout lyParent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_category, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        categoriesPresenter = new CategoriesPresenter(this);

        lyParent = mView.findViewById(R.id.lyParent);
        lyAllCategories = mView.findViewById(R.id.lyAllCategories);
        rvCategory = (RecyclerView) mView.findViewById(R.id.rvCategory);
        rvCategory.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        rvCategory.setFocusableInTouchMode(false);
        rvCategory.setNestedScrollingEnabled(false);


        if (Util.isDeviceOnline(getActivity())) {
            categoriesPresenter.getCategories();

        } else {
            showCenteredToast(lyParent, getActivity(), getString(R.string.network_connection),"");

        }



    }


    @Override
    protected String getActionbarTitle() {
        return getString(R.string.categories);
    }

    @Override
    protected boolean isCartIconVisible() {
        return true;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return true;
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(lyParent,getActivity(), appErrorMessage,"");

    }

    @Override
    public void getCategories(CategoriesResponse categoriesResponse) {
        if (categoriesResponse != null) {

            categoriesResponseArrayList.clear();
            categoriesResponseArrayList.addAll(categoriesResponse.getmData().getmMainCategories());

            allCategories_adapter = new AllCategoriesAdapter(getActivity(), categoriesResponseArrayList, CategoryFragment.this);
            rvCategory.setAdapter(allCategories_adapter);

            setAllCategories(categoriesResponse.getmData().getmMainCategories());
        }

    }

    private void setAllCategories(ArrayList<CategoriesResponse.MainCategoriesData> categoriesResponseArrayList) {

        try {


            lyAllCategories.removeAllViews();

            LayoutInflater layoutInflater = getLayoutInflater();
            View view;
            for (int i = 0; i < categoriesResponseArrayList.size(); i++) {
                view = layoutInflater.inflate(R.layout.list_item_catreogires, lyAllCategories, false);

                LinearLayout lySubCategory = view.findViewById(R.id.lySubCategory);
                TextView txtCategories = view.findViewById(R.id.txtCategories);
                txtCategories.setText(categoriesResponseArrayList.get(i).getmCatName());

                /*set subcategories name*/
                lySubCategory.removeAllViews();

                LayoutInflater layoutInflater2 = getLayoutInflater();
                View view2;
                for (int j = 0; j < categoriesResponseArrayList.get(i).getmSubCategories().size(); j++) {


                    view2 = layoutInflater2.inflate(R.layout.list_item_sub_categories, lySubCategory, false);
                    TextView txtSubCategory = view2.findViewById(R.id.txtSubCategory);

                    ImageView imgCategories = view2.findViewById(R.id.imgCategories);


                    txtSubCategory.setText(categoriesResponseArrayList.get(i).getmSubCategories().get(j).getmCatName());

                    String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + categoriesResponseArrayList.get(i).getmSubCategories().get(j).getmCatIconImgId();
                    Glide.with(getActivity()).load(thumbnail).placeholder(R.drawable.richkart).into(imgCategories);


                    lySubCategory.addView(view2, j);


                }


                lyAllCategories.addView(view);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
