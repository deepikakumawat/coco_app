package com.richkart.android.my_order_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.richkart.android.R;
import com.richkart.android.utility.Constant;

import java.util.ArrayList;


public class MyOrderDetailsAdapter extends RecyclerView.Adapter<MyOrderDetailsAdapter.ViewHolder> {
    private Context context;
    ArrayList<OrderProduct> orderProductArrayList;
    private MyOrderDetailsFragment myOrderActivity;

    public MyOrderDetailsAdapter(Context context, ArrayList<OrderProduct> orderProductArrayList, MyOrderDetailsFragment myOrderActivity) {
        this.context = context;
        this.orderProductArrayList = orderProductArrayList;
        this.myOrderActivity = myOrderActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_order_details, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        OrderProduct orderProduct = orderProductArrayList.get(position);
        if (orderProduct != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(orderProduct.getmProdcutName()) ? " - " : orderProduct.getmProdcutName());
            holder.txtPrice.setText(TextUtils.isEmpty(orderProduct.getmAmount()) ? " - " : orderProduct.getmAmount());
            holder.txtQuntity.setText("QTY :"+orderProduct.getmQty());
            holder.txVendor.setText("SOLD BY:"+orderProduct.getMcompanytitle());

            String thumbnail = Constant.MEDIA_THUMBNAIL_BASE_URL + orderProduct.getmProductImg();
            Glide.with(context).load(thumbnail).placeholder(R.drawable.richkart).into(holder.imgProduct);


            holder.lyOrderDetails.setTag(orderProduct);
            holder.lyOrderDetails.setTag(R.id.lyOrderDetails, position);
            holder.lyOrderDetails.setOnClickListener(myOrderActivity);


            holder.btnCheckout.setTag(orderProduct);
            holder.btnCheckout.setTag(R.id.btnCheckout, position);

            holder.btnCheckout.setOnClickListener(myOrderActivity);
        }

    }


    @Override
    public int getItemCount() {
        return orderProductArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtProductName;
        private TextView txtPrice;
        private TextView txtStatus;
        private TextView txtQuntity;
        Button btnCheckout;
        private TextView txVendor;
        private ImageView imgProduct;
        private LinearLayout lyOrderDetails;



        public ViewHolder(View view) {
            super(view);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtPrice = view.findViewById(R.id.txtPrice);
            txtStatus = view.findViewById(R.id.txtStatus);
            txVendor = view.findViewById(R.id.txVendor);
            txtQuntity = view.findViewById(R.id.txtQuntity);
            btnCheckout =  view.findViewById(R.id.btnCheckout);
            imgProduct = view.findViewById(R.id.imgProduct);
            lyOrderDetails = view.findViewById(R.id.lyOrderDetails);

        }
    }
}
