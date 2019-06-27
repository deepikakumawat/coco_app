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
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.List;

import Model.CocoCartModel3;

public class RecycleAdapterCocoCart2 extends RecyclerView.Adapter<RecycleAdapterCocoCart2.MyViewHolder> {
    Context context;


    private List<CocoCartModel3> moviesList;

    int myPos = 0;
    private int lastSelectedPosition = 0;

    boolean check = false;
    RadioButton btn;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView paymentType;
        ImageView radioButton2,logoImage;
        LinearLayout viewline;
        RadioButton btn;



        public MyViewHolder(View view) {
            super(view);

            paymentType = (TextView) view.findViewById(R.id.paymentType);
            logoImage=(ImageView)view.findViewById(R.id.logoImage);
            viewline = (LinearLayout)view.findViewById(R.id.viewline);
            btn = view.findViewById(R.id.button1);


        }

    }


    public RecycleAdapterCocoCart2(Context context, List<CocoCartModel3> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public RecycleAdapterCocoCart2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_payment_recycler, parent, false);


        return new RecycleAdapterCocoCart2.MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final RecycleAdapterCocoCart2.MyViewHolder holder, final int position) {
        CocoCartModel3 movie = moviesList.get(position);
        holder.paymentType.setText(movie.getPaymentType());
        holder.logoImage.setImageResource((movie.getLogoImage()));

//        holder.btn.setChecked(lastSelectedPosition == position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = position;
                notifyDataSetChanged();
            }
        });

        if (lastSelectedPosition == position) {
            holder.btn.setChecked(true);
            holder.btn.setEnabled(false);
        }
        else {
            holder.btn.setChecked(false);
            holder.btn.setEnabled(false);
        }


        if(position==3){

            holder.viewline.setVisibility(View.GONE);
        }else {
            holder.viewline.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}
