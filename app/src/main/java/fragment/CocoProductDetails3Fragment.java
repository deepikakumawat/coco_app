package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;

import Adapter.CocoProductDetailsAdapter;
import Adapter.RecycleAdapteTopTenHome;
import Model.TopTenModelClass;

import me.relex.circleindicator.CircleIndicator;

public class CocoProductDetails3Fragment extends Fragment implements View.OnClickListener {

    ViewPager viewPager;
    private CocoProductDetailsAdapter a1;
    CircleIndicator indicator;

    FrameLayout frames1, frames2, frames3, frames4, frames5;

    TextView text1, text2, text3, text4, text5;

    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;

    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details3fragment, container, false);
        frames1 = view.findViewById(R.id.frames1);
        frames2 = view.findViewById(R.id.frames2);
        frames3 = view.findViewById(R.id.frames3);
        frames4 = view.findViewById(R.id.frames4);
        frames5 = view.findViewById(R.id.frames5);

        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        text3 = view.findViewById(R.id.text3);
        text4 = view.findViewById(R.id.text4);
        text5 = view.findViewById(R.id.text5);


        frames1.setOnClickListener(this);
        frames2.setOnClickListener(this);
        frames3.setOnClickListener(this);
        frames4.setOnClickListener(this);
        frames5.setOnClickListener(this);

        top_ten_crecyclerview = view.findViewById(R.id.top_ten_recyclerview);

        viewPager = view.findViewById(R.id.viewPager);

        CircleIndicator indicator = view.findViewById(R.id.indicator);

        a1 = new CocoProductDetailsAdapter(getChildFragmentManager());

        viewPager.setAdapter(a1);

        indicator.setViewPager(viewPager);

        a1.registerDataSetObserver(indicator.getDataSetObserver());


        topTenModelClasses = new ArrayList<>();

        for (int i = 0; i < image1.length; i++) {
            TopTenModelClass beanClassForRecyclerView_contacts = new TopTenModelClass(image1[i], title1[i], type[i]);

            topTenModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter2 = new RecycleAdapteTopTenHome(getActivity(), topTenModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);


        top_ten_crecyclerview.setLayoutManager(mLayoutManager2);
        top_ten_crecyclerview.setItemAnimator(new DefaultItemAnimator());
        top_ten_crecyclerview.setAdapter(mAdapter2);

        frames1.setBackgroundResource(R.drawable.black_fill_circle);
        frames2.setBackgroundResource(R.drawable.black_border_circle);
        frames3.setBackgroundResource(R.drawable.black_border_circle);
        frames4.setBackgroundResource(R.drawable.black_border_circle);
        frames5.setBackgroundResource(R.drawable.black_border_circle);

        text1.setTextColor(Color.parseColor("#ffffff"));
        text2.setTextColor(Color.parseColor("#000000"));
        text3.setTextColor(Color.parseColor("#000000"));
        text4.setTextColor(Color.parseColor("#000000"));
        text5.setTextColor(Color.parseColor("#000000"));

        return view;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frames1:
                frames1.setBackgroundResource(R.drawable.black_fill_circle);
                frames2.setBackgroundResource(R.drawable.black_border_circle);
                frames3.setBackgroundResource(R.drawable.black_border_circle);
                frames4.setBackgroundResource(R.drawable.black_border_circle);
                frames5.setBackgroundResource(R.drawable.black_border_circle);

                text1.setTextColor(Color.parseColor("#ffffff"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#000000"));
                text4.setTextColor(Color.parseColor("#000000"));
                text5.setTextColor(Color.parseColor("#000000"));
                break;


            case R.id.frames2:
                frames1.setBackgroundResource(R.drawable.black_border_circle);
                frames2.setBackgroundResource(R.drawable.black_fill_circle);
                frames3.setBackgroundResource(R.drawable.black_border_circle);
                frames4.setBackgroundResource(R.drawable.black_border_circle);
                frames5.setBackgroundResource(R.drawable.black_border_circle);

                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#ffffff"));
                text3.setTextColor(Color.parseColor("#000000"));
                text4.setTextColor(Color.parseColor("#000000"));
                text5.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.frames3:
                frames1.setBackgroundResource(R.drawable.black_border_circle);
                frames2.setBackgroundResource(R.drawable.black_border_circle);
                frames3.setBackgroundResource(R.drawable.black_fill_circle);
                frames4.setBackgroundResource(R.drawable.black_border_circle);
                frames5.setBackgroundResource(R.drawable.black_border_circle);

                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#ffffff"));
                text4.setTextColor(Color.parseColor("#000000"));
                text5.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.frames4:
                frames1.setBackgroundResource(R.drawable.black_border_circle);
                frames2.setBackgroundResource(R.drawable.black_border_circle);
                frames3.setBackgroundResource(R.drawable.black_border_circle);
                frames4.setBackgroundResource(R.drawable.black_fill_circle);
                frames5.setBackgroundResource(R.drawable.black_border_circle);

                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#000000"));
                text4.setTextColor(Color.parseColor("#ffffff"));
                text5.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.frames5:


                frames1.setBackgroundResource(R.drawable.black_border_circle);
                frames2.setBackgroundResource(R.drawable.black_border_circle);
                frames3.setBackgroundResource(R.drawable.black_border_circle);
                frames4.setBackgroundResource(R.drawable.black_border_circle);
                frames5.setBackgroundResource(R.drawable.black_fill_circle);

                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#000000"));
                text4.setTextColor(Color.parseColor("#000000"));
                text5.setTextColor(Color.parseColor("#ffffff"));

                break;

        }
    }
}

