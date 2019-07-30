package com.ws.design.coco_ecommerce_ui_kit.product_rating_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response.ProductRatingResponse;
import com.ws.design.coco_ecommerce_ui_kit.product_rating_list.product_rating_response.Ratings;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.dismissProDialog;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;
import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showProDialog;

public class ReviewActivity extends AppCompatActivity implements ProductRatingView, View.OnClickListener {

    TextView txt1;
    private ArrayList<Ratings> ratingsArrayList = new ArrayList<>();
    private ProductRatingPresenter productRatingPresenter;
    private RecyclerView rvRating;
    private Button btnSubmit;
    private RatingBar productRating;
    private EditText etxtReview;
    private ImageView imgBack;
    private ReviewListAdapter reviewListAdapter;
    private String productId;
    private RatingBar rbProductRating;
    private TextView txtReview;
    private TextView txtRating;
    private LinearLayout lyCustomerRating;
    private boolean isReviewAdded = false;
    private RelativeLayout ryParent;
    private ShimmerFrameLayout mShimmerViewContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productId = extras.getString("productId");
        }

        productRatingPresenter = new ProductRatingPresenter(this);




        init();
    }

    private void init() {
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        ryParent = findViewById(R.id.ryParent);
        lyCustomerRating = findViewById(R.id.lyCustomerRating);
        txtRating = findViewById(R.id.txtRating);
        txtReview = findViewById(R.id.txtReview);
        rbProductRating = findViewById(R.id.rbProductRating);
        imgBack = findViewById(R.id.imgBack);
        etxtReview = findViewById(R.id.etxtReview);
        productRating = findViewById(R.id.productRating);
        btnSubmit = findViewById(R.id.btnSubmit);
        rvRating = findViewById(R.id.rvRating);
        txt1 = findViewById(R.id.txt1);
        txt1.setText("Reviews");
        btnSubmit.setOnClickListener(this);
        imgBack.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRating.setLayoutManager(layoutManager);

        if (Util.isDeviceOnline(this)) {
            productRatingPresenter.getProductRating(productId);

        } else {
            showCenteredToast(ryParent,this, getString(R.string.network_connection),"");

        }
    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.btnSubmit:
                    String comment = etxtReview.getText().toString();
                    Float rating = productRating.getRating();

                    String ratingFinal = String.valueOf((Math.round(rating)));
                    if (isValid(comment, rating)) {
                        productRatingPresenter.addReview(CocoPreferences.getUserId(), comment, productId, ratingFinal);
                    }

                    Util.hideKeyBoardMethod(this,btnSubmit);

                    break;
                case R.id.imgBack:

                  /*  if (isReviewAdded) {
                        Intent data = new Intent();
                        data.putExtra("isReviewAdded",isReviewAdded);
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    }else{
                        finish();

                    }
*/
                    backScreen();


                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void backScreen() {
        Intent data = new Intent();
        data.putExtra("isReviewAdded", isReviewAdded);
        setResult(Activity.RESULT_OK, data);
        finish();
    }

    @Override
    public void showWait() {

        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();

//        showProDialog(this);
    }

    @Override
    public void removeWait() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
//        dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {

        showCenteredToast(ryParent,this, appErrorMessage,"");
    }


    @Override
    public void getProductRating(ProductRatingResponse productRatingResponse) {
        if (productRatingResponse != null) {

            if (productRatingResponse.getmData() != null) {

                if (!productRatingResponse.getmData().getmRatings().isEmpty()) {

                    rvRating.setVisibility(View.VISIBLE);
                    ratingsArrayList.clear();
                    ratingsArrayList.addAll(productRatingResponse.getmData().getmRatings());

                    reviewListAdapter = new ReviewListAdapter(this, ratingsArrayList, ReviewActivity.this);
                    rvRating.setAdapter(reviewListAdapter);

                    lyCustomerRating.setVisibility(View.VISIBLE);


                    if (!productRatingResponse.getmData().getmOverAll().isEmpty()) {


                        if (!TextUtils.isEmpty(productRatingResponse.getmData().getmOverAll().get(0).getmAvgRating())) {

                            String rating = productRatingResponse.getmData().getmOverAll().get(0).getmAvgRating();

                            rbProductRating.setRating(Float.parseFloat(rating));
                            txtRating.setText(rating);

                        } else {
                            rbProductRating.setRating(0);
                            txtRating.setText("" + 0);

                        }


                    } else {
                        rbProductRating.setRating(0);
                        txtRating.setText("" + 0);

                    }


                    if (!TextUtils.isEmpty(productRatingResponse.getmData().getmTotalReviews())) {
                        txtReview.setText(productRatingResponse.getmData().getmTotalReviews() + " Reviews");
                    } else {
                        txtReview.setText(0 + " Reviews");

                    }

                } else {
                    rvRating.setVisibility(View.GONE);
                    lyCustomerRating.setVisibility(View.GONE);
                }
            }


        }
    }

    @Override
    public void addRating(AddRatingResponse addRatingResponse) {
        if (!TextUtils.isEmpty(addRatingResponse.getmStatus()) && ("1".equalsIgnoreCase(addRatingResponse.getmStatus()))) {
            showCenteredToast(ryParent,this, "Review added successfully", Constant.API_SUCCESS);

            isReviewAdded = true;

            etxtReview.getText().clear();
            productRating.setRating(0);
            productRatingPresenter.getProductRating(productId);

        } else {
            showCenteredToast(ryParent,this, getString(R.string.somethingWentWrong),"");
        }
    }

    private boolean isValid(String comment, Float rating) throws Exception {
        boolean validation_detials_flag = false;
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(comment)) {
                showCenteredToast(ryParent,this, getString(R.string.comment_validation_message),"");
                etxtReview.requestFocus();
            } else if (rating == 0.0) {
                showCenteredToast(ryParent,this, getString(R.string.rating_validation_message),"");
                productRating.requestFocus();
            } else {
                validation_detials_flag = true;

            }
        } else {
            showCenteredToast(ryParent,this, getString(R.string.network_connection),"");
        }
        return validation_detials_flag;
    }

}
