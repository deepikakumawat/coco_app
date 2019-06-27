package fragment;

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

import java.util.ArrayList;

import Adapter.CocoProductDetailsAdapter;
import Adapter.RecycleAdapteTopTenHome;
import Model.TopTenModelClass;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import me.relex.circleindicator.CircleIndicator;

public class CocoProductDetails2Fragment extends Fragment {

    ViewPager viewPager;
    private CocoProductDetailsAdapter a1;
    CircleIndicator indicator;


    private ArrayList<TopTenModelClass> topTenModelClasses;
    private RecyclerView top_ten_crecyclerview;
    private RecycleAdapteTopTenHome mAdapter2;

    private Integer image1[] = {R.drawable.ac, R.drawable.headphones, R.drawable.ac, R.drawable.headphones};
    private String title1[] = {"Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color", "Vigo Atom Personal Air Condi....", "Bosh Head Phone Blue Color",};
    private String type[] = {"Kitenid", "HeadPhones", "Kitenid", "HeadPhones"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details2fragment, container, false);

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

        return view;
    }

}

