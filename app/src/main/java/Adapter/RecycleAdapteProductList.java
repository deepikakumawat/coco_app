package Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.home.home_response.ProductData;

import java.util.List;

import Model.ProductGridModellClass;
import fragment.PopularListFragment;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapteProductList extends RecyclerView.Adapter<RecycleAdapteProductList.MyViewHolder> {
    Context context;


    private List<ProductData> moviesList;
    PopularListFragment mpopularListFragment;

    int myPos = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title,offer,txt_product_name;
        ImageView image;
        LinearLayout linear;
        private LinearLayout lyProduct;


        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            txt_product_name = (TextView) view.findViewById(R.id.txt_product_name);
            offer = (TextView) view.findViewById(R.id.offer);
            linear = (LinearLayout) view.findViewById(R.id.linear);
            lyProduct = (LinearLayout) view.findViewById(R.id.ly_root);

        }

    }


    public RecycleAdapteProductList(Context context, List<ProductData> moviesList, PopularListFragment popularListFragment) {
        this.moviesList = moviesList;
        mpopularListFragment = popularListFragment;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_popular_list2, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        ProductData movie = moviesList.get(position);
       // holder.image.setImageResource(movie.getImage());

       holder.txt_product_name.setText(""+movie.getmProductName());

        holder.lyProduct.setTag(movie);
        holder.lyProduct.setTag(R.id.ly_root,position);
        holder.lyProduct.setOnClickListener(mpopularListFragment);
//        holder.offer.setPaintFlags(holder.offer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}


