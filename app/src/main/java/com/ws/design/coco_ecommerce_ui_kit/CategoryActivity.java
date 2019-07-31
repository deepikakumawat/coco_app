package com.ws.design.coco_ecommerce_ui_kit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFragmentListener;

import java.util.ArrayList;

import Adapter.RecycleAdapteHomeBanner;
import Model.HomeBannerModelClass;
import Model.Item_Fashion_Model;
import fragment.ToolbarBaseFragment;

public class CategoryActivity extends ToolbarBaseFragment {

    TextView title;


    private ArrayList<HomeBannerModelClass> homeBannerModelClasses;
    private RecyclerView recyclerView;
    private RecycleAdapteHomeBanner mAdapter;
    private Integer image[] ={R.drawable.image95,R.drawable.image95,R.drawable.image95,R.drawable.image95};


    private Item_Fashion_Adapter item_fashion_adapter;
    private RecyclerView recyclerview;
    private View mView;
    private IFragmentListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_category, container, false);
        return  mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setTitle(R.string.str_categories);


        //fashion recyle
        recyclerview = (RecyclerView)mView.findViewById(R.id.recycle_fashion);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recyclerview.setFocusableInTouchMode(false);
        recyclerview.setNestedScrollingEnabled(false);

       ArrayList<Item_Fashion_Model> modelList = new ArrayList<>();

        modelList.add(new Item_Fashion_Model(R.drawable.puma,"Cloths"));
        modelList.add(new Item_Fashion_Model(R.drawable.addidas,"Specks"));
        modelList.add(new Item_Fashion_Model(R.drawable.puma,"Shoes"));
        modelList.add(new Item_Fashion_Model(R.drawable.addidas,"Suits"));

        item_fashion_adapter = new Item_Fashion_Adapter(getActivity(),modelList);
        recyclerview.setAdapter(item_fashion_adapter);


        //brads recyale
        recyclerview = (RecyclerView)mView.findViewById(R.id.recycle_brads);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recyclerview.setFocusableInTouchMode(false);
        recyclerview.setNestedScrollingEnabled(false);

        ArrayList<Item_Fashion_Model> modelList2 = new ArrayList<>();

        modelList2.add(new Item_Fashion_Model(R.drawable.puma,"Puma"));
        modelList2.add(new Item_Fashion_Model(R.drawable.dg,"D&G"));
        modelList2.add(new Item_Fashion_Model(R.drawable.rayban,"Ray ban"));
        modelList2.add(new Item_Fashion_Model(R.drawable.nike,"NIKE"));
        modelList2.add(new Item_Fashion_Model(R.drawable.addidas,"Adidas"));
        modelList2.add(new Item_Fashion_Model(R.drawable.levis,"Levis"));




        item_fashion_adapter = new Item_Fashion_Adapter(getActivity(),modelList2);
        recyclerview.setAdapter(item_fashion_adapter);




        //mens ewear recycle
        recyclerview = (RecyclerView)mView.findViewById(R.id.recycle_mens_wear);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recyclerview.setFocusableInTouchMode(false);
        recyclerview.setNestedScrollingEnabled(false);

        ArrayList<Item_Fashion_Model> modelList3 = new ArrayList<>();

        modelList3.add(new Item_Fashion_Model(R.drawable.puma,"Puma"));
        modelList3.add(new Item_Fashion_Model(R.drawable.dg,"D&G"));
        modelList3.add(new Item_Fashion_Model(R.drawable.rayban,"Ray ban"));
        modelList3.add(new Item_Fashion_Model(R.drawable.rayban,"Ray ban"));





        item_fashion_adapter = new Item_Fashion_Adapter(getActivity(),modelList3);
        recyclerview.setAdapter(item_fashion_adapter);




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



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DrawerActivity) context;

    }

    @Override
    public void onResume() {
        super.onResume();

        if (this.mListener != null ) {
            this.mListener.setScreenTitle(getString(R.string.categories));

        }

    }
}
