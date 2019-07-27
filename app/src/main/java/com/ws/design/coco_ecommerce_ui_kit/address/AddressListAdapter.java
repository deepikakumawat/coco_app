package com.ws.design.coco_ecommerce_ui_kit.address;

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

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import java.util.ArrayList;


public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<AddressListResponse.AddressData> addressDataArrayList;
    private AddressListActivity addressListActivity;


    public AddressListAdapter(Context context, ArrayList<AddressListResponse.AddressData> addressDataArrayList,  AddressListActivity addressListActivity) {
        this.context = context;
        this.addressDataArrayList = addressDataArrayList;
        this.addressListActivity = addressListActivity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_address_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AddressListResponse.AddressData addressData = addressDataArrayList.get(position);
        if (addressData != null) {
            holder.txtName.setText(TextUtils.isEmpty(addressData.getmName()) ? "-" : addressData.getmName());
            holder.txtAddressType.setText(TextUtils.isEmpty(addressData.getmName()) ? "-" : addressData.getmAddressType());
            holder.txtAddress1.setText(TextUtils.isEmpty(addressData.getmAddress1()) ? "-" : addressData.getmAddress1());
            holder.txtAddress2.setText(TextUtils.isEmpty(addressData.getmAddress2()) ? "-" : addressData.getmAddress2());
            holder.txtLandmark.setText(TextUtils.isEmpty(addressData.getmLandmark()) ? "-" : addressData.getmLandmark());
            holder.txtCity.setText(TextUtils.isEmpty(addressData.getmCity()) ? "-" : addressData.getmCity());
            holder.txtState.setText(TextUtils.isEmpty(addressData.getmState()) ? "-" : addressData.getmState());
            holder.txtCountry.setText(TextUtils.isEmpty(addressData.getmCountry()) ? "-" : addressData.getmCountry());
            holder.txtZipcode.setText(TextUtils.isEmpty(addressData.getmZipcode()) ? "-" : addressData.getmZipcode());
            holder.txtPhone.setText(TextUtils.isEmpty(addressData.getmPhone()) ? "-" : addressData.getmPhone());

            if (addressData.ismSelecetdAddress()) {
                holder.imgSelectAddress.setVisibility(View.VISIBLE);
            }else{
                holder.imgSelectAddress.setVisibility(View.GONE);
            }

            if (addressData.isDeleteButtonVisible()) {
                holder.btnDelete.setVisibility(View.VISIBLE);
            }else{
                holder.btnDelete.setVisibility(View.GONE);
            }



            holder.btnDelete.setTag(addressData);
            holder.btnDelete.setTag(R.id.btnDelete, position);
            holder.btnDelete.setOnClickListener(addressListActivity);

            holder.lyAddress.setTag(addressData);
            holder.lyAddress.setTag(R.id.lyAddress, position);
            holder.lyAddress.setOnClickListener(addressListActivity);
        }

    }


    @Override
    public int getItemCount() {
        return addressDataArrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtAddressType;
        private TextView txtAddress1;
        private TextView txtAddress2;
        private TextView txtLandmark;
        private TextView txtCity;
        private TextView txtState;
        private TextView txtCountry;
        private TextView txtZipcode;
        private TextView txtPhone;
        private Button btnDelete;
        private LinearLayout lyAddress;
        private ImageView imgSelectAddress;


        public ViewHolder(View view) {
            super(view);

            txtName = view.findViewById(R.id.txtName);
            txtAddressType = view.findViewById(R.id.txtAddressType);
            txtAddress1 = view.findViewById(R.id.txtAddress1);
            txtAddress2 = view.findViewById(R.id.txtAddress2);
            txtLandmark = view.findViewById(R.id.txtLandmark);
            txtCity = view.findViewById(R.id.txtCity);
            txtState = view.findViewById(R.id.txtState);
            txtCountry = view.findViewById(R.id.txtCountry);
            txtZipcode = view.findViewById(R.id.txtZipcode);
            txtPhone = view.findViewById(R.id.txtPhone);
            btnDelete = view.findViewById(R.id.btnDelete);
            lyAddress = view.findViewById(R.id.lyAddress);
            imgSelectAddress = view.findViewById(R.id.imgSelectAddress);


        }
    }
}
