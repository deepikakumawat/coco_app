package com.ws.design.coco_ecommerce_ui_kit.product_details;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.List;


public class ProductDetailsSizeAdapter extends RecyclerView.Adapter<ProductDetailsSizeAdapter.ViewHolder> {
    private Context context;
    private List<ColorSizeData> productColorSizeDataArrayList;
    private ProductDetailFragment productDetailFragment;


    public ProductDetailsSizeAdapter(Context context, List<ColorSizeData> productColorSizeDataArrayList, ProductDetailFragment productDetailFragment) {
        this.context = context;
        this.productColorSizeDataArrayList = productColorSizeDataArrayList;
        this.productDetailFragment = productDetailFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.size_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            ColorSizeData colorCode = productColorSizeDataArrayList.get(position);
            if (colorCode != null) {


                holder.txtSize.setText(colorCode.getmAttrbuteRelatedData());

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if (colorCode.isSelected()) {
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        holder.lySize.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.gray));
                    } else {
                        holder.lySize.setBackground(ContextCompat.getDrawable(context, R.color.gray));
                    }
                } else {
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        holder.lySize.setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.white));
                    } else {
                        holder.lySize.setBackground(ContextCompat.getDrawable(context, R.color.white));
                    }
                }

                holder.lySize.setTag(colorCode);
                holder.lySize.setOnClickListener(productDetailFragment);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return productColorSizeDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSize;
        LinearLayout lySize;


        public ViewHolder(View view) {
            super(view);

            txtSize = view.findViewById(R.id.txtSize);
            lySize = view.findViewById(R.id.lySize);


        }
    }
}
