package Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.List;

import Model.NewArrivalKitchenModellClass;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapteNewArrivalKitchen extends RecyclerView.Adapter<RecycleAdapteNewArrivalKitchen.MyViewHolder> {
    Context context;


    private List<NewArrivalKitchenModellClass> moviesList;

    Dialog myDialog, slideDialog;
    RadioButton btn1,btn2,btn3,btn4,btn5,btn6,btn11,btn22,btn33,btn44;
    private int lastSelectedPosition = 1;
     boolean first = true;

    public class MyViewHolder extends RecyclerView.ViewHolder {



        ImageView image,like,like2;
        TextView title,offer;
        LinearLayout linear;
        FrameLayout frame;




        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            like = (ImageView) view.findViewById(R.id.like);
            like2 = (ImageView) view.findViewById(R.id.like2);
            frame = (FrameLayout) view.findViewById(R.id.frame);
            title = (TextView)view.findViewById(R.id.title);
            offer = (TextView) view.findViewById(R.id.offer);
            linear = (LinearLayout) view.findViewById(R.id.linear);

        }

    }


    public RecycleAdapteNewArrivalKitchen(Context context, List<NewArrivalKitchenModellClass> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newarrival_kitchen_list, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        NewArrivalKitchenModellClass movie = moviesList.get(position);
        holder.image.setImageResource(movie.getImage());




        holder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first){
                    holder.like.setVisibility(View.GONE);
                    holder.like2.setVisibility(View.VISIBLE);
                    first = false;
                }else {
                    holder.like.setVisibility(View.VISIBLE);
                    holder.like2.setVisibility(View.GONE);
                    first = true;
                }
            }
        });




        holder.title.setText(movie.getTitle());



        holder.offer.setText("$620");
        holder.offer.setPaintFlags(holder.offer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}


