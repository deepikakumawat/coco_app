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

import Model.TopTenModelClass;
import fragment.CustomItemClickListener;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapteTopTenHome extends RecyclerView.Adapter<RecycleAdapteTopTenHome.MyViewHolder> {
    Context context;


    private List<TopTenModelClass> moviesList;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {


     /*   ImageView image;
        TextView title, type;
        LinearLayout linear;*/


        public MyViewHolder(View view) {
            super(view);

           /* image = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            type = (TextView) view.findViewById(R.id.type);
*/
//            linear = (LinearLayout) view.findViewById(R.id.linear);

        }

    }


    public RecycleAdapteTopTenHome(Context context, List<TopTenModelClass> moviesList, CustomItemClickListener customItemClickListener) {
        this.moviesList = moviesList;
        this.context = context;
        this.listener = customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_home_top_rated_products, parent, false);


        final MyViewHolder myViewHolder= new MyViewHolder(itemView);
        itemView.setOnClickListener(v -> listener.onItemClick(v, myViewHolder.getAdapterPosition()));
        return myViewHolder;


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        TopTenModelClass movie = moviesList.get(position);
      /*  holder.image.setImageResource(movie.getImage());
        holder.title.setText(movie.getTitle());
        holder.type.setText(movie.getType());*/

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


