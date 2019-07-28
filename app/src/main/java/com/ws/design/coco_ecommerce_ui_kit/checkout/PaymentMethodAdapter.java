package com.ws.design.coco_ecommerce_ui_kit.checkout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.razorpay.PaymentData;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartListResponse;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;

import java.util.ArrayList;


public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<PaymentMethodData> productDataArrayList;
    private CheckoutActivity checkoutActivity;


    public PaymentMethodAdapter(Context context, ArrayList<PaymentMethodData> productDataArrayList, CheckoutActivity checkoutActivity) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.checkoutActivity = checkoutActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_payment_method, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PaymentMethodData paymentMethodData = productDataArrayList.get(position);
        if (paymentMethodData != null) {
            holder.txtPaymentType.setText(TextUtils.isEmpty(paymentMethodData.getPaymentMethod()) ? "-" : paymentMethodData.getPaymentMethod());

            if (paymentMethodData.isSelectedPayment()) {
                holder.rbPaymentType.setChecked(true);
            } else {
                holder.rbPaymentType.setChecked(false);
            }


            holder.lyPaymentType.setTag(paymentMethodData);
            holder.lyPaymentType.setTag(R.id.lyPaymentType, position);
            holder.lyPaymentType.setOnClickListener(checkoutActivity);
        }

    }


    @Override
    public int getItemCount() {
        return productDataArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtPaymentType;
        private RadioButton rbPaymentType;
        private LinearLayout lyPaymentType;


        public ViewHolder(View view) {
            super(view);

            txtPaymentType = view.findViewById(R.id.txtPaymentType);
            rbPaymentType = view.findViewById(R.id.rbPaymentType);
            lyPaymentType = view.findViewById(R.id.lyPaymentType);


        }
    }
}
