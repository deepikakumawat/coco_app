package fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.ProductListGridAdapter;
import Model.ProductListGridModel;

/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class Details extends Fragment {

    Integer mobi[] = {R.drawable.img1, R.drawable.img1,R.drawable.img3,R.drawable.img1};

    private RecyclerView recyclerView;
    private ProductListGridAdapter coco_product_list_gride_adapter;
    private ArrayList<ProductListGridModel> coco_product_list_gride_models;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, container, false);



        recyclerView = view.findViewById(R.id.gride);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        coco_product_list_gride_models = new ArrayList<>();

        for (int i = 0; i < mobi.length; i++) {
            ProductListGridModel ab = new ProductListGridModel(mobi[i]);
            coco_product_list_gride_models.add(ab);
        }
        coco_product_list_gride_adapter = new ProductListGridAdapter(getContext(), coco_product_list_gride_models);
        recyclerView.setAdapter(coco_product_list_gride_adapter);




        return view;




    }
}
