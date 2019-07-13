package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ws.design.coco_ecommerce_ui_kit.address.AddAddressActivity;
import com.ws.design.coco_ecommerce_ui_kit.CategoryActivity;
import com.ws.design.coco_ecommerce_ui_kit.CocoAddNewAddress1Activity;
import com.ws.design.coco_ecommerce_ui_kit.checkout.CheckoutActivity;
import com.ws.design.coco_ecommerce_ui_kit.CocoEcommerceHomeActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishlistActivity;
import com.ws.design.coco_ecommerce_ui_kit.CocoFilter1_Activity;
import com.ws.design.coco_ecommerce_ui_kit.home.HomeFragment;
import com.ws.design.coco_ecommerce_ui_kit.CocoLogin1Activity;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginActivity;
import com.ws.design.coco_ecommerce_ui_kit.CocoProductDetails2;
import com.ws.design.coco_ecommerce_ui_kit.CocoProductDetails3;
import com.ws.design.coco_ecommerce_ui_kit.CocoProductDetails4;
import com.ws.design.coco_ecommerce_ui_kit.CocoProductListGrid10Activity;
import com.ws.design.coco_ecommerce_ui_kit.CocoProductListGrid2Activity;
import com.ws.design.coco_ecommerce_ui_kit.CocoProductListGridwithTabActivity;
import com.ws.design.coco_ecommerce_ui_kit.CocoSearch1;
import com.ws.design.coco_ecommerce_ui_kit.Coco_Search2;
import com.ws.design.coco_ecommerce_ui_kit.Coco_Search3;
import com.ws.design.coco_ecommerce_ui_kit.Coco_Search_Activity;
import com.ws.design.coco_ecommerce_ui_kit.Coco_product_list_grid_8;
import com.ws.design.coco_ecommerce_ui_kit.CocoecommerceHome2Activity;
import com.ws.design.coco_ecommerce_ui_kit.Cocoproductdetail5;
import com.ws.design.coco_ecommerce_ui_kit.Cocoproductlist3Activity;
import com.ws.design.coco_ecommerce_ui_kit.Cocoproductlistgrid5;
import com.ws.design.coco_ecommerce_ui_kit.Cocoproductlistgrid4;
import com.ws.design.coco_ecommerce_ui_kit.Cocoproductlistgrid6;
import com.ws.design.coco_ecommerce_ui_kit.Cocoproductlistgrid7;
import com.ws.design.coco_ecommerce_ui_kit.Cocoproductlistgrid9;
import com.ws.design.coco_ecommerce_ui_kit.Cocoreviews2Activity;
import com.ws.design.coco_ecommerce_ui_kit.ExploreActivity;
import com.ws.design.coco_ecommerce_ui_kit.FilterActivity;
import com.ws.design.coco_ecommerce_ui_kit.KitchenActivity;
import com.ws.design.coco_ecommerce_ui_kit.MobileVerificationActivity;
import com.ws.design.coco_ecommerce_ui_kit.MyAccountActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderActivity;
import com.ws.design.coco_ecommerce_ui_kit.Navigation1;
import com.ws.design.coco_ecommerce_ui_kit.NavigationActivity2;
import com.ws.design.coco_ecommerce_ui_kit.DrawerActivity;
import com.ws.design.coco_ecommerce_ui_kit.product_details.ProductDetailFragment;
import com.ws.design.coco_ecommerce_ui_kit.ProductGridActivity;
import com.ws.design.coco_ecommerce_ui_kit.ProductListActivity;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.ReviewActivity;
import com.ws.design.coco_ecommerce_ui_kit.Reviews;
import com.ws.design.coco_ecommerce_ui_kit.signup.SignupActivity;
import com.ws.design.coco_ecommerce_ui_kit.Signup2Activity;

import java.util.List;

import Model.CocoListModelClass;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapteCocoList extends RecyclerView.Adapter<RecycleAdapteCocoList.MyViewHolder> {
    Context context;


    private List<CocoListModelClass> moviesList;

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


    public RecycleAdapteCocoList(Context context, List<CocoListModelClass> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coco_list, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        CocoListModelClass movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 0) {
                    Intent i = new Intent(context, HomeFragment.class);
                    context.startActivity(i);
                } else if (position == 1) {
                    Intent i = new Intent(context, DrawerActivity.class);
                    context.startActivity(i);
                } else if (position == 2) {
                    Intent i = new Intent(context, ExploreActivity.class);
                    context.startActivity(i);
                } else if (position == 3) {
                    Intent i = new Intent(context, CategoryActivity.class);
                    context.startActivity(i);
                } else if (position == 4) {
                    Intent i = new Intent(context, KitchenActivity.class);
                    context.startActivity(i);
                }else if (position == 5) {
                    Intent i = new Intent(context, ProductDetailFragment.class);
                    context.startActivity(i);
                }else if (position == 6) {
                    Intent i = new Intent(context, ProductGridActivity.class);
                    context.startActivity(i);
                }else if (position == 7) {
                    Intent i = new Intent(context, ProductListActivity.class);
                    context.startActivity(i);
                }else if (position == 8) {
                    Intent i = new Intent(context, FilterActivity.class);
                    context.startActivity(i);
                }else if (position == 9) {
                    Intent i = new Intent(context, Coco_Search_Activity.class);
                    context.startActivity(i);
                }else if (position == 10) {
                    Intent i = new Intent(context, CocoSearch1.class);
                    context.startActivity(i);
                }else if (position == 11) {
                    Intent i = new Intent(context, Coco_Search2.class);
                    context.startActivity(i);
                }else if (position == 12) {
                    Intent i = new Intent(context, Coco_Search3.class);
                    context.startActivity(i);
                }else if (position == 13) {
                    Intent i = new Intent(context, MyOrderActivity.class);
                    context.startActivity(i);
                }else if (position == 14) {
                    Intent i = new Intent(context, MyAccountActivity.class);
                    context.startActivity(i);
                }else if (position == 15) {
                    Intent i = new Intent(context, Navigation1.class);
                    context.startActivity(i);
                }else if (position == 16) {
                    Intent i = new Intent(context, NavigationActivity2.class);
                    context.startActivity(i);
                }else if (position == 17) {
                    Intent i = new Intent(context, AddAddressActivity.class);
                    context.startActivity(i);
                }else if (position == 18) {
                    Intent i = new Intent(context, CocoAddNewAddress1Activity.class);
                    context.startActivity(i);
                }else if (position == 19) {
                    Intent i = new Intent(context, CheckoutActivity.class);
                    context.startActivity(i);
                }else if (position == 20) {
//                    Intent i = new Intent(context, CartActivity.class);
//                    context.startActivity(i);
                } else if (position == 21) {
                    Intent i = new Intent(context, MyWishlistActivity.class);
                    context.startActivity(i);
                }else if (position == 22) {
                    Intent i = new Intent(context, CocoLogin1Activity.class);
                    context.startActivity(i);
                }else if (position == 23) {
                    Intent i = new Intent(context, LoginActivity.class);
                    context.startActivity(i);
                }else if (position == 24) {
                    Intent i = new Intent(context, MobileVerificationActivity.class);
                    context.startActivity(i);
                }else if (position == 25) {
                    Intent i = new Intent(context, SignupActivity.class);
                    context.startActivity(i);
                }else if (position == 26) {
                    Intent i = new Intent(context, Signup2Activity.class);
                    context.startActivity(i);
                }else if (position == 27) {
                    Intent i = new Intent(context, ReviewActivity.class);
                    context.startActivity(i);
                }else if (position == 28) {
                    Intent i = new Intent(context, CocoFilter1_Activity.class);
                    context.startActivity(i);



                } if (position == 29) {
                    Intent i = new Intent(context, CocoEcommerceHomeActivity.class);
                    context.startActivity(i);
                } else if (position == 30) {
                    Intent i = new Intent(context, CocoProductListGrid2Activity.class);
                    context.startActivity(i);
                } else if (position == 31) {
                    Intent i = new Intent(context, CocoecommerceHome2Activity.class);
                    context.startActivity(i);
                } else if (position == 32) {
                    Intent i = new Intent(context, CocoProductListGrid10Activity.class);
                    context.startActivity(i);
                } else if (position == 33) {
                    Intent i = new Intent(context, CocoProductListGridwithTabActivity.class);
                    context.startActivity(i);
                }else if (position == 34) {
                    Intent i = new Intent(context, Cocoreviews2Activity.class);
                    context.startActivity(i);
                }else if (position == 35) {
                    Intent i = new Intent(context, Cocoproductlist3Activity.class);
                    context.startActivity(i);




                }else if (position == 36) {
                    Intent i = new Intent(context, Coco_product_list_grid_8.class);
                    context.startActivity(i);
                }else if (position == 37) {
                    Intent i = new Intent(context, CocoProductDetails3.class);
                    context.startActivity(i);
                }else if (position == 38) {
                    Intent i = new Intent(context, CocoProductDetails2.class);
                    context.startActivity(i);
                }else if (position == 39) {
                    Intent i = new Intent(context, CocoProductDetails4.class);
                    context.startActivity(i);


                }else if (position == 40) {
                    Intent i = new Intent(context, Cocoproductlistgrid4.class);
                    context.startActivity(i);
                }else if (position == 41) {
                    Intent i = new Intent(context, Cocoproductlistgrid5.class);
                    context.startActivity(i);
                }else if (position == 42) {
                    Intent i = new Intent(context, Cocoproductlistgrid6.class);
                    context.startActivity(i);
                }else if (position == 43) {
                    Intent i = new Intent(context, Cocoproductlistgrid7.class);
                    context.startActivity(i);
                }else if (position == 44) {
                    Intent i = new Intent(context, Cocoproductlistgrid9.class);
                    context.startActivity(i);
                }else if (position == 45) {
                    Intent i = new Intent(context, Reviews.class);
                    context.startActivity(i);
                }else if (position == 46) {
                    Intent i = new Intent(context, Cocoproductdetail5.class);
                    context.startActivity(i);
                }

            }

        });





    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}


