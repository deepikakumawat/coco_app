package com.ws.design.coco_ecommerce_ui_kit.checkout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;


public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<PaymentMethodData> productDataArrayList;
    private CheckoutFragment checkoutFragment;


    public PaymentMethodAdapter(Context context, ArrayList<PaymentMethodData> productDataArrayList, CheckoutFragment checkoutFragment) {
        this.context = context;
        this.productDataArrayList = productDataArrayList;
        this.checkoutFragment = checkoutFragment;

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
            holder.lyPaymentType.setOnClickListener(checkoutFragment);
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
