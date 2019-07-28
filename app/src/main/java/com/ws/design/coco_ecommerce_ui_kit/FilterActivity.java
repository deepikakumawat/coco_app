package com.ws.design.coco_ecommerce_ui_kit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.MyExpandableListAdapter;
import Model.Item_Expantable_Model;
import fragment.ToolbarBaseFragment;

public class FilterActivity extends ToolbarBaseFragment {

    TextView title;
    ImageView imageView,icon_toolbar;
    private View mView;
    private Item_Expantable_Adapter item_expantable_adapter;
    private RecyclerView recyclerview;
    private ArrayList<Item_Expantable_Model> modelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.activity_filter, container, false);

        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        title = (TextView)mView.findViewById(R.id.title);
        imageView = (ImageView)mView.findViewById(R.id.imgSearch);


        icon_toolbar = mView.findViewById(R.id.navigation_menu);


        recyclerview = (RecyclerView)mView.findViewById(R.id.recycle_expantable);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setFocusableInTouchMode(false);

        modelList = new ArrayList<>();

        modelList.add(new Item_Expantable_Model("Brand"));
        modelList.add(new Item_Expantable_Model("Color"));
        modelList.add(new Item_Expantable_Model("CPU Type"));
        modelList.add(new Item_Expantable_Model("Screen Size"));
        modelList.add(new Item_Expantable_Model("Body Weight"));
        modelList.add(new Item_Expantable_Model("Operating System"));
        modelList.add(new Item_Expantable_Model("Ram"));


        item_expantable_adapter = new Item_Expantable_Adapter(getActivity(),modelList);
        recyclerview.setAdapter(item_expantable_adapter);


        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) mView.findViewById(R.id.rangeSeekbar1);


        final TextView tvMin = (TextView) mView.findViewById(R.id.textMin1);
        final TextView tvMax = (TextView) mView.findViewById(R.id.textMin2);

// set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText("$ " + String.valueOf(minValue));
                tvMax.setText("$ " + String.valueOf(maxValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });
    }
}
