package com.ws.design.coco_ecommerce_ui_kit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.RecycleAdapteCocoList;
import Model.CocoListModelClass;

public class CocoListActivity extends AppCompatActivity {



    private ArrayList<CocoListModelClass> cocoListModelClasses;

    private RecyclerView recyclerView;
    private RecycleAdapteCocoList mAdapter;


    private String title[] = {"1 HomePage","2 Navigation","3 Explore","4 Category","5 Kitchen","6 Product_Detail","7 Product Detail Grid",
    "8 Product List","9 Filter","10 Coco Search","11 Coco Search1","12 Coco Search2","13 Coco Search3","14 MyOrder","15 My Account",
            "16 Navigation1","17 Navigation2","18 Add New Address","19 Crate Instant Account","20 Cart","21 Cart1","22 WishList","23 Login","24 Login1",
    "25 Mobile Verification","26 Coco SignUp1","27 Coco SignUp2","28 Review","29 Coco Filter1",

            "30 CocoEcommerceHomeActivity","31 CocoProductListGrid2Activity","32 CocoecommerceHome2Activity"
            ,"33 CocoProductListGrid10Activity","34 CocoProductListGridwithTabActivity","35 Cocoreviews2Activity"
            ,"36Cocoproductlist3Activity",

            "37 Coco_product_list_grid_8","38 CocoProductDetails3","39 CocoProductDetails2","40 CocoProductDetails4",

            "41 Cocoproductlistgrid4","42 Cocoproductlistgrid5","43 Cocoproductlistgrid6","44 Cocoproductlistgrid7"
            ,"45 Cocoproductlistgrid9"
            ,"46 Reviews","47 Cocoproductdetail5","gdfhjkghdfg"};


    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder() {
            super(new TextView(getApplicationContext()));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_list);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);



        recyclerView.invalidate();
        recyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {


            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder();
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText("item: " + position);
                holder.itemView.setPadding(10,10,10,10);
            }


            @Override
            public int getItemCount() {
                return 50;
            }
        });


        cocoListModelClasses = new ArrayList<>();



        for (int i = 0; i < title.length; i++) {
            CocoListModelClass beanClassForRecyclerView_contacts = new CocoListModelClass(title[i]);

            cocoListModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapteCocoList(CocoListActivity.this,cocoListModelClasses);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CocoListActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
