package com.ws.design.coco_ecommerce_ui_kit;


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
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailActivity;

import java.util.ArrayList;

import Adapter.RecycleAdapteHomeBanner;
import Adapter.RecycleAdapteHomeCategory;
import Adapter.RecycleAdapteTopTenHome;
import Model.HomeBannerModelClass;
import Model.HomeCategoryModelClass;
import Model.TopTenModelClass;
import fragment.CustomItemClickListener;
import fragment.FragmentManagerUtils;
import fragment.ToolbarBaseFragment;

public class HomeActivity extends ToolbarBaseFragment {


    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView recyclerView;
    private RecycleAdapteHomeBanner mAdapter;
    private Integer image[] ={R.drawable.image95,R.drawable.image95,R.drawable.image95,R.drawable.image95};




    private ArrayList<HomeCategoryModelClass> homeCategoryModelClasses;
    private RecyclerView category_recyclerView;
    private RecycleAdapteHomeCategory mAdapter1;
    private String title[] ={"All Categories","Mens","Womens","Electronics","Home and Furniture","Sports"};



    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;
    private Integer image1[]={R.drawable.ac,R.drawable.headphones,R.drawable.ac,R.drawable.headphones};
    private String title1[] ={"Vigo Atom Personal Air Condi....","Bosh Head Phone Blue Color","Vigo Atom Personal Air Condi....","Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid","HeadPhones","Kitenid","HeadPhones"};




    private ArrayList<TopTenModelClass> topTenModelClasses1;
    private RecyclerView like_recyclerview;
    private RecycleAdapteTopTenHome mAdapter3;
    private Integer image2[]={R.drawable.mobile1,R.drawable.mobile2,R.drawable.mobile1,R.drawable.mobile2};
    private String title2[] ={"Samsung On Mask 2GB Ram","Samsung Galaxy 8 6GB Ram","Samsung On Mask 2GB Ram","Samsung Galaxy 8 6GB Ram"};
    private String type2[] = {"Phones","Phones","Phones","Phones"};
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_coco_homepage, container, false);

        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        Homepage Banner Recyclerview Code is here

        recyclerView = (RecyclerView)mView.findViewById(R.id.recyclerview);

        homeBannerModelClasses = new ArrayList<>();



        for (int i = 0; i < image.length; i++) {
            HomeBannerModelClass beanClassForRecyclerView_contacts = new HomeBannerModelClass(image[i]);

            homeBannerModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapteHomeBanner(getActivity(), homeBannerModelClasses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);




        //        Category Recyclerview Code is here

        category_recyclerView = (RecyclerView)mView.findViewById(R.id.category_recyclerview);

        homeCategoryModelClasses = new ArrayList<>();



        for (int i = 0; i < title.length; i++) {
            HomeCategoryModelClass beanClassForRecyclerView_contacts = new HomeCategoryModelClass(title[i]);

            homeCategoryModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter1 = new RecycleAdapteHomeCategory(getActivity(), homeCategoryModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ExploreActivity(), null, false, false);
            }
        });
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(mLayoutManager1);


        category_recyclerView.setLayoutManager(mLayoutManager1);
        category_recyclerView.setItemAnimator(new DefaultItemAnimator());
        category_recyclerView.setAdapter(mAdapter1);



        //        Top Ten  Recyclerview Code is here

        top_ten_crecyclerview = (RecyclerView)mView.findViewById(R.id.top_ten_recyclerview);

        topTenModelClasses = new ArrayList<>();



        for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i],title1[i],type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter2 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ProductDetailActivity(), null, false, false);
            }
        });
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);


        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);
        top_ten_crecyclerview.setItemAnimator(new DefaultItemAnimator());
        top_ten_crecyclerview.setAdapter(mAdapter2);




        //      Like  Recyclerview Code is here

        like_recyclerview = (RecyclerView)mView.findViewById(R.id.like_recyclerview);

        topTenModelClasses1 = new ArrayList<>();



        for (int i = 0; i < image2.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image2[i],title2[i],type2[i]);

            topTenModelClasses1.add(beanClassForRecyclerView_contacts);
        }


        mAdapter3 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses1, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ProductDetailActivity(), null, false, false);
            }
        });
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        like_recyclerview.setLayoutManager(mLayoutManager3);


        like_recyclerview.setLayoutManager(mLayoutManager3);
        like_recyclerview.setItemAnimator(new DefaultItemAnimator());
        like_recyclerview.setAdapter(mAdapter3);


        //        Recent  Recyclerview Code is here

        top_ten_crecyclerview = (RecyclerView)mView.findViewById(R.id.recent_recyclerview);

        topTenModelClasses = new ArrayList<>();



        for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i],title1[i],type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter2 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new ProductDetailActivity(), null, false, false);
            }
        });
        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        top_ten_crecyclerview.setLayoutManager(mLayoutManager4);


        top_ten_crecyclerview.setLayoutManager(mLayoutManager4);
        top_ten_crecyclerview.setItemAnimator(new DefaultItemAnimator());
        top_ten_crecyclerview.setAdapter(mAdapter2);


    }
}
