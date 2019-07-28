package com.ws.design.coco_ecommerce_ui_kit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderActivity;
import com.ws.design.coco_ecommerce_ui_kit.profile.ProfileActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title, edit, test, edit_txt, txtProfile;
    private ImageView imgBack;
    private Button btnLogout;
    private TextView txtUserName;
    private TextView txtUserEmail;
    private LinearLayout lyMyOrder;
    private RelativeLayout ryParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        ryParent = findViewById(R.id.ryParent);
        title = findViewById(R.id.title);
        edit_txt = findViewById(R.id.edit_txt);
        edit_txt.setVisibility(View.GONE);
        edit = findViewById(R.id.edit);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        txtProfile = findViewById(R.id.txtProfile);
        btnLogout = findViewById(R.id.btnLogout);
        txtUserName = findViewById(R.id.txtUserName);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        lyMyOrder = findViewById(R.id.lyMyOrder);
        edit.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);

        imgBack.setOnClickListener(this);
        txtProfile.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        lyMyOrder.setOnClickListener(this);

        txtUserName.setText(CocoPreferences.getFirstName() + " " + CocoPreferences.getLastName());
        txtUserEmail.setText(CocoPreferences.getUserEmail());

    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.imgBack:
                    finish();
                    break;
                case R.id.txtProfile:
                    startActivity(new Intent(MyAccountActivity.this, ProfileActivity.class));
                    break;
                case R.id.btnLogout:
                    logout();
                    break;
                case R.id.lyMyOrder:
                    startActivity(new Intent(MyAccountActivity.this, MyOrderActivity.class));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void logout() {
        try {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.do_you_want_to_logout))
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                          /*  CocoPreferences.removeValueForKey(LoginResponse.KEY_USERID);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_USEREMAIL);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_USERPHONE);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_FIRST_NAME);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_LAST_NAME);*/


                            CocoPreferences.removeValueForKey("UserID");
                            CocoPreferences.removeValueForKey("UserEmail");
                            CocoPreferences.removeValueForKey("UserPhone");
                            CocoPreferences.removeValueForKey("FirstName");
                            CocoPreferences.removeValueForKey("LastName");


                            txtUserName.setText("");
                            txtUserEmail.setText("");

                            Util.showCenteredToast(ryParent,MyAccountActivity.this, "Logout Successfully!");


                            Intent data = new Intent();
                            setResult(Activity.RESULT_OK, data);
                            finish();



                         /*   android.app.Fragment f = getFragmentManager().findFragmentById(R.id.frame_container);
                            if (f instanceof FragmentProfile) {
                                getFragmentManager().popBackStack();
                            }
*/
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
