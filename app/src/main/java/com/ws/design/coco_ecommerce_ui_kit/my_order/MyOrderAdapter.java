package com.ws.design.coco_ecommerce_ui_kit.my_order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;


public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    private Context context;
    ArrayList<MyOrderResponse.MyOrderData> myOrderDataArrayList;
    private MyOrderActivity myOrderActivity;

    public MyOrderAdapter(Context context, ArrayList<MyOrderResponse.MyOrderData> myOrderDataArrayList, MyOrderActivity myOrderActivity) {
        this.context = context;
        this.myOrderDataArrayList = myOrderDataArrayList;
        this.myOrderActivity = myOrderActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_order, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MyOrderResponse.MyOrderData myOrderData = myOrderDataArrayList.get(position);
        if (myOrderData != null) {
            holder.txtProductName.setText(TextUtils.isEmpty(myOrderData.getmProductName()) ? " - " : myOrderData.getmProductName());
            holder.txtPrice.setText(TextUtils.isEmpty(myOrderData.getmAmount()) ? " - " : myOrderData.getmAmount());
            holder.txtStatus.setText(TextUtils.isEmpty(myOrderData.getmStatus()) ? " - " : myOrderData.getmStatus());
            holder.txtDate.setText(TextUtils.isEmpty(myOrderData.getmCreatedDate()) ? " - " : myOrderData.getmCreatedDate());


            holder.txtSupport.setTag(myOrderData);
            holder.txtSupport.setTag(R.id.txtSupport, position);
            holder.txtSupport.setOnClickListener(myOrderActivity);

            holder.txtCancelOrder.setTag(myOrderData);
            holder.txtCancelOrder.setTag(R.id.txtCancelOrder, position);
            holder.txtCancelOrder.setOnClickListener(myOrderActivity);

            holder.lyOrder.setTag(myOrderData);
            holder.lyOrder.setTag(R.id.lyOrder, position);
            holder.lyOrder.setOnClickListener(myOrderActivity);

            holder.txtTrack.setTag(myOrderData);
            holder.txtTrack.setTag(R.id.txtTrack, position);
            holder.txtTrack.setOnClickListener(myOrderActivity);
        }

    }


    @Override
    public int getItemCount() {
        return myOrderDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDate;
        private TextView txtProductName;
        private TextView txtPrice;
        private TextView txtStatus;
        private TextView txtSupport;
        private TextView txtCancelOrder;
        private TextView txtTrack;
        private LinearLayout lyOrder;


        public ViewHolder(View view) {
            super(view);
            txtProductName = view.findViewById(R.id.txtProductName);
            txtPrice = view.findViewById(R.id.txtPrice);
            txtStatus = view.findViewById(R.id.txtStatus);
            txtDate = view.findViewById(R.id.txtDate);
            txtSupport = view.findViewById(R.id.txtSupport);
            txtTrack = view.findViewById(R.id.txtTrack);
            txtCancelOrder = view.findViewById(R.id.txtCancelOrder);
            lyOrder = view.findViewById(R.id.lyOrder);

        }
    }
}
