package com.ws.design.coco_ecommerce_ui_kit.payment_gateway;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;

import org.json.JSONObject;



public class RazorPaymentActivity extends Activity implements PaymentResultListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_razor_payment);



        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout
        TextView txtCourse = (TextView) findViewById(R.id.txtCourse);
        TextView txtPrice = (TextView) findViewById(R.id.txtPrice);
        Button button = (Button) findViewById(R.id.btnPurchase);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "10000");

            JSONObject preFill = new JSONObject();
            preFill.put("email", CocoPreferences.getUserEmail());
            preFill.put("contact", CocoPreferences.getUserPhone());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {

/*

            Intent intent = new Intent(RazorPaymentActivity.this, DrawerBaseActivity.class);
            intent.putExtra("test", getFirstName);
            startActivity(intent);
*/

            Toast.makeText(getApplicationContext(), "Successfully payment", Toast.LENGTH_LONG).show();


//            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {


            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
