package com.ws.design.coco_ecommerce_ui_kit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Model.Item_Expantable_Model;

public class Expantable extends AppCompatActivity {

    private Item_Expantable_Adapter item_expantable_adapter;
    private RecyclerView recyclerview;
    private ArrayList<Item_Expantable_Model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expantable);

        recyclerview = (RecyclerView)findViewById(R.id.recycle_expantable);
        recyclerview.setLayoutManager(new LinearLayoutManager(Expantable.this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setFocusableInTouchMode(false);

        modelList = new ArrayList<>();

        modelList.add(new Item_Expantable_Model("Brand"));
        modelList.add(new Item_Expantable_Model("Color"));
        modelList.add(new Item_Expantable_Model("Color"));
        modelList.add(new Item_Expantable_Model("Color"));
        modelList.add(new Item_Expantable_Model("Color"));
        modelList.add(new Item_Expantable_Model("Color"));
        modelList.add(new Item_Expantable_Model("Color"));
//        modelList.add(new Item_Expantable_Model("CPU Type",false,false,false,false,false,false,false,false));
//        modelList.add(new Item_Expantable_Model("Screen Size",false,false,false,false,false,false,false,false));
//        modelList.add(new Item_Expantable_Model("Body Weight",false,false,false,false,false,false,false,false));
//        modelList.add(new Item_Expantable_Model("Oprating System",false,false,false,false,false,false,false,false));
//        modelList.add(new Item_Expantable_Model("Ram",false,false,false,false,false,false,false,false));
//

        item_expantable_adapter = new Item_Expantable_Adapter(Expantable.this,modelList);
        recyclerview.setAdapter(item_expantable_adapter);



    }
}
