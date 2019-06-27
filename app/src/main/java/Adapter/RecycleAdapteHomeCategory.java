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

import java.util.List;

import Model.HomeCategoryModelClass;
import fragment.CustomItemClickListener;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapteHomeCategory extends RecyclerView.Adapter<RecycleAdapteHomeCategory.MyViewHolder> {
    Context context;


    private List<HomeCategoryModelClass> moviesList;
    CustomItemClickListener listener;
    int myPos = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        ImageView image;
        LinearLayout linear;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);

            linear = (LinearLayout) view.findViewById(R.id.linear);

        }

    }


    public RecycleAdapteHomeCategory(Context context, List<HomeCategoryModelClass> moviesList, CustomItemClickListener customItemClickListener) {
        this.moviesList = moviesList;
        this.context = context;
        this.listener = customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_list, parent, false);


        final MyViewHolder myViewHolder= new MyViewHolder(itemView);
        itemView.setOnClickListener(v -> listener.onItemClick(v, myViewHolder.getAdapterPosition()));

        return myViewHolder;


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        HomeCategoryModelClass movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClick(view, holder.getAdapterPosition());

            }
        });



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}


